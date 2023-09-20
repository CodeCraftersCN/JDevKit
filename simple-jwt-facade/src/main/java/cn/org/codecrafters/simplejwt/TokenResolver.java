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

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.Map;

/**
 * {@code TokenResolver} 定义了创建、提取和更新令牌（尤其是 JSON 网络令牌）的方法( JWTs )。
 * 它提供了一套方法，用于生成具有各种有效载荷配置的令牌、从令牌中提取有效载荷以及更新过期令牌。
 * <p>
 * <b>Token 创建:</b>
 * 该接口提供了重载方法，用于创建具有不同令牌创建有效载荷配置的令牌，包括过期时间、受众、主题和
 * 自定义有效载荷数据。客户可根据自己的具体令牌需求选择合适的方法。
 * <p>
 * <b>Token 提取:</b>
 * 该接口包括从给定令牌中提取有效载荷信息的方法。客户端可以使用这些方法获取令牌中编码的有效载荷数据。
 * <p>
 * <b>Token 更新:</b>
 * 该接口还提供令牌更新方法。客户端可以通过提供新的过期时间、受众、主题和可选的自定义有效载荷数据来
 * 更新已过期的令牌。
 * @param <ResolvedTokenType> 第三方库在解析 JWT 时获得的结果的类型
 * @author Zihlu Wang
 *         Zitai Long
 *
 * @version 1.1.0
 * @since 1.0.0
 */
public interface TokenResolver<ResolvedTokenType> {

    /**
     * 创建一个新令牌，并指定有效期、主题和受众。
     *
     * @param expireAfter 令牌过期的时间
     * @param subject     标记的主体
     * @param audience    令牌面向的目标受众
     * @return 生成的令牌作为 {@code String}
     */
    String createToken(Duration expireAfter, String audience, String subject);

    /**
     * 创建一个新令牌，其中包含指定的过期时间、主题、受众和自定义有效载荷数据。
     *
     * @param expireAfter 令牌过期的时间
     * @param subject     标记的主体
     * @param audience    令牌面向的目标受众
     * @param payload     将包含在令牌中的自定义有效载荷数据
     * @return 生成的令牌作为 {@code String}
     */
    String createToken(Duration expireAfter, String audience, String subject, Map<String, Object> payload);

    /**
     * 创建一个新令牌，并指定有效期、主题、受众和强类型有效载荷数据。
     *
     * @param <T>         有效载荷数据类型，必须实现
     *                    {@link TokenPayload}
     * @param expireAfter 令牌过期的时间
     * @param subject     标记的主体
     * @param audience    令牌面向的目标受众
     * @param payload     令牌中包含的强类型有效载荷数据
     * @return 生成的令牌作为 {@code String}
     */
    <T extends TokenPayload> String createToken(Duration expireAfter, String audience, String subject, T payload);

    /**
     * 将给定的令牌解析为 ResolvedTokenType 对象。
     *
     * @param token 要解析的标记
     * @return 一个 ResolvedTokenType 对象
     */
    ResolvedTokenType resolve(String token);

    /**
     * 从给定标记中提取有效载荷信息，并将其映射到指定的目标类型。
     *
     * @param <T>        将映射有效载荷数据的目标类型
     * @param token      从中提取有效负载的令牌
     * @param targetType 代表有效载荷数据类型的目标类
     * @return           指定目标类型的实例，其中包含提取的有效载荷数据
     */
    <T extends TokenPayload> T extract(String token, Class<T> targetType);

    /**
     * 使用指定的自定义有效载荷数据更新给定的过期令牌。
     *
     * @param oldToken    要续期的过期令牌
     * @param expireAfter 指定新令牌何时失效
     * @param payload     更新令牌中包含的自定义有效载荷数据
     * @return 更新后的令牌作为 {@code String}
     */
    String renew(String oldToken, Duration expireAfter, Map<String, Object> payload);

    /**
     * 使用指定的自定义有效载荷数据更新给定的过期令牌。
     *
     * @param oldToken 要更新的过期令牌
     * @param payload  将包含在已更新令牌中的自定义有效载荷数据
     * @return 更新后的令牌作为 {@code String}
     */
    String renew(String oldToken, Map<String, Object> payload);

    /**
     * 使用指定的强类型有效载荷数据更新给定的过期令牌。
     *
     * @param <T>         有效载荷数据类型，必须实现
     *                    {@link TokenPayload}
     * @param oldToken    要续期的过期令牌
     * @param expireAfter 指定新令牌何时失效
     * @param payload     续期令牌中将包含的强类型有效载荷数据
     * @return 更新后的令牌作为 {@code String}
     */
    <T extends TokenPayload> String renew(String oldToken, Duration expireAfter, T payload);

    /**
     * 使用指定的强类型有效载荷数据更新给定的过期令牌。
     *
     * @param <T>      有效载荷数据类型，必须实现
     *                 {@link TokenPayload}
     * @param oldToken 要续期的过期令牌
     * @param payload 续期令牌中将包含的强类型有效载荷数据
     *
     * @return 更新后的令牌作为 {@code String}
     */
    <T extends TokenPayload> String renew(String oldToken, T payload);

}
