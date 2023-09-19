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

package cn.org.codecrafters.simplejwt.authzero.config;

import cn.org.codecrafters.simplejwt.TokenResolver;
import cn.org.codecrafters.simplejwt.authzero.AuthzeroTokenResolver;
import cn.org.codecrafters.simplejwt.config.TokenResolverConfig;
import cn.org.codecrafters.simplejwt.constants.TokenAlgorithm;
import cn.org.codecrafters.simplejwt.exceptions.UnsupportedAlgorithmException;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * The {@code AuthzeroTokenResolverConfig} class provides the configuration for
 * the {@link AuthzeroTokenResolver}.
 * <p>
 * This configuration is used to establish the mapping between the standard
 * {@link TokenAlgorithm} defined within the {@link AuthzeroTokenResolver}
 * facade and the specific algorithms used by the {@code com.auth0:java-jwt}
 * library, which is the underlying library used by {@link
 * AuthzeroTokenResolver} to handle JSON Web Tokens (JWTs).
 * <p>
 * <b>Algorithm Mapping:</b>
 * The {@code AuthzeroTokenResolverConfig} allows specifying the relationship
 * between the standard {@link TokenAlgorithm} instances supported by
 * {@link AuthzeroTokenResolver} and the corresponding algorithms used by the
 * {@code com.auth0:java-jwt} library. The mapping is achieved using a Map,
 * where the keys are the standard TokenAlgorithm instances, and the values
 * represent the algorithm functions used by Auth0 Java JWT library for each
 * corresponding key.
 * <p>
 * <b>Note:</b>
 * The provided algorithm mapping should be consistent with the actual
 * algorithms supported and used by the {@code com.auth0:java-jwt} library. It
 * is crucial to ensure that the mapping is accurate to enable proper token
 * validation and processing within the {@link AuthzeroTokenResolver}.
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public final class AuthzeroTokenResolverConfig implements TokenResolverConfig<Function<String, Algorithm>> {

    /**
     * Constructs a new instance of {@code AuthzeroTokenResolverConfig}.
     * <p>
     * The constructor is set as private to enforce the singleton pattern for
     * this configuration class. Instances of
     * {@code AuthzeroTokenResolverConfig} should be obtained through the
     * {@link #getInstance()} method.
     */
    private AuthzeroTokenResolverConfig() {
    }

    /**
     * The singleton instance of {@code AuthzeroTokenResolverConfig}.
     * <p>
     * This instance is used to ensure that only one instance of
     * {@code AuthzeroTokenResolverConfig} is created and shared throughout the
     * application. The singleton pattern is implemented to provide centralised
     * configuration and avoid redundant object creation.
     */
    private static AuthzeroTokenResolverConfig instance;

    /**
     * The supported algorithms and their corresponding algorithm functions.
     * <p>
     * This map stores the supported algorithms as keys and their corresponding
     * algorithm functions as values. The algorithm functions represent the
     * functions used by the {@code com.auth0:java-jwt} library to handle the
     * specific algorithms. The mapping is used to provide proper algorithm
     * resolution and processing within the {@link AuthzeroTokenResolver}.
     */
    private static final Map<TokenAlgorithm, Function<String, Algorithm>> SUPPORTED_ALGORITHMS = new HashMap<>() {{
        put(TokenAlgorithm.HS256, Algorithm::HMAC256);
        put(TokenAlgorithm.HS384, Algorithm::HMAC384);
        put(TokenAlgorithm.HS512, Algorithm::HMAC512);
    }};

    /**
     * Gets the instance of {@code AuthzeroTokenResolverConfig}.
     * <p>
     * This method returns the singleton instance of
     * {@code AuthzeroTokenResolverConfig}. If the instance is not yet created,
     * it will create a new instance and return it. Otherwise, it returns the
     * existing instance.
     *
     * @return the instance of {@code AuthzeroTokenResolverConfig}
     */
    public static AuthzeroTokenResolverConfig getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AuthzeroTokenResolverConfig();
        }

        return instance;
    }

    /**
     * Gets the algorithm function corresponding to the specified
     * {@link TokenAlgorithm}.
     * <p>
     * This method returns the algorithm function associated with the given
     * {@link TokenAlgorithm}. The provided {@link TokenAlgorithm} represents
     * the specific algorithm for which the corresponding algorithm function
     * is required. The returned Algorithm Function represents the function
     * implementation that can be used by the {@link TokenResolver} to handle
     * the specific algorithm.
     *
     * @param algorithm the {@link TokenAlgorithm} for which the algorithm
     *                  function isrequired
     * @return the algorithm function associated with the given {@link
     * TokenAlgorithm}
     * @throws UnsupportedAlgorithmException if the given {@code algorithm} is
     *                                       not supported by this
     *                                       implementation
     */
    @Override
    public Function<String, Algorithm> getAlgorithm(TokenAlgorithm algorithm) {
        return Optional.of(SUPPORTED_ALGORITHMS).map((entry) -> entry.get(algorithm))
                .orElseThrow(() -> new UnsupportedAlgorithmException("The specified algorithm is not supported yet."));
    }
}
