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

import java.util.List;

/**
 * The {@code PredefinedKeys} class contains constants for standard JSON Web Token (JWT) claims. These constants
 * represent the names of the standard claims that can be included in a JWT payload. Developers can use these constants
 * when working with JWTs to ensure consistent naming of the claims.
 * <p>
 * The class provides the following standard JWT claim constants:
 * <ul>
 *     <li>{@link #ISSUER}: Represents the "iss" (Issuer) claim.</li>
 *     <li>{@link #SUBJECT}: Represents the "sub" (Subject) claim.</li>
 *     <li>{@link #AUDIENCE}: Represents the "aud" (Audience) claim.</li>
 *     <li>{@link #EXPIRATION_TIME}: Represents the "exp" (Expiration Time) claim.</li>
 *     <li>{@link #NOT_BEFORE}: Represents the "nbf" (Not Before) claim.</li>
 *     <li>{@link #ISSUED_AT}: Represents the "iat" (Issued At) claim.</li>
 *     <li>{@link #JWT_ID}: Represents the "jti" (JWT ID) claim.</li>
 * </ul>
 * <p>
 * The class also contains a list of all the standard claim constants, accessible via the {@link #KEYS} field. This
 * list can be useful for iterating through all the standard claims or checking for the presence of specific claims.
 * <p>
 * Note: This class is final and cannot be instantiated. It only serves as a utility class to hold the standard JWT
 * claim constants.
 *
 * @version 1.1.0
 * @since 1.0.0
 */
public final class PredefinedKeys {

    /**
     * Constant representing the "iss" (Issuer) claim in a JWT payload.
     */
    public static final String ISSUER = "iss";

    /**
     * Constant representing the "sub" (Subject) claim in a JWT payload.
     */
    public static final String SUBJECT = "sub";

    /**
     * Constant representing the "aud" (Audience) claim in a JWT payload.
     */
    public static final String AUDIENCE = "aud";

    /**
     * Constant representing the "exp" (Expiration Time) claim in a JWT payload.
     */
    public static final String EXPIRATION_TIME = "exp";

    /**
     * Constant representing the "nbf" (Not Before) claim in a JWT payload.
     */
    public static final String NOT_BEFORE = "nbf";

    /**
     * Constant representing the "iat" (Issued At) claim in a JWT payload.
     */
    public static final String ISSUED_AT = "iat";

    /**
     * Constant representing the "jti" (JWT ID) claim in a JWT payload.
     */
    public static final String JWT_ID = "jti";

    /**
     * List containing all the standard JWT claim constants.
     */
    public static final List<String> KEYS = List.of(ISSUER, SUBJECT, AUDIENCE, EXPIRATION_TIME, NOT_BEFORE, ISSUED_AT, JWT_ID);

    /**
     * Private constructor to prevent instantiation of the {@code PredefinedKeys} class.
     * This class is intended to be used as a utility class with only static constants and methods.
     */
    private PredefinedKeys() {
        // Private constructor to prevent instantiation
    }
}

