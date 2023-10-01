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
 * 此软件包包含注解类，用于防止注解属性在令牌生成过程中自动注入 JSON Web 令牌（JWT）有效载荷。
 * 这些注解可应用于数据类的属性，以防止它们被作为 JWT 有效负载的一部分。
 *
 * @since 1.0.0
 */
package cn.org.codecrafters.simplejwt.annotations;