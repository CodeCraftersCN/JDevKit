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
 * The {@code AuthzeroTokenResolver} class is an implementation of the {@link
 * cn.org.codecrafters.simplejwt.TokenResolver} interface. It uses the {@code
 * com.auth0:java-jwt} library to handle JSON Web Token (JWT) resolution. This
 * resolver provides functionality to create, extract, verify, and renew JWT
 * tokens using various algorithms and custom payload data.
 * <p>
 * <b>Dependencies:</b>
 * This implementation relies on the {@code com.auth0:java-jwt} library. Please
 * ensure you have added this library as a dependency to your project before
 * using this resolver.
 * <p>
 * <b>Usage:</b>
 * To use the {@code AuthzeroTokenResolver}, first, create an instance of this
 * class:
 * <pre>{@code
 * TokenResolver<DecodedJWT> tokenResolver =
 *     new AuthzeroTokenResolver(TokenAlgorithm.HS256,
 *                               "Token Subject",
 *                               "Token Issuer",
 *                               "Token Secret");
 * }</pre>
 * <p>
 * Then, you can utilize the various methods provided by this resolver to
 * handle JWT tokens:
 * <pre>{@code
 * // Creating a new JWT token
 * String token =
 *     tokenResolver.createToken(Duration.ofHours(1),
 *                               "your_subject",
 *                               "your_audience",
 *                               customPayloads);
 *
 * // Extracting payload data from a JWT token
 * DecodedJWT decodedJWT = tokenResolver.resolve(token);
 * T payloadData = decodedJWT.extract(token, T.class);
 *
 * // Renewing an existing JWT token
 * String renewedToken =
 *     tokenResolver.renew(token, Duration.ofMinutes(30), customPayloads);
 * }</pre>
 * <p>
 * <b>Note:</b>
 * It is essential to configure the appropriate algorithms, secret, and issuer
 * according to your specific use case when using this resolver.
 * Additionally, ensure that the {@code com.auth0:java-jwt} library is
 * correctly configured in your project's dependencies.
 *
 * @author Zihlu Wang
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
     * GuidCreator used for generating unique identifiers for "jti" claim in
     * JWT tokens.
     */
    private final GuidCreator<?> jtiCreator;

    /**
     * The algorithm used for signing and verifying JWT tokens.
     */
    private final Algorithm algorithm;

    /**
     * The issuer claim value to be included in JWT tokens.
     */
    private final String issuer;

    /**
     * The JSON Web Token resolver.
     */
    private final JWTVerifier verifier;

    private final AuthzeroTokenResolverConfig config = AuthzeroTokenResolverConfig.getInstance();

    /**
     * Creates a new instance of {@code AuthzeroTokenResolver} with the
     * provided configurations.
     *
     * @param jtiCreator the {@link GuidCreator} used for generating unique
     *                   identifiers for "jti" claim in JWT tokens
     * @param algorithm  the algorithm used for signing and verifying JWT
     *                   tokens
     * @param issuer     the issuer claim value to be included in JWT tokens
     * @param secret     the secret used for HMAC-based algorithms (HS256,
     *                   HS384, HS512) for token signing and verification
     */
    public AuthzeroTokenResolver(GuidCreator<?> jtiCreator, TokenAlgorithm algorithm, String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("A secret is required to build a JSON Web Token.");
        }

        if (secret.length() <= 32) {
            log.warn("The provided secret which owns {} characters is too weak. Please consider replacing it with a stronger one.", secret.length());
        }

        this.jtiCreator = jtiCreator;
        this.algorithm = config
                .getAlgorithm(algorithm)
                .apply(secret);
        this.issuer = issuer;
        this.verifier = JWT.require(this.algorithm).build();
    }

    /**
     * Creates a new instance of {@link AuthzeroTokenResolver} with the
     * provided configurations and a simple UUID GuidCreator.
     *
     * @param algorithm the algorithm used for signing and verifying JWT tokens
     * @param issuer    the issuer claim value to be included in JWT tokens
     * @param secret    the secret used for HMAC-based algorithms (HS256,
     *                  HS384, HS512) for token signing and verification
     */
    public AuthzeroTokenResolver(TokenAlgorithm algorithm, String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("A secret is required to build a JSON Web Token.");
        }

        if (secret.length() <= 32) {
            log.warn("The provided secret which owns {} characters is too weak. Please consider replacing it with a stronger one.", secret.length());
        }

        this.jtiCreator = UUID::randomUUID;
        this.algorithm = config
                .getAlgorithm(algorithm)
                .apply(secret);
        this.issuer = issuer;
        this.verifier = JWT.require(this.algorithm).build();
    }

    /**
     * Creates a new instance of {@link AuthzeroTokenResolver} with the
     * provided configurations, HMAC256 algorithm and a simple
     * UUID GuidCreator.
     *
     * @param issuer the issuer claim value to be included in JWT tokens
     * @param secret the secret used for HMAC-based algorithms (HS256,
     *               HS384, HS512) for token signing and verification
     */
    public AuthzeroTokenResolver(String issuer, String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("A secret is required to build a JSON Web Token.");
        }

        if (secret.length() <= 32) {
            log.warn("The provided secret which owns {} characters is too weak. Please consider replacing it with a stronger one.", secret.length());
        }

        this.jtiCreator = UUID::randomUUID;
        this.algorithm = config
                .getAlgorithm(TokenAlgorithm.HS256)
                .apply(secret);
        this.issuer = issuer;
        this.verifier = JWT.require(this.algorithm).build();
    }

    /**
     * Creates a new instance of {@link AuthzeroTokenResolver} with the
     * provided configurations, HMAC256 algorithm and a simple
     * UUID GuidCreator.
     *
     * @param issuer the issuer claim value to be included in JWT tokens
     */
    public AuthzeroTokenResolver(String issuer) {
        var secret = SecretCreator.createSecret(32, true, true, true);

        this.jtiCreator = UUID::randomUUID;
        this.algorithm = config
                .getAlgorithm(TokenAlgorithm.HS256)
                .apply(secret);
        this.issuer = issuer;
        this.verifier = JWT.require(this.algorithm).build();

        log.info("The secret has been set to {}.", secret);
    }

    /**
     * Builds the basic information of the JSON Web Token (JWT) using the
     * provided parameters and adds it to the JWTCreator.Builder.
     *
     * @param subject     the subject claim value to be included in the JWT
     * @param audience    an array of audience claim values to be included in
     *                    the JWT
     * @param expireAfter the duration after which the JWT will expire
     * @param builder     the JWTCreator.Builder instance to which the basic
     *                    information will be added
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
     * Add a claim to a builder.
     *
     * @param builder the builder to build this JSON Web Token
     * @param name    the property name
     * @param value   the property value
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
                        Unable to determine the type of field {}, converting it to a string now.""", name);
                builder.withClaim(name, value.toString());
            }
        } else {
            builder.withNullClaim(name);
        }
    }

    /**
     * Builds the custom claims of the JSON Web Token (JWT) using the provided
     * Map of claims and adds them to the JWTCreator.Builder.
     * <p>
     * This method is used to add custom claims to the JWT. It takes a Map of
     * claims, where each entry represents a custom claim name (key) and its
     * corresponding value (value). The custom claims will be added to the JWT
     * using the JWTCreator.Builder.
     *
     * @param claims  a Map containing the custom claims to be added to the JWT
     * @param builder the JWTCreator.Builder instance to which the custom
     *                claims will be added
     */
    private void buildMapClaims(JWTCreator.Builder builder, Map<String, Object> claims) {
        if (Objects.nonNull(claims)) {
            for (var e : claims.entrySet()) {
                addClaim(builder, e.getKey(), e.getValue());
            }
        }
    }

    /**
     * Finish creating a token.
     * <p>
     * This is the final step of create a token, to sign this token.
     *
     * @param builder the builder to build this JWT
     * @return the generated token as a {@code String}
     */
    private String buildToken(JWTCreator.Builder builder) {
        return builder.sign(algorithm);
    }

    /**
     * Creates a new token with the specified expiration duration, subject, and
     * audience.
     *
     * @param expireAfter the duration after which the token will expire
     * @param subject     the subject of the token
     * @param audience    the audience for which the token is intended
     * @return the generated token as a {@code String}
     */
    @Override
    public String createToken(Duration expireAfter, String audience, String subject) {
        final var builder = JWT.create();
        buildBasicInfo(builder, expireAfter, subject, audience);
        return buildToken(builder);
    }

    /**
     * Creates a new token with the specified expiration time, subject,
     * audience, and custom payload data.
     *
     * @param expireAfter the duration after which the token will expire
     * @param subject     the subject of the token
     * @param audience    the audience for which the token is intended
     * @param payloads    the custom payload data to be included in the token
     * @return the generated token as a {@code String}
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
     * Creates a new token with the specified expiration time, subject,
     * audience, and strongly-typed payload data.
     *
     * @param expireAfter the duration after which the token will expire
     * @param subject     the subject of the token
     * @param audience    the audience for which the token is intended
     * @param payload     the strongly-typed payload data to be included in the
     *                    token
     * @return the generated token as a {@code String}
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
     * Resolves the given token into a {@link DecodedJWT} object.
     *
     * @param token the token to be resolved
     * @return a {@link DecodedJWT} object
     */
    @Override
    public DecodedJWT resolve(String token) {
        return verifier.verify(token);
    }

    /**
     * Extracts the payload information from the given token and maps it to the
     * specified target type.
     *
     * @param token      the token from which to extract the payload
     * @param targetType the target class representing the payload data type
     * @return an instance of the specified target type with the extracted
     * payload data, or {@code null} when extraction fails
     */
    @Override
    public <T extends TokenPayload> T extract(String token, Class<T> targetType) {
        // Get claims from token.
        var claims = resolve(token).getClaims();

        try {
            // Get the no-argument constructor to create an instance.
            T bean = targetType.getConstructor().newInstance();

            var fields = targetType.getDeclaredFields();
            for (var field : fields) {
                // Ignore the field annotated with @ExcludeFromPayload.
                if (field.isAnnotationPresent(ExcludeFromPayload.class))
                    continue;

                // Get the name of this field.
                var fieldName = field.getName();

                // Prevent this class is annotated @Slf4j or added logger.
                if ("log".equalsIgnoreCase(fieldName) || "logger".equalsIgnoreCase(fieldName))
                    continue;

                // Get the value of this field.
                var fieldValue = Optional.ofNullable(claims.get(fieldName))
                        .map(claim -> claim.as(field.getType()))
                        .orElse(null);
                if (fieldValue != null) {
                    // Set the field value by invoking the setter method.
                    var setter = targetType.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), fieldValue.getClass());
                    setter.invoke(bean, fieldValue);
                }
            }

            return bean;
        } catch (NoSuchMethodException e) {
            log.error("Unable to find a no-argument constructor declaration for class {}.", targetType.getCanonicalName());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("Unable to create a new instance of class {}.", targetType.getCanonicalName());
        }
        return null;
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
    public String renew(String oldToken, Duration expireAfter, Map<String, Object> payload) {
        final var resolvedToken = this.resolve(oldToken);
        var audience = resolvedToken.getAudience().get(0);

        return createToken(expireAfter, audience, resolvedToken.getSubject(), payload);
    }

    /**
     * Renews the given expired token with the specified custom payload data.
     *
     * @param oldToken the expiring token to be renewed
     * @param payload  the custom payload data to be included in the renewed
     *                 token
     * @return the renewed token as a {@code String}
     */
    @Override
    public String renew(String oldToken, Map<String, Object> payload) {
        return renew(oldToken, Duration.ofMinutes(30), payload);
    }

    /**
     * Renews the given expired token with the new specified strongly-typed
     * payload data.
     *
     * @param oldToken the expiring token to be renewed
     * @param payload  the strongly-typed payload data to be included in the
     *                 renewed token
     * @return the renewed token as a {@code String}
     */
    @Override
    public <T extends TokenPayload> String renew(String oldToken, Duration expireAfter, T payload) {
        final var resolvedToken = this.resolve(oldToken);
        var audience = resolvedToken.getAudience().get(0);

        return createToken(expireAfter, audience, resolvedToken.getSubject(), payload);
    }

    /**
     * Renews the given expired token with the new specified strongly-typed
     * payload data.
     *
     * @param <T>      the type of the payload data, must implement
     *                 {@link TokenPayload}
     * @param oldToken the expired token to be renewed
     * @param payload  the strongly-typed payload data to be included in the
     *                 renewed token
     * @return the renewed token as a {@link String}
     */
    @Override
    public <T extends TokenPayload> String renew(String oldToken, T payload) {
        return renew(oldToken, Duration.ofMinutes(30), payload);
    }
}
