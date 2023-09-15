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
 * This package contains the custom exception classes related to GUID
 * generation. These exceptions are thrown when there are issues or errors
 * during the generation or processing of global unique identifiers (GUIDs).
 * <p>
 * The main exception class in this package is {@link
 * cn.org.codecrafters.guid.exceptions.TimingException}, which is a runtime
 * exception and serves as the base exception for all other custom exceptions
 * related to GUID generation.
 * <p>
 * Custom exceptions in this package provide specific information about the
 * type of error that occurred during GUID generation, making it easier for
 * developers to handle and respond to different scenarios when dealing with
 * GUIDs. They are designed to enhance the robustness and reliability of the
 * GUID generation process by providing clear and meaningful error messages to
 * the developers.
 * <p>
 * Developers using the GUID generation module should be aware of the possible
 * exceptions that can be thrown and handle them appropriately to ensure smooth
 * operation and error handling in their applications.
 *
 * @since 1.0.0
 */
package cn.org.codecrafters.guid.exceptions;