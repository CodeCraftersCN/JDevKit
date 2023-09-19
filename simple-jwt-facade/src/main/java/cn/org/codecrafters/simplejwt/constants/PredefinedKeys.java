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

package cn.org.codecrafters.simplejwt.constants;

import java.util.List;

/**
 * {@code PredefinedKeys} 类包含标准 JSON 网络令牌（JWT）声明的常量。这些常量表示可包含在
 * JWT 有效负载中的标准声明名称。开发人员在使用 JWT 时可以使用这些常量，以确保声明命名的一致性。
 * <p>
 * 该类提供以下标准 JWT 声明的常量：
 * <ul>
 *     <li>{@link #ISSUER}: 代表 "iss"（签发者）声明；</li>
 *     <li>{@link #SUBJECT}: 代表 “sub” （Token 主题）声明；</li>
 *     <li>{@link #AUDIENCE}: 代表 “aud” （听众）声明；</li>
 *     <li>{@link #EXPIRATION_TIME}: 代表 “exp” （过期时间）声明；</li>
 *     <li>{@link #NOT_BEFORE}: 代表 “nbf” （不早于）声明；</li>
 *     <li>{@link #ISSUED_AT}: 代表 “iat” （签发时间）声明；</li>
 *     <li>{@link #JWT_ID}: 代表 “jti” （JWT ID） 声明。</li>
 * </ul>
 * <p>
 * 该类还包含一个所有标准权利要求常量的列表，可通过 {@link #KEYS} 字段访问。该列表可用于遍历所
 * 有标准声明或检查是否存在特定声明。
 *
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @since 1.0.0
 */
public final class PredefinedKeys {

    /**
     * 常量，代表 JWT 有效负载中的 "iss"（发行方）声明。
     */
    public static final String ISSUER = "iss";

    /**
     * 常量，代表 JWT 有效负载中的 "sub"（主题）声明。
     */
    public static final String SUBJECT = "sub";

    /**
     * 常量，代表 JWT 有效负载中的 "aud"（受众）声明。
     */
    public static final String AUDIENCE = "aud";

    /**
     * 常量，代表 JWT 有效负载中的 "exp"（过期时间）声明。
     */
    public static final String EXPIRATION_TIME = "exp";

    /**
     * 表示 JWT 有效负载中 "nbf"（不早于/生效时间）声明的常量。
     */
    public static final String NOT_BEFORE = "nbf";

    /**
     * 表示 JWT 有效负载中 "iat"（签发时间）声明的常量。
     */
    public static final String ISSUED_AT = "iat";

    /**
     * 常量，代表 JWT 有效负载中的 "jti"（JWT ID）声明。
     */
    public static final String JWT_ID = "jti";

    /**
     * 包含所有标准 JWT 声明常量的列表。
     */
    public static final List<String> KEYS = List.of(ISSUER, SUBJECT, AUDIENCE, EXPIRATION_TIME, NOT_BEFORE, ISSUED_AT, JWT_ID);

    /**
     * 私有构造函数，用于防止 {@code PredefinedKeys} 类实例化。
     * <p>
     * 该类旨在作为一个只包含静态常量和方法的实用类。
     */
    private PredefinedKeys() {
    }
}

