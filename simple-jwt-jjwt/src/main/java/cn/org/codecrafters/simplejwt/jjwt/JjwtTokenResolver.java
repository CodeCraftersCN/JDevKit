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
 * The {@link JjwtTokenResolver} class is an implementation of the {@link
 * cn.org.codecrafters.simplejwt.TokenResolver} interface. It uses the {@code
 * io.jsonwebtoken:jjwt} library to handle JSON Web Token (JWT) resolution.
 * This resolver provides functionality to create, extract, verify, and renew
 * JWT tokens using various algorithms and custom payload data.
 * <p>
 * <b>Dependencies:</b>
 * This implementation relies on the {@code io.jsonwebtoken:jjwt} library. Please
 * ensure you have added this library as a dependency to your project before
 * using this resolver.
 * <p>
 * <b>Usage:</b>
 * To use the {@code JjwtTokenResolver}, first, create an instance of this
 * class:
 * <pre>{@code
 *   TokenResolver<DecodedJWT> tokenResolver =
 *       new JjwtTokenResolver(TokenAlgorithm.HS256,
 *                                 "Token Subject",
 *                                 "Token Issuer",
 *                                 "Token Secret");
 *   }</pre>
 * <p>
 * Then, you can utilize the various methods provided by this resolver to
 * handle JWT tokens:
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
 * <b>Note:</b>
 * It is essential to configure the appropriate algorithms, secret, and issuer
 * according to your specific use case when using this resolver.
 * Additionally, ensure that the {@code io.jsonwebtoken:jjwt} library is
 * correctly configured in your project's dependencies.
 *
 * @author Zihlu Wang
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
            throw new IllegalArgumentException("A secret is required to build a JSON Web Token.");
        }

        if (secret.length() <= 32) {
            log.error("""
                            The provided secret which owns {} characters is too weak. Please replace it with a stronger one.""",
                    secret.length());
            throw new WeakSecretException("""
                    The provided secret which owns %s characters is too weak. Please replace it with a stronger one."""
                    .formatted(secret.length()));
        }

        this.jtiCreator = jtiCreator;
        this.algorithm = config.getAlgorithm(algorithm);
        this.issuer = issuer;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public JjwtTokenResolver(TokenAlgorithm algorithm, String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("A secret is required to build a JSON Web Token.");
        }

        if (secret.length() <= 32) {
            log.error(
                    "The provided secret which owns {} characters is too weak. Please replace it with a stronger one.",
                    secret.length());
            throw new WeakSecretException(
                    "The provided secret which owns %s characters is too weak. Please replace it with a stronger one."
                            .formatted(secret.length()));
        }

        this.jtiCreator = UUID::randomUUID;
        this.algorithm = config.getAlgorithm(algorithm);
        this.issuer = issuer;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public JjwtTokenResolver(String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("A secret is required to build a JSON Web Token.");
        }

        if (secret.length() <= 32) {
            log.error(
                    "The provided secret which owns {} characters is too weak. Please replace it with a stronger one.",
                    secret.length());
            throw new WeakSecretException(
                    "The provided secret which owns %s characters is too weak. Please replace it with a stronger one."
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
     * Creates a new token with the specified expiration time, subject, and
     * audience.
     *
     * @param expireAfter the duration after which the token will expire
     * @param audience    the audience for which the token is intended
     * @param subject     the subject of the token
     * @return the generated token as a {@code String}
     */
    @Override
    public String createToken(Duration expireAfter, String audience, String subject) {
        var now = LocalDateTime.now();
        return buildToken(expireAfter, audience, subject, now, null);
    }

    /**
     * Creates a new token with the specified expiration time, subject,
     * audience, and custom payload data.
     *
     * @param expireAfter the duration after which the token will expire
     * @param audience    the audience for which the token is intended
     * @param subject     the subject of the token
     * @param payload     the custom payload data to be included in the token
     * @return the generated token as a {@code String}
     */
    @Override
    public String createToken(Duration expireAfter, String audience, String subject, Map<String, Object> payload) {
        var now = LocalDateTime.now();
        return buildToken(expireAfter, audience, subject, now, payload);
    }

    /**
     * Creates a new token with the specified expiration time, subject,
     * audience, and strongly-typed payload data.
     *
     * @param expireAfter the duration after which the token will expire
     * @param audience    the audience for which the token is intended
     * @param subject     the subject of the token
     * @param payload     the strongly-typed payload data to be included in the
     *                    token
     * @return the generated token as a {@code String} or {@code null} if
     * creation fails
     * @see MapUtil#objectToMap(Object)
     */
    @Override
    public <T extends TokenPayload> String createToken(Duration expireAfter, String audience, String subject, T payload) {
        var now = LocalDateTime.now();
        try {
            var claims = MapUtil.objectToMap(payload);
            return buildToken(expireAfter, audience, subject, now, claims);
        } catch (IllegalAccessException e) {
            log.error("An error occurs while accessing the fields of the object");
        }

        return null;
    }

    /**
     * Resolves the given token into a ResolvedTokenType object.
     *
     * @param token the token to be resolved
     * @return a ResolvedTokenType object
     */
    @Override
    public Jws<Claims> resolve(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    /**
     * Extracts the payload information from the given token and maps it to the
     * specified target type.
     *
     * @param token      the token from which to extract the payload
     * @param targetType the target class representing the payload data type
     * @return an instance of the specified target type with the extracted
     * payload data, or {@code null} if extraction fails.
     * @see MapUtil#mapToObject(Map, Class)
     */
    @Override
    public <T extends TokenPayload> T extract(String token, Class<T> targetType) {
        var resolvedToken = resolve(token);

        var claims = resolvedToken.getBody();
        try {
            return MapUtil.mapToObject(claims, targetType);
        } catch (InvocationTargetException e) {
            log.info("An error occurs while invoking the constructor of type {}.", targetType.getCanonicalName());
        } catch (NoSuchMethodException e) {
            log.error("The constructor of the required type {} is not found.", targetType.getCanonicalName());
        } catch (InstantiationException e) {
            log.error("The required type {} is abstract or an interface.", targetType.getCanonicalName());
        } catch (IllegalAccessException e) {
            log.error("An error occurs while accessing the fields of the object.");
        }

        return null;
    }

    /**
     * Renews the given expired token with the specified custom payload data.
     *
     * @param oldToken    the expired token to be renewed
     * @param expireAfter specify when does the new token invalid
     * @param payload     the custom payload data to be included in the renewed
     *                    token
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
     * Renews the given expired token with the specified custom payload data.
     *
     * @param oldToken the expired token to be renewed
     * @param payload  the custom payload data to be included in the renewed
     *                 token
     * @return the renewed token as a {@code String}
     */
    @Override
    public String renew(String oldToken, Map<String, Object> payload) {
        return renew(oldToken, Duration.ofMinutes(30), payload);
    }

    /**
     * Renews the given expired token with the specified strongly-typed
     * payload data.
     *
     * @param oldToken    the expired token to be renewed
     * @param expireAfter specify when does the new token invalid
     * @param payload     the strongly-typed payload data to be included in the
     *                    renewed token
     * @return the renewed token as a {@code String}
     */
    @Override
    public <T extends TokenPayload> String renew(String oldToken, Duration expireAfter, T payload) {
        var resolvedTokenClaims = resolve(oldToken).getBody();
        var audience = resolvedTokenClaims.getAudience();
        var subject = resolvedTokenClaims.getSubject();

        return createToken(expireAfter, audience, subject, payload);
    }

    /**
     * Renews the given expired token with the specified strongly-typed
     * payload data.
     *
     * @param oldToken the expired token to be renewed
     * @param payload  the strongly-typed payload data to be included in the
     *                 renewed token
     * @return the renewed token as a {@code String}
     */
    @Override
    public <T extends TokenPayload> String renew(String oldToken, T payload) {
        return renew(oldToken, Duration.ofMinutes(30), payload);
    }
}
