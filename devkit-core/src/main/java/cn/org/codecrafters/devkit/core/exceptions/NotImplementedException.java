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

package cn.org.codecrafters.devkit.core.exceptions;

/**
 * {@code NotImplementedException} 异常是一个自定义的运行时环境异常，其表示在一个具体的方法或功能
 * 并未进行实现或者在代码层面不可访问。其继承自 {@code RuntimeException} 异常。
 * <p>
 * 当开发人员需要指示代码的特定部分不完整或需要进一步实现时，通常会引发此异常。 它用作占位符，以突出显示开
 * 发和测试阶段应用程序中未完成的部分。
 * <p>
 * 使用案例:
 * <pre>
 * public void someMethod() {
 *     // Some code...
 *     throw new NotImplementedException("""
 *     This feature will be implemented in a future release.""");
 * }
 * </pre>
 *
 * <b>联系方式</b>
 * <ul>
 *     <li>
 *         <a href="https://github.com/CodeCraftersCN/jdevkit/issues/new"
 *         >GitHub Issues</a>
 *     </li>
 *     <li>
 *         <a href="https://discord.gg/">Discord Community</a>
 *     </li>
 * </ul>
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @see RuntimeException
 * @since 1.0.0
 */
public class NotImplementedException extends RuntimeException {

    /**
     * 创建一个不指定错误信息的 {@code NotImplementedException} 实例
     */
    public NotImplementedException() {
    }

    /**
     * 创建一个带有指定错误信息的 {@code NotImplementedException} 实例。
     *
     * @param message 与此异常相关的错误消息
     */
    public NotImplementedException(String message) {
        super(message);
    }

    /**
     * 创建一个带有指定错误信息和造成原因的 {@code NotImplementedException} 实例。
     *
     * @param message 与此异常相关的错误消息
     * @param cause   该异常发生的原因
     */
    public NotImplementedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 创建一个带有指定造成原因的 {@code NotImplementedException} 实例。
     *
     * @param cause 该异常发生的原因
     */
    public NotImplementedException(Throwable cause) {
        super(cause);
    }

    /**
     * 使用指定的错误消息、原因、抑制标志和堆栈跟踪可写标志创建一个新的 {@code NotImplementedException}。
     *
     * @param message            与此异常相关的错误消息
     * @param cause              该异常发生的原因
     * @param enableSuppression  抑制是启用还是禁用
     * @param writableStackTrace 堆栈跟踪是否可写
     */
    public NotImplementedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
