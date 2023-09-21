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
 * {@code UnsupportedAlgorithmException} 表示 {@link
 * cn.org.codecrafters.simplejwt.TokenResolver} 尚未支持给定的算法。
 * <p>
 * 如果您想让该算法得到支持，还请您耐心等候，或者联系我们。
 *
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @since 1.0.0
 */
public class UnsupportedAlgorithmException extends RuntimeException {

    /**
     * 构造一个新的 {@code UnsupportedAlgorithmException}，其详细信息为{@code null}。
     * 该原因未被初始化，随后可通过调用{@link #initCause}进行初始化。
     */
    public UnsupportedAlgorithmException() {
    }

    /**
     * 构造一个新的{@code UnsupportedAlgorithmException}，并带有指定的细节信息。
     * 该原因未被初始化，可通过调用{@link #initCause}进行初始化。
     *
     * @param message 详细信息。详细消息将被保存，
     *                以便以后通过 {@link #getMessage()} 方法进行检索。
     */
    public UnsupportedAlgorithmException(String message) {
        super(message);
    }

    /**
     * 构造一个新的 {@code UnsupportedAlgorithmException} ，包含指定的详细信息和原因。
     *
     * @param message 详细消息（该消息将被保存，以便以后通过
     *                {@link #getMessage()} 方法进行检索）。
     * @param cause   错误原因（该错误原因会被保存起来，以便以后通过
     *                {@link #getCause()} 方法进行检索）。
     *                (允许使用 {@code null} 值，表示原因不存在或未知）。
     * @since 1.4
     */
    public UnsupportedAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造一个新的 {@code UnsupportedAlgorithmException}，
     * 包含指定的错误原因和 {@code (cause==null ? null : cause.toString())} 的详细信息
     * （通常包含 {@code cause} 的类和详细信息）。
     * 该构造函数适用于运行时异常，这些异常只是其他可抛出异常的封装器。
     *
     * @param cause 错误原因（该起因会被保存起来，以便以后通过
     *              {@link #getCause()} 方法进行检索）。
     *              (允许使用 {@code null} 值，表示原因不存在或未知）。
     * @since 1.4
     */
    public UnsupportedAlgorithmException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造一个新的 {@code UnsupportedAlgorithmException} ，包含
     * 指定的详细消息、原因、已启用或已禁用的抑制功能，以及已启用或已禁用的可写堆栈跟踪功能。
     *
     * @param message            详细信息。
     * @param cause              原因（允许使用 {@code null} 值，表示原因不存在或未知）。
     * @param enableSuppression  是否启用或禁用抑制功能
     * @param writableStackTrace 堆栈跟踪是否可写
     * @since 1.0.0
     */
    public UnsupportedAlgorithmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
