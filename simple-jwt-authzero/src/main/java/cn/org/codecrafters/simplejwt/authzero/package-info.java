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

/**
 * This package contains classes related to the integration of the {@code
 * com.auth0:java-jwt} library in the Simple JWT project. {@code
 * com.auth0:java-jwt} is a powerful and widely-used Identity as a Service
 * (IDaaS) platform that provides secure authentication and authorisation
 * solutions for web and mobile applications. The classes in this package
 * provide the necessary functionality to handle JSON Web Tokens (JWTs) using
 * the {@code com.auth0:java-jwt} library.
 * <p>
 * The main class in this package is the {@link
 * cn.org.codecrafters.simplejwt.authzero.AuthzeroTokenResolver}, which
 * implements the {@link cn.org.codecrafters.simplejwt.TokenResolver} interface
 * and uses the {@code com.auth0:java-jwt} library to handle JWT operations. It
 * provides the functionality to create, validate, and extract JWTs using the
 * {@code com.auth0:java-jwt} library. Developers can use this class as the
 * main token resolver in the Simple JWT project when integrating {@code
 * com.auth0:java-jwt} as the JWT management library.
 * <p>
 * The {@link cn.org.codecrafters.simplejwt.authzero.AuthzeroTokenResolver}
 * relies on the {@code com.auth0:java-jwt} library to handle the underlying
 * JWT operations, including token creation, validation, and extraction. It
 * utilizes the {@code com.auth0:java-jwt} {@link
 * com.auth0.jwt.algorithms.Algorithm} class to define and use different
 * algorithms for JWT signing and verification.
 * <p>
 * To use the {@link
 * cn.org.codecrafters.simplejwt.authzero.AuthzeroTokenResolver}, developers
 * must provide the necessary configurations and dependencies, such as the
 * {@link cn.org.codecrafters.guid.GuidCreator} for generating unique JWT IDs
 * (JTI), the supported algorithm function, the issuer name, and the secret key
 * used for token signing and validation. The {@link
 * cn.org.codecrafters.simplejwt.authzero.config.AuthzeroTokenResolverConfig}
 * class provides a convenient way to configure these dependencies.
 * <p>
 * Developers using the {@code com.auth0:java-jwt} integration should be
 * familiar with the concepts and usage of the {@code com.auth0:java-jwt}
 * library and follow the official {@code com.auth0:java-jwt} documentation for
 * best practices and security considerations.
 *
 * @since 1.0.0
 */
package cn.org.codecrafters.simplejwt.authzero;