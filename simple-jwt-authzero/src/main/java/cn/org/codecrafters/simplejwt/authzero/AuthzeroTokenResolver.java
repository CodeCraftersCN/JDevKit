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

package cn.org.codecrafters.simplejwt.authzero;

import cn.org.codecrafters.guid.GuidCreator;
import cn.org.codecrafters.simplejwt.SecretCreator;
import cn.org.codecrafters.simplejwt.TokenPayload;
import cn.org.codecrafters.simplejwt.TokenResolver;
import cn.org.codecrafters.simplejwt.annotations.ExcludeFromPayload;
import cn.org.codecrafters.simplejwt.authzero.config.AuthzeroTokenResolverConfig;
import cn.org.codecrafters.simplejwt.config.TokenResolverConfig;
import cn.org.codecrafters.simplejwt.constants.TokenAlgorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * 该类 {@code AuthzeroTokenResolver} 实现了 {@link
 * cn.org.codecrafters.simplejwt.TokenResolver} 接口. 它用了 {@code
 * com.auth0:java-jwt} 库来处理 JSON 网络令牌 (JWT) 解析。
 * 该解析器提供使用各种算法和自定义有效载荷数据创建、提取、验证和更新 JWT 标记的功能。
 * <p>
 * <b>依赖:</b>
 * 该实现依赖于 {@code com.auth0:java-jwt} 库。
 * 在使用此解析器之前，请确保已将此库作为依赖项添加到您的项目中。
 * <p>
 * <b>使用方法:</b>
 * 要使用该 {@code AuthzeroTokenResolver} 功能，首先要创建一个类:
 * <pre>{@code
 * TokenResolver<DecodedJWT> tokenResolver =
 *     new AuthzeroTokenResolver(TokenAlgorithm.HS256,
 *                               "Token Subject",
 *                               "Token Issuer",
 *                               "Token Secret");
 * }</pre>
 * <p>
 * 然后，您就可以利用该解析器提供的各种方法来处理 JWT 标记：
 * <pre>{@code
 * // 创建新的 JWT 标记
 * String token =
 *     tokenResolver.createToken(Duration.ofHours(1),
 *                               "your_subject",
 *                               "your_audience",
 *                               customPayloads);
 *
 * // 从 JWT 令牌中提取有效载荷数据
 * DecodedJWT decodedJWT = tokenResolver.resolve(token);
 * T payloadData = decodedJWT.extract(token, T.class);
 *
 * // 更新现有的 JWT 令牌
 * String renewedToken =
 *     tokenResolver.renew(token, Duration.ofMinutes(30), customPayloads);
 * }</pre>
 * <p>
 * <b>备注:</b>
 * 使用该解析器时，必须根据具体使用情况配置适当的算法、密文和签发器。
 * 此外，请确保在项目的依赖项中正确配置了该 {@code com.auth0:java-jwt} 库。
 *
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @see GuidCreator
 * @see Algorithm
 * @see JWTVerifier
 * @see JWTCreator
 * @see JWTCreator.Builder
 * @since 1.0.0
 */
@Slf4j
public class AuthzeroTokenResolver implements TokenResolver<DecodedJWT> {

    /**
     * 用于为 JWT 标记中的 "jti" 声明生成唯一标识符的 GuidCreator。
     */
    private final GuidCreator<?> jtiCreator;

    /**
     * 用于签署和验证 JWT 标记的算法。
     */
    private final Algorithm algorithm;

    /**
     * 将包含在 JWT 标记中的发行者声明值。
     */
    private final String issuer;

    /**
     * JSON 网络令牌解析器。
     */
    private final JWTVerifier verifier;

    private final AuthzeroTokenResolverConfig config = AuthzeroTokenResolverConfig.getInstance();

    /**
     * 使用提供的配置创建一个新 {@code AuthzeroTokenResolver} 实例。
     *
     * @param jtiCreator 这个 {@link GuidCreator} 用于为 JWT 标记中的 "jti" 请求生成唯一标识符
     * @param algorithm  用于签署和验证 JWT 标记的算法
     * @param issuer     将包含在 JWT 标记中的发行人声明值
     * @param secret     基于 HMAC 算法（HS256、HS384、HS512）的密文，用于令牌签名和验证
     */
    public AuthzeroTokenResolver(GuidCreator<?> jtiCreator, TokenAlgorithm algorithm, String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("创建 JSON 网络令牌需要一个密文。");
        }

        if (secret.length() <= 32) {
            log.warn("所提供的字符密文 {} 太短。请考虑将其更换为更长的。", secret.length());
        }

        this.jtiCreator = jtiCreator;
        this.algorithm = config
                .getAlgorithm(algorithm)
                .apply(secret);
        this.issuer = issuer;
        this.verifier = JWT.require(this.algorithm).build();
    }

    /**
     * 使用提供的配置和简单 UUID GuidCreator 创建一个 {@link AuthzeroTokenResolver}  新实例。
     *
     * @param algorithm 用于签署和验证 JWT 标记的算法
     * @param issuer    将包含在 JWT 标记中的发行人声明值
     * @param secret    基于 HMAC 算法（HS256、HS384、HS512）的密文，用于令牌签名和验证
     */
    public AuthzeroTokenResolver(TokenAlgorithm algorithm, String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("创建 JSON 网络令牌需要一个密文。");
        }

        if (secret.length() <= 32) {
            log.warn("所提供的字符密文 {} 太短。请考虑将其更换为更长的。", secret.length());
        }

        this.jtiCreator = UUID::randomUUID;
        this.algorithm = config
                .getAlgorithm(algorithm)
                .apply(secret);
        this.issuer = issuer;
        this.verifier = JWT.require(this.algorithm).build();
    }

    /**
     * 使用提供的配置、HMAC256 算法和简单 UUID GuidCreator
     * 创建一个 {@link AuthzeroTokenResolver} 新实例。
     *
     * @param issuer 将包含在 JWT 标记中的发行人声明值
     * @param secret 基于 HMAC 算法（HS256、HS384、HS512）的密文，用于令牌签名和验证
     */
    public AuthzeroTokenResolver(String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("创建 JSON 网络令牌需要一个密文。");
        }

        if (secret.length() <= 32) {
            log.warn("所提供的字符密文 {} 太短。请考虑将其更换为更长的。", secret.length());
        }

        this.jtiCreator = UUID::randomUUID;
        this.algorithm = config
                .getAlgorithm(TokenAlgorithm.HS256)
                .apply(secret);
        this.issuer = issuer;
        this.verifier = JWT.require(this.algorithm).build();
    }

    /**
     * 使用提供的配置、HMAC256 算法和简单 UUID GuidCreator
     * 创建一个 {@link AuthzeroTokenResolver} 新实例。
     *
     * @param issuer 将包含在 JWT 标记中的发行人声明值
     */
    public AuthzeroTokenResolver(String issuer) {
        var secret = SecretCreator.createSecret(32, true, true, true);

        this.jtiCreator = UUID::randomUUID;
        this.algorithm = config
                .getAlgorithm(TokenAlgorithm.HS256)
                .apply(secret);
        this.issuer = issuer;
        this.verifier = JWT.require(this.algorithm).build();

        log.info("该密文已被设置为 {}。", secret);
    }

    /**
     * 使用提供的参数构建 JSON 网络令牌 (JWT) 的基本信息，并将其添加到 JWTCreator.Builder 中。
     *
     * @param subject     将包含在 JWT 中的标的声明值
     * @param audience    一个将包含在 JWT 中的受众声明值数组
     * @param expireAfter JWT 将过期的持续时间
     * @param builder     将添加基本信息的 JWTCreator.Builder 实例
     */
    private void buildBasicInfo(JWTCreator.Builder builder, Duration expireAfter, String subject, String... audience) {
        var now = LocalDateTime.now();

        // bind issuer (iss)
        builder.withIssuer(issuer);
        // bind issued at (iat)
        builder.withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
        // bind not before (nbf)
        builder.withNotBefore(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
        // bind audience (aud)
        builder.withAudience(audience);
        // bind subject (sub)
        builder.withSubject(subject);
        // bind expire at (exp)
        builder.withExpiresAt(Date.from(now.plus(expireAfter).atZone(ZoneId.systemDefault()).toInstant()));
        // bind JWT Id (jti)
        builder.withJWTId(jtiCreator.nextId().toString());
    }

    /**
     * 向构造器商添加声明。
     *
     * @param builder 构建器来构建该 JSON 网络令牌
     * @param name    属性名
     * @param value   属性值
     */
    private void addClaim(JWTCreator.Builder builder, String name, Object value) {
        if (Objects.nonNull(value)) {
            if (value instanceof Boolean v) {
                builder.withClaim(name, v);
            } else if (value instanceof Double v) {
                builder.withClaim(name, v);
            } else if (value instanceof Float v) {
                builder.withClaim(name, v.doubleValue());
            } else if (value instanceof Integer v) {
                builder.withClaim(name, v);
            } else if (value instanceof Long v) {
                builder.withClaim(name, v);
            } else if (value instanceof String v) {
                builder.withClaim(name, v);
            } else if (value instanceof Date v) {
                builder.withClaim(name, v);
            } else if (value instanceof List<?> v) {
                builder.withClaim(name, v);
            } else {
                log.warn("""
                        无法确定字段 {} 的类型，现在将其转换为字符串。""", name);
                builder.withClaim(name, value.toString());
            }
        } else {
            builder.withNullClaim(name);
        }
    }

    /**
     * 使用提供的权利要求映射表构建 JSON 网络令牌 (JWT) 的自定义权利要求，
     * 并将其添加到 JWTCreator.Builder 中。
     * <p>
     * 该方法用于向 JWT 添加自定义声明。它接收一个索赔映射表，
     * 其中每个条目代表一个自定义索赔名称（key）及其相应的值（value）。
     * 自定义请求将使用 JWTCreator.Builder.Map 方法添加到 JWT。
     *
     * @param claims  其中包含要添加到 JWT 中的自定义声明的键值对
     * @param builder 将添加自定义索赔的 JWTCreator.Builder 实例
     */
    private void buildMapClaims(JWTCreator.Builder builder, Map<String, Object> claims) {
        if (Objects.nonNull(claims)) {
            for (var e : claims.entrySet()) {
                addClaim(builder, e.getKey(), e.getValue());
            }
        }
    }

    /**
     * 完成创建令牌。
     * <p>
     * 这是创建令牌的最后一步，也是签署令牌的最后一步。
     *
     * @param builder 构建器来构建该 JWT
     * @return 生成的令牌作为 {@code String}
     */
    private String buildToken(JWTCreator.Builder builder) {
        return builder.sign(algorithm);
    }

    /**
     * 创建一个新令牌，并指定有效期、主题和受众。
     *
     * @param expireAfter 令牌过期的时间
     * @param subject     标记的主体
     * @param audience    令牌面向的受众
     * @return 生成的令牌作为 {@code String}
     */
    @Override
    public String createToken(Duration expireAfter, String audience, String subject) {
        final var builder = JWT.create();
        buildBasicInfo(builder, expireAfter, subject, audience);
        return buildToken(builder);
    }

    /**
     * 创建一个新令牌，并指定到期时间和主题、受众和自定义有效载荷数据。
     *
     * @param expireAfter 令牌过期的时间
     * @param subject     标记的主体
     * @param audience    令牌面向的受众
     * @param payloads    将包含在令牌中的自定义有效载荷数据(键值对类型)
     * @return 生成的令牌作为 {@code String}
     */
    @Override
    public String createToken(Duration expireAfter, String audience, String subject, Map<String, Object> payloads) {
        // Create token.
        final var builder = JWT.create();
        buildBasicInfo(builder, expireAfter, subject, audience);
        buildMapClaims(builder, payloads);
        return buildToken(builder);
    }

    /**
     * 创建一个新令牌，并指定有效期、主题、受众和强类型有效载荷数据。
     *
     * @param expireAfter 令牌过期的时间
     * @param subject     标记的主体
     * @param audience    令牌面向的受众
     * @param payload     将包含在令牌中的自定义有效载荷数据(泛型)
     * @return 生成的令牌作为 {@code String}
     */
    @Override
    public <T extends TokenPayload> String createToken(Duration expireAfter, String audience, String subject, T payload) {
        final JWTCreator.Builder builder = JWT.create();
        buildBasicInfo(builder, expireAfter, subject, audience);

        var payloadClass = payload.getClass();
        var fields = payloadClass.getDeclaredFields();

        for (var field : fields) {
            // Skip the fields which are annotated with ExcludeFromPayload
            if (field.isAnnotationPresent(ExcludeFromPayload.class))
                continue;

            try {
                field.setAccessible(true);
                // Build Claims
                addClaim(builder, field.getName(), field.get(payload));
            } catch (IllegalAccessException e) {
                log.error("Cannot access field {}!", field.getName());
            }
        }

        return buildToken(builder);
    }

    /**
     * 将给定的标记转换为 @link DecodedJWT} 对象。
     *
     * @param token 要解析的标记
     * @return 一个 {@link DecodedJWT} 对象
     */
    @Override
    public DecodedJWT resolve(String token) {
        return verifier.verify(token);
    }

    /**
     * 从给定标记中提取有效载荷信息，并将其映射到指定的目标类型。
     *
     * @param token      从中提取有效负载的令牌
     * @param targetType 代表有效负载数据类型的目标类
     * @return 指定目标类型的实例，其中包含提取的有效载荷数据，
     * 或者当 {@code null} 提取失败时
     */
    @Override
    public <T extends TokenPayload> T extract(String token, Class<T> targetType) {
        // 从令牌中获取声明。
        var claims = resolve(token).getClaims();

        try {
            // 获取无参数构造函数以创建实例。
            T bean = targetType.getConstructor().newInstance();

            var fields = targetType.getDeclaredFields();
            for (var field : fields) {
                // 忽略注释为 @ExcludeFromPayload 的字段。
                if (field.isAnnotationPresent(ExcludeFromPayload.class))
                    continue;

                // 获取该字段的名称。
                var fieldName = field.getName();

                // 防止该类被注释为 @Slf4j 或添加日志记录器。
                if ("log".equalsIgnoreCase(fieldName) || "logger".equalsIgnoreCase(fieldName))
                    continue;

                // 获取该字段的值。
                var fieldValue = Optional.ofNullable(claims.get(fieldName))
                        .map(claim -> claim.as(field.getType()))
                        .orElse(null);
                if (fieldValue != null) {
                    // 通过调用 setter 方法设置字段值。
                    var setter = targetType.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), fieldValue.getClass());
                    setter.invoke(bean, fieldValue);
                }
            }

            return bean;
        } catch (NoSuchMethodException e) {
            log.error("无法为类 {} 找到无参数构造函数声明。", targetType.getCanonicalName());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("无法创建类 {} 的新实例。", targetType.getCanonicalName());
        }
        return null;
    }

    /**
     * 使用指定的自定义有效载荷数据更新给定的过期令牌。
     *
     * @param oldToken 要续期的过期令牌
     * @param payload  将包含在已更新令牌中的自定义有效负载数据
     * @return 更新后的令牌作为 {@code String}
     */
    @Override
    public String renew(String oldToken, Duration expireAfter, Map<String, Object> payload) {
        final var resolvedToken = this.resolve(oldToken);
        var audience = resolvedToken.getAudience().get(0);

        return createToken(expireAfter, audience, resolvedToken.getSubject(), payload);
    }

    /**
     * 使用指定的自定义有效载荷数据更新给定的过期令牌。
     *
     * @param oldToken 要续期的即将到期的令牌
     * @param payload  将包含在已更新令牌中的自定义有效载荷数据
     * @return 更新后的令牌作为 {@code String}
     */
    @Override
    public String renew(String oldToken, Map<String, Object> payload) {
        return renew(oldToken, Duration.ofMinutes(30), payload);
    }

    /**
     * 使用新的指定强类型有效载荷数据更新给定的过期令牌。
     *
     * @param oldToken 要续期的即将到期的令牌
     * @param payload  续期令牌中将包含的强类型有效载荷数据
     * @return 更新后的令牌作为 {@code String}
     */
    @Override
    public <T extends TokenPayload> String renew(String oldToken, Duration expireAfter, T payload) {
        final var resolvedToken = this.resolve(oldToken);
        var audience = resolvedToken.getAudience().get(0);

        return createToken(expireAfter, audience, resolvedToken.getSubject(), payload);
    }

    /**
     * 使用新的指定强类型有效载荷数据更新给定的过期令牌。
     *
     * @param <T>      有效载荷数据类型，必须执行
     *                 {@link TokenPayload}
     * @param oldToken 要续期的过期令牌
     * @param payload  续期令牌中将包含的强类型有效载荷数据
     * @return 更新后的令牌作为 {@link String}
     */
    @Override
    public <T extends TokenPayload> String renew(String oldToken, T payload) {
        return renew(oldToken, Duration.ofMinutes(30), payload);
    }
}
