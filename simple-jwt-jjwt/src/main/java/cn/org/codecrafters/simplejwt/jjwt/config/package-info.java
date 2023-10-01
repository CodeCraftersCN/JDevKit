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
 * 包 {@code cn.org.codecrafters.simplejwt.jjwt.config} 包含
 * 与 {@link cn.org.codecrafters.simplejwt.jjwt.JjwtTokenResolver} 实现相关的配置类。
 *
 * <p>
 * 该软件包中的类为 {@link cn.org.codecrafters.simplejwt.jjwt.JjwtTokenResolver}
 * 提供了配置选项和设置，后者用于使用 Auth0 库解析 JSON Web 标记（JWT）。
 * <p>
 * {@link cn.org.codecrafters.simplejwt.jjwt.config.JjwtTokenResolverConfig}
 * 类是一个配置类，它定义了标准 {@link cn.org.codecrafters.simplejwt.constants.TokenAlgorithm}
 * 与 {@link cn.org.codecrafters.simplejwt.jjwt.JjwtTokenResolver}
 * 用于处理 JWT 算法的相应函数实现之间的映射。它使开发人员能够根据所选的 JWT 算法和所使用的库指定和自定义算法函数。
 * <p>
 * 此软件包中的配置选项可帮助开发人员将 {@link
 * cn.org.codecrafters.simplejwt.jjwt.JjwtTokenResolver}
 * 无缝集成并配置到 Spring Boot 应用程序中。开发人员可以微调令牌解析过程并自定义算法处理，
 * 以满足其特定要求和所需的安全级别。
 * <p>
 * 建议探索此软件包中的类，以了解如何在 Spring Boot 环境中有效配置和使用
 * {@link cn.org.codecrafters.simplejwt.jjwt.JjwtTokenResolver} ，
 * 从而安全高效地处理 JWT 身份验证和授权。
 *
 * @since 1.0.0
 */
package cn.org.codecrafters.simplejwt.jjwt.config;