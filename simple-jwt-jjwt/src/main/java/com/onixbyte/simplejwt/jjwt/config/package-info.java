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
 * The package {@code cn.org.codecrafters.simplejwt.jjwt.config} contains
 * configuration classes related to the {@link
 * com.onixbyte.simplejwt.jjwt.JjwtTokenResolver}
 * implementation.
 * <p>
 * The classes in this package provide configuration options and settings for
 * the {@link com.onixbyte.simplejwt.jjwt.JjwtTokenResolver},
 * which is used for resolving JSON Web Tokens (JWT) using the Auth0 library.
 * <p>
 * The {@link
 * com.onixbyte.simplejwt.jjwt.config.JjwtTokenResolverConfig}
 * class is a configuration class that defines the mapping between standard
 * {@link com.onixbyte.simplejwt.constants.TokenAlgorithm} and the
 * corresponding function implementation used by {@link
 * com.onixbyte.simplejwt.jjwt.JjwtTokenResolver} for handling
 * JWT algorithms. It enables developers to specify and customize the
 * algorithm functions according to the chosen JWT algorithm and the library
 * being used.
 * <p>
 * The configuration options in this package help developers integrate and
 * configure the {@link
 * com.onixbyte.simplejwt.jjwt.JjwtTokenResolver} seamlessly
 * into their Spring Boot applications. Developers can fine-tune the token
 * resolution process and customize algorithm handling to align with their
 * specific requirements and desired level of security.
 * <p>
 * It is recommended to explore the classes in this package to understand how
 * to configure and use the {@link
 * com.onixbyte.simplejwt.jjwt.JjwtTokenResolver} effectively
 * in the Spring Boot environment to handle JWT authentication and
 * authorisation securely and efficiently.
 *
 * @since 1.0.0
 */
package com.onixbyte.simplejwt.jjwt.config;