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
 * SnowflakeGuidCreator - GUID generator based on the Snowflake algorithm.
 * <p>
 * The SnowflakeGuidCreator generates unique identifiers using the Snowflake
 * algorithm, which combines a timestamp, worker ID, and data centre ID to
 * create 64-bit long integers. The bit distribution for the generated IDs is
 * as follows:
 * <ul>
 *     <li>1 bit for sign</li>
 *     <li>41 bits for timestamp (in milliseconds)</li>
 *     <li>5 bits for data centre ID</li>
 *     <li>5 bits for worker ID</li>
 *     <li>12 bits for sequence number (per millisecond)</li>
 * </ul>
 * <p>
 * When initializing the SnowflakeGuidCreator, you must provide the worker ID
 * and data centre ID, ensuring they are within the valid range defined by the
 * bit size. The generator maintains an internal sequence number that
 * increments for IDs generated within the same millisecond. If the system
 * clock moves backward, an exception is thrown to prevent generating IDs with
 * repeated timestamps.
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public final class SnowflakeGuidCreator implements GuidCreator<Long> {

    /**
     * Default custom epoch.
     *
     * @value 2015-01-01T00:00:00Z
     */
    private static final long DEFAULT_CUSTOM_EPOCH = 1_420_070_400_000L;

    /**
     * The start epoch timestamp to generate IDs from.
     */
    private final long startEpoch;

    /**
     * The number of bits reserved for the worker ID.
     */
    private final long workerIdBits = 5L;

    /**
     * The number of bits reserved for the data centre ID.
     */
    private final long dataCentreIdBits = 5L;

    /**
     * The worker ID assigned to this generator.
     */
    private final long workerId;

    /**
     * The data centre ID assigned to this generator.
     */
    private final long dataCentreId;

    /**
     * The current sequence number.
     */
    private long sequence = 0L;

    /**
     * The timestamp of the last generated ID.
     */
    private long lastTimestamp = -1L;

    /**
     * Constructs a SnowflakeGuidGenerator with the default start epoch and
     * custom worker ID, data centre ID.
     *
     * @param workerId     the worker ID (between 0 and 31).
     * @param dataCentreId the data centre ID (between 0 and 31).
     */
    public SnowflakeGuidCreator(long workerId, long dataCentreId) {
        this(workerId, dataCentreId, DEFAULT_CUSTOM_EPOCH);
    }

    /**
     * Constructs a SnowflakeGuidGenerator with a custom epoch, worker ID, and
     * data centre ID.
     *
     * @param startEpoch   the custom epoch timestamp (in milliseconds) to
     *                     start generating IDs from
     * @param workerId     the worker ID (between 0 and 31)
     * @param dataCentreId the data centre ID (between 0 and 31)
     * @throws IllegalArgumentException if the start epoch is greater than the
     *                                  current timestamp, or if the worker ID
     *                                  or data centre ID is out of range
     */
    public SnowflakeGuidCreator(long workerId, long dataCentreId, long startEpoch) {
        if (startEpoch > currentTimestamp()) {
            throw new IllegalArgumentException("Start Epoch can not be greater than current timestamp!");
        }

        var maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }

        var maxDataCentreId = ~(-1L << dataCentreIdBits);
        if (dataCentreId > maxDataCentreId || dataCentreId < 0) {
            throw new IllegalArgumentException(String.format("Data Centre Id can't be greater than %d or less than 0",
                    maxDataCentreId));
        }

        this.startEpoch = startEpoch;
        this.workerId = workerId;
        this.dataCentreId = dataCentreId;
    }

    /**
     * Generates the next unique ID.
     *
     * @return the generated unique ID
     * @throws TimingException if the system clock moves backwards,
     *                         indicating an invalid sequence of timestamps.
     */
    @Override
    public synchronized Long nextId() {
        var timestamp = currentTimestamp();

        // If the current time is less than the timestamp of the last ID generation, it means that the system clock
        // has been set back and an exception should be thrown.
        if (timestamp < lastTimestamp) {
            throw new TimingException("Clock moved backwards. Refusing to generate id for %d milliseconds"
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
     * Blocks until the next millisecond to obtain a new timestamp.
     *
     * @param lastTimestamp the timestamp when the last ID was generated
     * @return the current timestamp
     */
    private long awaitToNextMillis(long lastTimestamp) {
        var timestamp = currentTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTimestamp();
        }
        return timestamp;
    }

    /**
     * Returns the current timestamp in milliseconds.
     *
     * @return the current timestamp
     */
    private long currentTimestamp() {
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}

