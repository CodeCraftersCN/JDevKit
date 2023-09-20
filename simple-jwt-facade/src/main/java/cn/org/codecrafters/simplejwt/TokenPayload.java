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

package cn.org.codecrafters.simplejwt;

import java.util.Map;

/**
 * {@code TokenPayload}
 * 接口用于将数据类标记为适合用作 JSON 网络令牌（JWT）的有效载荷。任何实现该接口的
 * 类都可以用来表示 JWT 中的有效载荷数据。
 * <p>
 * 实现该接口表明数据类包含需要作为 JWT 的一部分进行安全传输和验证的信息。有效载荷
 * 通常包含提供有关 JWT 主题或上下文的附加信息的声明或属性。
 * <p>
 * <b>使用方法:</b>
 * 要将一个类用作 JWT 有效载荷，只需实现 {@code TokenPayload}
 * 数据类中的接口:
 * <pre>
 * public class UserData implements TokenPayload {
 *     // Class implementation with payload data...
 * }
 * </pre>
 *
 * @author Zihlu Wang
 *         Zitai Long
 * @version 1.1.0
 * @since 1.0.0
 */
public interface TokenPayload {

    // Marker interface for JWT payload data classes

}
