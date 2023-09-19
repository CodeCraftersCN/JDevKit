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

package cn.org.codecrafters.simplejwt;

import java.util.Map;

/**
 * {@code TokenPayload} interface is used to mark a data class as suitable
 * for being used as the payload in a JSON Web Token (JWT). Any class
 * implementing this interface can be used to represent the payload data that
 * will be included in a JWT.
 * <p>
 * Implementing this interface indicates that the data class contains
 * information that needs to be securely transmitted and verified as part of a
 * JWT. The payload typically contains claims or attributes that provide
 * additional information about the JWT subject or context.
 * <p>
 * <b>Usage:</b>
 * To use a class as a JWT payload, simply implement the {@code TokenPayload}
 * interface in the data class:
 * <pre>
 * public class UserData implements TokenPayload {
 *     // Class implementation with payload data...
 * }
 * </pre>
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public interface TokenPayload {

    // Marker interface for JWT payload data classes

}
