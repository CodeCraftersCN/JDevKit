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

import cn.org.codecrafters.simplejwt.TokenResolver;
import cn.org.codecrafters.simplejwt.constants.TokenAlgorithm;

/**
 * {@code TokenResolverConfig} 提供了一种机制来配置 {@link TokenResolver} 的算法函数。
 * <p>
 * 这个通用接口用于定义使用算法函数的 {@link TokenResolver} 的配置细节。该接口允许指定与
 * {@link TokenResolver} 实现支持的不同 {@link TokenAlgorithm} 实例相对应的算法函数。
 *
 * @param <Algo> 表示 {@link TokenResolver} 使用的算法函数的类型。
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @since 1.0.0
 */
public interface TokenResolverConfig<Algo> {

    /**
     * 获取与指定 {@link TokenAlgorithm} 相对应的算法函数。
     * <p>
     * 本方法返回与给定 {@link TokenAlgorithm} 相关的算法函数。提供的令牌算法
     * （{@link TokenAlgorithm}）代表需要相应算法函数的特定算法。返回的 {@code Algo}
     * 代表 {@link TokenResolver} 可用来处理特定算法的函数实现。
     *
     * @param algorithm 需要的算法函数
     * @return 与给定的 {@link TokenAlgorithm} 相关联的算法函数
     */
    Algo getAlgorithm(TokenAlgorithm algorithm);

}
