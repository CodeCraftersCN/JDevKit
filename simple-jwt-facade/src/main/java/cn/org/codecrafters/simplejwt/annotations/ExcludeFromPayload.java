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

package cn.org.codecrafters.simplejwt.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * This annotation is used to mark a property of a data class that should be
 * excluded from being automatically injected into the JSON Web Token (JWT)
 * payload during token generation. When a property is annotated with this
 * annotation, it will not be included as part of the JWT payload.
 * <p>
 * <b>Usage:</b>
 * To exclude a property from the JWT payload, annotate the property with
 * {@code @ExcludeFromPayload}:
 *
 * <pre>{@code
 * public class UserData implements TokenPayload {
 *     private String username;
 *
 *     // This property will not be included in the JWT payload
 *     @ExcludeFromPayload
 *     private String sensitiveData;
 *
 *     // Getters and setters...
 * }
 * }</pre>
 * <p>
 * <b>Note:</b>
 * This annotation should be used only on properties that are not intended to
 * be included in the JWT payload due to their sensitive nature or for other
 * reasons. It is important to carefully choose which properties are excluded
 * from the payload to ensure the JWT remains secure and efficient.
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcludeFromPayload {
}
