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
 * 注解 {@code ExcludeFromPayload} 用于标记数据类的一个属性，该属性应在令牌生成过程中被自动
 * 注入到 JSON Web Token（JWT）有效负载中。使用此注解的属性将不会包含在 JWT 有效负载中。
 * <p>
 * <b>使用方法:</b>
 * 要从 JWT 有效负载中排除某个属性，请将该注释 {@code @ExcludeFromPayload} 添加到目标字段上：
 *
 * <pre>{@code
 * public class UserData implements TokenPayload {
 *     private String username;
 *
 *     // 这个属性将不会包含在 JWT 有效载荷中
 *     @ExcludeFromPayload
 *     private String sensitiveData;
 *
 *     // Getters and setters...
 * }
 * }</pre>
 * <p>
 * <b>注意事项：</b>
 * 此注释应用于因敏感性质或其他原因而不打算包含在 JWT 有效负载中的属性。必须仔细选择哪些属性不包括
 * 在有效负载中，以确保 JWT 保持安全和高效。
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcludeFromPayload {
}
