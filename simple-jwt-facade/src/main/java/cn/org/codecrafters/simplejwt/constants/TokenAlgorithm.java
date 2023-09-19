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

import lombok.Getter;

/**
 * {@code TokenAlgorithm} 枚举类定义了可用于签署和验证 JSON Web 标记（JWT）的算法。
 * JWT 允许使用各种加密算法来安全生成和验证令牌。该枚举提供了一个受支持算法的列表，以确保使
 * 用的一致性并避免潜在的安全问题。
 * <p>
 * <b>支持的算法</b>
 * <ul>
 *     <li>{@link TokenAlgorithm#HS256}: HMAC SHA-256</li>
 *     <li>{@link TokenAlgorithm#HS384}: HMAC SHA-384</li>
 *     <li>{@link TokenAlgorithm#HS512}: HMAC SHA-512</li>
 *     <li>{@link TokenAlgorithm#RS256}: RSA PKCS#1 v1.5 with SHA-256</li>
 *     <li>{@link TokenAlgorithm#RS384}: RSA PKCS#1 v1.5 with SHA-384</li>
 *     <li>{@link TokenAlgorithm#RS512}: RSA PKCS#1 v1.5 with SHA-512</li>
 *     <li>{@link TokenAlgorithm#ES256}: ECDSA with SHA-256</li>
 *     <li>{@link TokenAlgorithm#ES384}: ECDSA with SHA-384</li>
 *     <li>{@link TokenAlgorithm#ES512}: ECDSA with SHA-512</li>
 * </ul>
 *
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @since 1.0.0
 */
@Getter
public enum TokenAlgorithm {

    /**
     * HMAC using SHA-256
     */
    HS256,

    /**
     * HMAC using SHA-384
     */
    HS384,

    /**
     * HMAC using SHA-512
     */
    HS512,

    /**
     * RSASSA-PKCS-v1_5 using SHA-256
     */
    RS256,

    /**
     * RSASSA-PKCS-v1_5 using SHA-384
     */
    RS384,

    /**
     * RSASSA-PKCS-v1_5 using SHA-512
     */
    RS512,

    /**
     * ECDSA using P-256 and SHA-256
     */
    ES256,

    /**
     * ECDSA using P-384 and SHA-384
     */
    ES384,

    /**
     * ECDSA using P-521 and SHA-512
     */
    ES512,
    ;

}
