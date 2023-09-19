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

package cn.org.codecrafters.simplejwt.jjwt.config;

import cn.org.codecrafters.simplejwt.TokenResolver;
import cn.org.codecrafters.simplejwt.config.TokenResolverConfig;
import cn.org.codecrafters.simplejwt.constants.TokenAlgorithm;
import cn.org.codecrafters.simplejwt.exceptions.UnsupportedAlgorithmException;
import cn.org.codecrafters.simplejwt.jjwt.JjwtTokenResolver;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code JjwtTokenResolverConfig} class provides the configuration for the
 * {@link JjwtTokenResolver}.
 * <p>
 * This configuration class is used to establish the mapping between the
 * standard {@link TokenAlgorithm} defined within the
 * {@code JjwtTokenResolverConfig} and the specific algorithms used by the
 * {@code io.jsonwebtoken:jjwt} library, which is the underlying library used
 * by {@code JjwtTokenResolver} to handle JSON Web Tokens (JWTs).
 * <p>
 * <b>Algorithm Mapping:</b>
 * The {@code JjwtTokenResolverConfig} allows specifying the relationship
 * between the standard {@link TokenAlgorithm} instances supported by {@link
 * JjwtTokenResolver} and the corresponding algorithms used by the
 * {@code io.jsonwebtoken:jjwt} library. The mapping is achieved using a Map,
 * where the keys are the standard {@link TokenAlgorithm} instances, and the
 * values represent the algorithm functions used by
 * {@code io.jsonwebtoken:jjwt} library for each corresponding key.
 * <p>
 * <b>Note:</b>
 * The provided algorithm mapping should be consistent with the actual
 * algorithms supported and used by the {@code io.jsonwebtoken:jjwt} library.
 * It is crucial to ensure that the mapping is accurate to enable proper token
 * validation and processing within the {@link JjwtTokenResolver}.
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public final class JjwtTokenResolverConfig implements TokenResolverConfig<SignatureAlgorithm> {

    private JjwtTokenResolverConfig() {
    }

    private static final Map<TokenAlgorithm, SignatureAlgorithm> SUPPORTED_ALGORITHMS = new HashMap<>() {{
        put(TokenAlgorithm.HS256, SignatureAlgorithm.HS256);
        put(TokenAlgorithm.HS384, SignatureAlgorithm.HS384);
        put(TokenAlgorithm.HS512, SignatureAlgorithm.HS512);
    }};

    private static JjwtTokenResolverConfig instance;

    public static JjwtTokenResolverConfig getInstance() {
        if (instance == null) {
            instance = new JjwtTokenResolverConfig();
        }

        return instance;
    }

    /**
     * Gets the algorithm function corresponding to the specified
     * {@link TokenAlgorithm}.
     * <p>
     * This method returns the algorithm function associated with the given
     * {@link TokenAlgorithm}. The provided {@link TokenAlgorithm} represents
     * the specific algorithm for which the corresponding algorithm function is
     * required. The returned algorithm function represents the function
     * implementation that can be used by the {@link TokenResolver} to handle
     * the specific algorithm.
     *
     * @param algorithm the {@link TokenAlgorithm} for which the algorithm
     *                  function is required
     * @return the algorithm function associated with the given {@link
     * TokenAlgorithm}
     */
    @Override
    public SignatureAlgorithm getAlgorithm(TokenAlgorithm algorithm) {
        if (!SUPPORTED_ALGORITHMS.containsKey(algorithm)) {
            throw new UnsupportedAlgorithmException("""
                    The request algorithm is not supported by our system yet. Please change to supported ones.""");
        }
        return SUPPORTED_ALGORITHMS.get(algorithm);
    }
}
