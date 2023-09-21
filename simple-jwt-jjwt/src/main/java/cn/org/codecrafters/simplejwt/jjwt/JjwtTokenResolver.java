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

package cn.org.codecrafters.simplejwt.jjwt;

import cn.org.codecrafters.devkit.utils.MapUtil;
import cn.org.codecrafters.guid.GuidCreator;
import cn.org.codecrafters.simplejwt.SecretCreator;
import cn.org.codecrafters.simplejwt.TokenPayload;
import cn.org.codecrafters.simplejwt.TokenResolver;
import cn.org.codecrafters.simplejwt.constants.TokenAlgorithm;
import cn.org.codecrafters.simplejwt.exceptions.WeakSecretException;
import cn.org.codecrafters.simplejwt.jjwt.config.JjwtTokenResolverConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * {@link JjwtTokenResolver} 类是 {@link cn.org.codecrafters.simplejwt.TokenResolver}
 * 接口的实现。它使用 {@code io.jsonwebtoken:jjwt} 库来处理 JSON 网络令牌（JWT）解析。
 * 该解析器提供了使用各种算法和自定义有效载荷数据创建、提取、验证和更新 JWT 令牌的功能。
 *
 * <p>
 * <b>依赖关系：</b>
 * 本实现依赖于 {@code io.jsonwebtoken:jjwt} 库。
 * 在使用此解析器之前，请确保您已将此库作为依赖项添加到您的项目中。
 * <p>
 * <b>使用方法：</b>
 * 要使用 {@code JjwtTokenResolver}，首先要创建该类的一个实例：
 * <pre>{@code
 *   TokenResolver<Jws<Claims>> tokenResolver =
 *       new JjwtTokenResolver(TokenAlgorithm.HS256,
 *                                 "Token Subject",
 *                                 "Token Issuer",
 *                                 "Token Secret");
 *   }</pre>
 * <p>
 * 然后，您就可以利用该解析器提供的各种方法来处理 JWT 标记：
 * <pre>{@code
 *   // Creating a new JWT token
 *   String token =
 *       tokenResolver.createToken(Duration.ofHours(1),
 *                                 "your_subject",
 *                                 "your_audience",
 *                                 customPayloads);
 *
 *   // Extracting payload data from a JWT token
 *   DecodedJWT decodedJWT = tokenResolver.resolve(token);
 *   T payloadData = decodedJWT.extract(token, T.class);
 *
 *   // Renewing an existing JWT token
 *   String renewedToken =
 *       tokenResolver.renew(token, Duration.ofMinutes(30), customPayloads);
 *   }</pre>
 * <p>
 * <b>注意事项：</b>
 * 使用此解析器时，必须根据具体使用情况配置适当的算法、密文和签发器。
 * 此外，请确保在项目的依赖项中正确配置了 {@code io.jsonwebtoken:jjwt} 库。
 *
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @see Claims
 * @see Jws
 * @see Jwts
 * @see SignatureAlgorithm
 * @see Keys
 * @since 1.0.0
 */
@Slf4j
public class JjwtTokenResolver implements TokenResolver<Jws<Claims>> {

    private final GuidCreator<?> jtiCreator;

    private final SignatureAlgorithm algorithm;

    private final String issuer;

    private final Key key;

    private final JjwtTokenResolverConfig config = JjwtTokenResolverConfig.getInstance();

    public JjwtTokenResolver(GuidCreator<?> jtiCreator, TokenAlgorithm algorithm, String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("创建 JSON 网络令牌需要一个密文。");
        }

        if (secret.length() <= 32) {
            log.error("""
                            所提供的包含 {} 字符的密文太弱。请替换为更强的密文。""",
                    secret.length());
            throw new WeakSecretException("""
                    所提供的包含 %s 个字符的密文太弱。请替换为更强的密文。"""
                    .formatted(secret.length()));
        }

        this.jtiCreator = jtiCreator;
        this.algorithm = config.getAlgorithm(algorithm);
        this.issuer = issuer;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public JjwtTokenResolver(TokenAlgorithm algorithm, String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("创建 JSON 网络令牌需要一个密文。");
        }

        if (secret.length() <= 32) {
            log.error(
                    "所提供的包含 {} 字符的密文太弱。请替换为更强的密文。",
                    secret.length());
            throw new WeakSecretException(
                    "所提供的包含 %s 个字符的密文太弱。请替换为更强的密文。"
                            .formatted(secret.length()));
        }

        this.jtiCreator = UUID::randomUUID;
        this.algorithm = config.getAlgorithm(algorithm);
        this.issuer = issuer;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public JjwtTokenResolver(String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("创建 JSON 网络令牌需要一个密文。");
        }

        if (secret.length() <= 32) {
            log.error(
                    "所提供的包含 {} 字符的密文太弱。请替换为更强的密文。",
                    secret.length());
            throw new WeakSecretException(
                    "所提供的包含 %s 个字符的密文太弱。请替换为更强的密文。"
                            .formatted(secret.length()));
        }

        this.jtiCreator = UUID::randomUUID;
        this.algorithm = config.getAlgorithm(TokenAlgorithm.HS256);
        this.issuer = issuer;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public JjwtTokenResolver(String issuer) {
        this.jtiCreator = UUID::randomUUID;
        this.algorithm = config.getAlgorithm(TokenAlgorithm.HS256);
        this.issuer = issuer;
        this.key = Keys.hmacShaKeyFor(SecretCreator.createSecret(32, true, true, true).getBytes(StandardCharsets.UTF_8));
    }

    private String buildToken(Duration expireAfter, String audience, String subject, LocalDateTime now, Map<String, Object> claims) {
        var builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setNotBefore(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(now.plus(expireAfter).atZone(ZoneId.systemDefault()).toInstant()))
                .setSubject(subject)
                .setAudience(audience)
                .setIssuer(this.issuer)
                .setId(jtiCreator.nextId().toString());

        if (claims != null && !claims.isEmpty()) {
            builder.setClaims(claims);
        }

        return builder.signWith(key, algorithm)
                .compact();
    }

    /**
     * 创建一个新令牌，并指定有效期、主题和受众。
     *
     * @param expireAfter 令牌过期的时间
     * @param audience    令牌的目标受众
     * @param subject     令牌的主体
     * @return 生成的令牌为 {@code String｝
     */
    @Override
    public String createToken(Duration expireAfter, String audience, String subject) {
        var now = LocalDateTime.now();
        return buildToken(expireAfter, audience, subject, now, null);
    }

    /**
     * 创建一个新令牌，其中包含指定的过期时间、主题、受众和自定义有效载荷数据。
     *
     * @param expireAfter 令牌过期的时间
     * @param audience    令牌的目标受众
     * @param subject     令牌主体
     * @param payload     将包含在令牌中的自定义有效载荷数据
     * @return 生成的令牌为 {@code String}
     */
    @Override
    public String createToken(Duration expireAfter, String audience, String subject, Map<String, Object> payload) {
        var now = LocalDateTime.now();
        return buildToken(expireAfter, audience, subject, now, payload);
    }

    /**
     * 创建新令牌，并指定有效期、主题、受众和强类型有效载荷数据。
     *
     * @param expireAfter 令牌过期的时间
     * @param audience    令牌的目标受众
     * @param subject     令牌主体
     * @param payload     令牌中包含的强类型有效载荷数据
     * @return 生成的令牌为 {@code String} 或 {@code null} 如果 创建失败
     * @see MapUtil#objectToMap(Object)
     */
    @Override
    public <T extends TokenPayload> String createToken(Duration expireAfter, String audience, String subject, T payload) {
        var now = LocalDateTime.now();
        try {
            var claims = MapUtil.objectToMap(payload);
            return buildToken(expireAfter, audience, subject, now, claims);
        } catch (IllegalAccessException e) {
            log.error("访问对象字段时发生错误。");
        }

        return null;
    }

    /**
     * 将给定的标记解析为 {@link Jws<Claims>} 对象。
     *
     * @param token 要解析的令牌
     * @return 一个 ResolvedTokenType 对象
     */
    @Override
    public Jws<Claims> resolve(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    /**
     * 从给定标记中提取有效载荷信息，并将其映射到指定的目标类型。
     *
     * @param token      从中提取有效载荷的令牌
     * @param targetType 代表有效载荷数据类型的目标类
     * @return 指定目标类型的实例，其中包含提取的有效载荷数据；如果提取失败，则返回 {@code null}。
     * @see MapUtil#mapToObject(Map, Class)
     */
    @Override
    public <T extends TokenPayload> T extract(String token, Class<T> targetType) {
        var resolvedToken = resolve(token);

        var claims = resolvedToken.getBody();
        try {
            return MapUtil.mapToObject(claims, targetType);
        } catch (InvocationTargetException e) {
            log.error("调用 {} 类型的构造函数时发生错误。", targetType.getCanonicalName());
        } catch (NoSuchMethodException e) {
            log.error("未找到所需类型 {} 的构造函数。", targetType.getCanonicalName());
        } catch (InstantiationException e) {
            log.error("所需类型 {} 是抽象类型或接口类型。", targetType.getCanonicalName());
        } catch (IllegalAccessException e) {
            log.error("访问对象的字段时发生错误。");
        }

        return null;
    }

    /**
     * 使用指定的自定义有效载荷数据更新给定的过期令牌。
     *
     * @param oldToken    要续期的过期令牌
     * @param expireAfter 指定新令牌何时失效
     * @param payload     更新令牌中包含的自定义有效载荷数据
     * @return the renewed token as a {@code String}
     */
    @Override
    public String renew(String oldToken, Duration expireAfter, Map<String, Object> payload) {
        var resolvedTokenClaims = resolve(oldToken).getBody();
        var audience = resolvedTokenClaims.getAudience();
        var subject = resolvedTokenClaims.getSubject();

        return createToken(expireAfter, audience, subject, payload);
    }

    /**
     * 使用指定的自定义有效载荷数据更新给定的过期令牌。
     *
     * @param oldToken 要续期的过期令牌
     * @param payload  将包含在已更新令牌中的自定义有效载荷数据
     * @return 更新的标记为 {@code String}
     */
    @Override
    public String renew(String oldToken, Map<String, Object> payload) {
        return renew(oldToken, Duration.ofMinutes(30), payload);
    }

    /**
     * 使用指定的强类型有效载荷数据更新给定的过期令牌。
     *
     * @param oldToken    要续期的过期令牌
     * @param expireAfter 指定新令牌何时失效
     * @param payload     续期令牌中将包含的强类型有效载荷数据
     * @return 更新的标记为 {@code String}
     */
    @Override
    public <T extends TokenPayload> String renew(String oldToken, Duration expireAfter, T payload) {
        var resolvedTokenClaims = resolve(oldToken).getBody();
        var audience = resolvedTokenClaims.getAudience();
        var subject = resolvedTokenClaims.getSubject();

        return createToken(expireAfter, audience, subject, payload);
    }

    /**
     * 使用指定的强类型有效载荷数据更新给定的过期令牌。
     *
     * @param oldToken 要续期的过期令牌
     * @param payload  续期令牌中将包含的强类型有效载荷数据
     * @return 更新的标记为 {@code String}
     */
    @Override
    public <T extends TokenPayload> String renew(String oldToken, T payload) {
        return renew(oldToken, Duration.ofMinutes(30), payload);
    }
}
