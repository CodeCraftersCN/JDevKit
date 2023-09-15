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
 * The TimingException class represents an exception that is thrown when there
 * is an error related to time sequence.
 * <p>
 * This class extends the RuntimeException class, which means that instances of
 * TimingException do not need to be declared in a method or constructor's
 * throws clause.
 * <p>
 * Instances of TimingException can be created with or without a message and a
 * cause. The message provides a description of the exception, while the cause
 * represents the underlying cause of the exception and provides additional
 * information about the error.
 * <p>
 * TimingException is typically used to handle exceptions related to timing,
 * such as timeouts or synchronisation issues. It is a subclass of
 * RuntimeException, which means it is an unchecked exception and does not need
 * to be caught or declared.
 *
 * @author Zihlu Wang
 * @since 1.0.0
 */
public class TimingException extends RuntimeException {

    /**
     * A custom exception that is thrown when there is an issue with timing or
     * scheduling.
     */
    public TimingException() {
    }

    /**
     * A custom exception that is thrown when there is an issue with timing or
     * scheduling with customized error message.
     *
     * @param message customized message
     */
    public TimingException(String message) {
        super(message);
    }

    /**
     * A custom exception that is thrown when there is an issue with timing or
     * scheduling with customized error message.
     *
     * @param message customized message
     * @param cause   the cause of this exception
     */
    public TimingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * A custom exception that is thrown when there is an issue with timing or
     * scheduling with customized error message.
     *
     * @param cause the cause of this exception
     */
    public TimingException(Throwable cause) {
        super(cause);
    }
}
