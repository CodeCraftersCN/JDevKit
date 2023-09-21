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
 * 该包包含与 Simple JWT 项目中的 {@code com.auth0:java-jwt} 库的相关类。
 * {@code com.auth0:java-jwt} 是一个功能强大、应用广泛的身份即服务（IDaaS）
 * 平台，为网络和移动应用程序提供安全的身份验证和授权解决方案。
 * 该软件包中的类提供了使用 {@code com.auth0:java-jwt} 库处理 JSON Web
 * 标记（JWT）的必要功能。
 * <p>
 * 该软件包中的主要类是 {@link
 * cn.org.codecrafters.simplejwt.authzero.AuthzeroTokenResolver} ，
 * 它实现了 {@link cn.org.codecrafters.simplejwt.TokenResolver} 接口，
 * 并使用 {@code com.auth0:java-jwt} 库来处理 JWT 操作。
 * 它提供了使用 {@code com.auth0:java-jwt} 库创建、验证和提取 JWT 的功能。
 * 当集成 {@code com.auth0:java-jwt} 作为 JWT 管理库时，开发人员可将该类
 * 用作简单 JWT 项目中的主要令牌解析器。
 * <p>
 * {@link cn.org.codecrafters.simplejwt.authzero.AuthzeroTokenResolver}
 * 依赖于 {@code com.auth0:java-jwt} 库来处理底层 JWT 操作，包括令牌创建、验证和提取。
 * 它利用 {@code com.auth0:java-jwt} {@link com.auth0.jwt.algorithms.Algorithm}
 * 类来定义和使用不同的 JWT 签名和验证算法。
 * <p>
 * 要使用 {@link cn.org.codecrafters.simplejwt.authzero.AuthzeroTokenResolver} ，
 * 开发人员必须提供必要的配置和依赖项，例如用于生成唯一 JWT ID (JTI)的 {@link
 * cn.org.codecrafters.guid.GuidCreator}、支持的算法函数、发行者名称以及用于令牌签名和验证的秘钥。
 * {@link cn.org.codecrafters.simplejwt.authzero.config.AuthzeroTokenResolverConfig}
 * 类提供了配置这些依赖关系的便捷方法。
 * <p>
 * 使用 {@code com.auth0:java-jwt} 集成的开发人员应熟悉
 * {@code com.auth0:java-jwt} 库的概念和用法，并遵循
 * {@code com.auth0:java-jwt} 官方文档中的最佳实践和安全注意事项。
 *
 * @since 1.0.0
 */
package cn.org.codecrafters.simplejwt.authzero;