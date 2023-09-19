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
 * {@code WeakSecretException} 表示您的秘密太弱，不能用于签署 JWT。
 * <p>
 * {@code WeakSecretException} 只有在使用
 * {@code cn.org.codecrafters:simple-jwt-jjwt}实现模块时才会出现，因为其采用了
 * {@code io.jsonwebtoken:jjwt} 进行实现，该第三方类库要求密钥不得的长度不得小于16。
 *
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @since 1.0.0
 */
public class WeakSecretException extends RuntimeException {

    /**
     * 构造一个新的 {@code WeakSecretException} ，其详细信息为 {@code null} 。该原因
     * 未被初始化，随后可通过调用{@link #initCause}进行初始化。
     */
    public WeakSecretException() {
    }

    /**
     * 构造一个新的 {@code WeakSecretException} ，并带有指定的细节信息。原因未初始化，
     * 可通过调用 {@link #initCause} 进行初始化。
     *
     * @param message 详细信息，该信息将被保存起来，以便以后通过 {@link
     *                #getMessage()} 方法进行检索
     */
    public WeakSecretException(String message) {
        super(message);
    }

    /**
     * 构造一个新的 {@code WeakSecretException} ，包含指定的细节信息和原因。
     * <p>
     * 请注意，与 {@code cause} 相关联的详细信息<i>不会</i>自动包含在此运行时异常的详细
     * 信息中。
     *
     * @param message 详细信息，该信息将被保存起来，以便以后通过 {@link
     *                #getMessage()} 方法进行检索
     * @param cause   原因（保存后可通过 {@link #getCause()} 方法检索）。 (允许使用
     *                {@code null} 值，表示原因不存在或未知）。
     * @since 1.0.0
     */
    public WeakSecretException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造一个新的 {@code WeakSecretException}，该异常具有指定的起因和
     * {@code (cause==null ? null : cause.toString())} 的详细信息（通常包含
     * {@code cause} 的类和详细信息）。该构造函数对运行时异常非常有用，因为运行时异常只不过
     * 是其他可抛出异常的包装。
     *
     * @param cause 原因（保存后可通过 {@link #getCause()} 方法检索）。 (允许使用
     *              {@code null} 值，表示原因不存在或未知）。
     * @since 1.0.0
     */
    public WeakSecretException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造一个新的 {@code WeakSecretException}，包含指定的细节信息、原因、启用或禁用的
     * 抑制功能，以及启用或禁用的可写堆栈跟踪功能。
     *
     * @param message            详细信息
     * @param cause              原因（保存后可通过 {@link #getCause()} 方法检索）
     *                           (允许使用 {@code null} 值，表示原因不存在或未知）
     * @param enableSuppression  是否启用或禁用抑制功能
     * @param writableStackTrace 堆栈跟踪是否可写
     * @since 1.0.0
     */
    public WeakSecretException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
