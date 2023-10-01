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
 * {@code JjwtTokenResolverConfig} 类提供了 {@link JjwtTokenResolver} 的配置。
 * <p>
 * 该配置类用于建立 {@code JjwtTokenResolverConfig} 中定义的标准
 * {@link TokenAlgorithm} 与 {@code io.jsonwebtoken:jjwt} 库
 * （ {@code JjwtTokenResolver} 用于处理 JSON Web 令牌（JWT）的
 * 底层库）所使用的特定算法之间的映射。
 * <p>
 * <b>算法映射：</b>
 * {@code JjwtTokenResolverConfig} 允许指定 {@link JjwtTokenResolver}
 * 支持的标准 {@link TokenAlgorithm} 实例与 {@code io.jsonwebtoken:jjwt}
 * 库使用的相应算法之间的关系。该映射使用 Map 实现，其中键是标准的 {@link
 * TokenAlgorithm} 实例，值代表 {@code io.jsonwebtoken:jjwt} 库针对每个相应键使用的算法函数。
 * <p>
 * <b>注意事项：</b>
 * 所提供的算法映射应与 {@code io.jsonwebtoken:jjwt} 库支持和使用的实际算法一致。
 * 确保映射的准确性对于在 {@link JjwtTokenResolver} 中正确验证和处理令牌至关重要。
 *
 * @author Zihlu Wang
 * @author Zitai Long
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

    /**
     * 获取 {@code JjwtTokenResolverConfig} 的实例。
     *
     * @return {@code JjwtTokenResolverConfig} 的实例
     */
    public static JjwtTokenResolverConfig getInstance() {
        if (instance == null) {
            instance = new JjwtTokenResolverConfig();
        }

        return instance;
    }

    /**
     * 获取与指定 {@link TokenAlgorithm} 相对应的算法函数。
     * <p>
     * 本方法返回与给定的 {@link TokenAlgorithm} 相关的算法函数。
     * 所提供的 {@link TokenAlgorithm} 代表需要相应算法函数的特定算法。
     * 返回的算法函数代表 {@link TokenResolver} 可用于处理特定算法的函数实现。
     *
     * @param algorithm 需要算法函数的{@link TokenAlgorithm}。
     * @return 与给定的 {@link TokenAlgorithm} 相关联的算法函数
     */
    @Override
    public SignatureAlgorithm getAlgorithm(TokenAlgorithm algorithm) {
        if (!SUPPORTED_ALGORITHMS.containsKey(algorithm)) {
            throw new UnsupportedAlgorithmException("""
                    我们的系统尚不支持该请求算法。请更改为支持的算法。""");
        }
        return SUPPORTED_ALGORITHMS.get(algorithm);
    }
}
