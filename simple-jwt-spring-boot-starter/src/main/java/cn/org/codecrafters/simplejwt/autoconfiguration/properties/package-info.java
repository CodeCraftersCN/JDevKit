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
 * The "cn.org.codecrafters.simplejwt.autoconfiguration.properties" package
 * contains configuration properties classes used for Simple JWT library
 * autoconfiguration. These classes define the properties that can be
 * configured in the application's properties file (e.g.,
 * application.properties) to customize the behavior and settings of the Simple
 * JWT library.
 * <p>
 * Developers can customize the JWT algorithm, issuer, and secret by setting
 * the corresponding properties in the application's properties file with the
 * prefix "code-crafters.simple-jwt". The SimpleJwtAutoConfiguration class
 * reads these properties and uses them to create the {@link
 * cn.org.codecrafters.simplejwt.TokenResolver} bean with the desired
 * configuration.
 *
 * @since 1.0.0
 */
package cn.org.codecrafters.simplejwt.autoconfiguration.properties;