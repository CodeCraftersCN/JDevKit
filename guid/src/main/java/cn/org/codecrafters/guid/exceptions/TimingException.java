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

package cn.org.codecrafters.guid.exceptions;

/**
 * {@link TimingException} 类表示出现与时间顺序相关的错误时引发的异常。
 * <p>
 * {@link TimingException} 的实例可以在有或没有消息和原因的情况下创建。该消息提供异常的描述，
 * 而原因表示异常的根本原因并提供有关错误的附加信息。
 *
 * @author Zihlu Wang
 * @since 1.0.0
 */
public class TimingException extends RuntimeException {

    /**
     * 当时间或调度出现问题时抛出的自定义异常。
     */
    public TimingException() {
    }

    /**
     * 当时间或调度出现问题时抛出的自定义带有错误信息的异常。
     *
     * @param message 自定义错误信息
     */
    public TimingException(String message) {
        super(message);
    }

    /**
     * 当时间或调度出现问题时抛出的自定义带有错误信息和造成原因的异常。
     *
     * @param message 自定义的错误信息
     * @param cause   造成这种异常的原因
     */
    public TimingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 当定时或调度出现问题时抛出的自定义异常，带有指定的错误原因。
     *
     * @param cause 造成这种异常的原因
     */
    public TimingException(Throwable cause) {
        super(cause);
    }
}
