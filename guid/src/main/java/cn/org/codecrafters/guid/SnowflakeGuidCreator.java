/*
 * Copyright (C) 2023 CodeCraftersCN.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.org.codecrafters.guid;

import cn.org.codecrafters.guid.exceptions.TimingException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * {@link SnowflakeGuidCreator} 使用雪花算法生成全局唯一 ID，其结合时间戳、工作机 ID
 * 以及数据中心 ID 来创建一个 64 位长度的正整数。生成的 ID 的比特分布如下：
 * <ul>
 *     <li>1 比特用于签名</li>
 *     <li>41 比特用于表示时间戳（单位：毫秒）</li>
 *     <li>5 比特用于表示数据中心 ID</li>
 *     <li>5 比特用于表示服务器 ID</li>
 *     <li>12 比特表示每毫秒内的序列号</li>
 * </ul>
 * <p>
 * 初始化 {@link SnowflakeGuidCreator} 时，您必须提供服务器 ID 和数据中心 ID，确保它们在位
 * 大小定义的有效范围内。生成器会维护一个内部序列号，在同一毫秒内生成的 ID 会递增。如果系统时钟向后
 * 移动，则会出现异常，以防止生成具有重复时间戳的 ID。
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public final class SnowflakeGuidCreator implements GuidCreator<Long> {

    /**
     * 默认的起始时间
     *
     * @value 2015-01-01T00:00:00Z
     */
    private static final long DEFAULT_CUSTOM_EPOCH = 1_420_070_400_000L;

    /**
     * 用于生成 ID 的自定义开始时间的时间戳
     */
    private final long startEpoch;

    /**
     * 服务器 ID 所占位长.
     */
    private final long workerIdBits = 5L;

    /**
     * 数据中心 ID 所占位长。
     */
    private final long dataCentreIdBits = 5L;

    /**
     * 当前 ID 生成服务器的服务器 ID。
     */
    private final long workerId;

    /**
     * 当前 ID 生成服务器的数据中心 ID。
     */
    private final long dataCentreId;

    /**
     * 当前序列号。
     */
    private long sequence = 0L;

    /**
     * 上次生成 ID 的时间戳。
     */
    private long lastTimestamp = -1L;

    /**
     * 使用默认的开始时间戳与自定义的服务器 ID 和数据中心 ID 创建 {@link
     * SnowflakeGuidCreator} 实例。
     *
     * @param dataCentreId 数据中心 ID（0-31之间）
     * @param workerId     服务器 ID（0-31之间）
     */
    public SnowflakeGuidCreator(long dataCentreId, long workerId) {
        this(dataCentreId, workerId, DEFAULT_CUSTOM_EPOCH);
    }

    /**
     * 使用指定的开始时间戳与自定义的服务器 ID 和数据中心 ID 创建 {@link
     * SnowflakeGuidCreator} 实例。
     *
     * @param dataCentreId 数据中心 ID（0-31之间）
     * @param workerId     服务器 ID（0-31之间）
     * @param startEpoch   自定义的纪元时间戳（以毫秒为单位），从该时间戳开始生成 ID
     * @throws IllegalArgumentException 如果起始时间大于当前时间戳，或者服务器 ID 或
     *                                  数据中心 ID 超出范围
     */
    public SnowflakeGuidCreator(long dataCentreId, long workerId, long startEpoch) {
        if (startEpoch > currentTimestamp()) {
            throw new IllegalArgumentException("起始时间戳不得大于当前时间戳");
        }

        var maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("服务器 ID 不得大于 %d 或小于 0",
                    maxWorkerId));
        }

        var maxDataCentreId = ~(-1L << dataCentreIdBits);
        if (dataCentreId > maxDataCentreId || dataCentreId < 0) {
            throw new IllegalArgumentException(String.format("数据中心 ID 不得大于 %d 或小于 0",
                    maxDataCentreId));
        }

        this.startEpoch = startEpoch;
        this.workerId = workerId;
        this.dataCentreId = dataCentreId;
    }

    /**
     * 生成下一个全局唯一 ID。
     *
     * @return 生成的下一个全局唯一 ID
     * @throws TimingException 如果系统时钟向后移动，表明时间戳序列无效
     */
    @Override
    public synchronized Long nextId() {
        var timestamp = currentTimestamp();

        // If the current time is less than the timestamp of the last ID generation, it means that the system clock
        // has been set back and an exception should be thrown.
        if (timestamp < lastTimestamp) {
            throw new TimingException("时钟回调，拒绝生成 %d 毫秒的 ID"
                    .formatted(lastTimestamp - timestamp));
        }

        // If generated at the same time, perform intra-millisecond sequences
        long sequenceBits = 12L;
        if (lastTimestamp == timestamp) {
            long sequenceMask = ~(-1L << sequenceBits);
            sequence = (sequence + 1) & sequenceMask;
            // Sequence overflow in milliseconds
            if (sequence == 0) {
                // Block to the next millisecond, get a new timestamp
                timestamp = awaitToNextMillis(lastTimestamp);
            }
        }
        // Timestamp change, sequence reset in milliseconds
        else {
            sequence = 0L;
        }

        // Timestamp of last ID generation
        lastTimestamp = timestamp;

        // shifted and put together by or operations to form a 64-bit ID
        var timestampLeftShift = sequenceBits + workerIdBits + dataCentreIdBits;
        var dataCentreIdShift = sequenceBits + workerIdBits;
        return ((timestamp - startEpoch) << timestampLeftShift)
                | (dataCentreId << dataCentreIdShift)
                | (workerId << sequenceBits)
                | sequence;
    }

    /**
     * 阻塞至下一毫秒，以获取新的时间戳。
     *
     * @param lastTimestamp 最后一次生成 ID 的时间戳
     * @return 当前的时间戳
     */
    private long awaitToNextMillis(long lastTimestamp) {
        var timestamp = currentTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTimestamp();
        }
        return timestamp;
    }

    /**
     * 以毫秒返回当前时间戳。
     *
     * @return 当前时间戳
     */
    private long currentTimestamp() {
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}

