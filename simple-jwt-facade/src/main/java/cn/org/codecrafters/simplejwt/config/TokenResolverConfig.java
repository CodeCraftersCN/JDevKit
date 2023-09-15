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

package cn.org.codecrafters.simplejwt.config;

import cn.org.codecrafters.simplejwt.constants.TokenAlgorithm;

/**
 * The TokenResolverConfig interface provides a mechanism to configure a
 * TokenResolver with algorithm functions.
 * <p>
 * This generic interface is used to define the configuration details for a
 * TokenResolver that utilizes algorithm functions. The interface allows for
 * specifying algorithm functions corresponding to different TokenAlgorithm
 * instances supported by the TokenResolver implementation.
 *
 * @param <Algo> the type representing algorithm functions used by the
 *               {@code TokenResolver}
 * @author Zihlu Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public interface TokenResolverConfig<Algo> {

    /**
     * Gets the algorithm function corresponding to the specified
     * TokenAlgorithm.
     * <p>
     * This method returns the algorithm function associated with the given
     * TokenAlgorithm. The provided TokenAlgorithm represents the specific
     * algorithm for which the corresponding algorithm function is required.
     * The returned AlgorithmFunction represents the function implementation
     * that can be used by the TokenResolver to handle the specific algorithm.
     *
     * @param algorithm the TokenAlgorithm for which the algorithm function is
     *                  required
     * @return the algorithm function associated with the given TokenAlgorithm
     */
    Algo getAlgorithm(TokenAlgorithm algorithm);

}
