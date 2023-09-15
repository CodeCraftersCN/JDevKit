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
 * The {@code TokenAlgorithm} enum class defines the algorithms that can be
 * used for signing and verifying JSON Web Tokens (JWT). JWT allows various
 * cryptographic algorithms to be used for secure token generation and
 * validation. This enum provides a list of supported algorithms to ensure
 * consistent usage and avoid potential security issues.
 * <p><b>Supported Algorithms:</b>
 * This enum includes the following supported algorithms:
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
