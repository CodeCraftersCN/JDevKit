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

package cn.org.codecrafters.simplejwt.exceptions;

/**
 * {@code WeakSecretException} represents that your secret is too weak to be
 * used in signing JWTs.
 * <p>
 * {@code WeakSecretException} will only appears that if you are using the
 * implementation module {@code cn.org.codecrafters:simple-jwt-jjwt} due to
 * it is implemented by {@code io.jsonwebtoken:jjwt}.
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public class WeakSecretException extends RuntimeException {

    /**
     * Constructs a new {@code WeakSecretException} with {@code null} as its
     * detail message. The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public WeakSecretException() {
    }

    /**
     * Constructs a new {@code WeakSecretException} with the specified detail
     * message. The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public WeakSecretException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code WeakSecretException} with the specified detail
     * message and cause.
     * <p>
     * Note that the detail message associated with {@code cause} is <i>not</i>
     * automatically incorporated in this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.0.0
     */
    public WeakSecretException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code WeakSecretException} with the specified cause
     * and a detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}). This constructor is useful for runtime exceptions that
     * are little more than wrappers for other throwable.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.0.0
     */
    public WeakSecretException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new {@code WeakSecretException} with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause. (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or
     *                           unknown.)
     * @param enableSuppression  whether suppression is enabled or disabled
     * @param writableStackTrace whether the stack trace should be writable
     * @since 1.0.0
     */
    public WeakSecretException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
