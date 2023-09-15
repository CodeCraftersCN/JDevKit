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

package cn.org.codecrafters.devkit.utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * BranchUtil 类提供静态方法，通过利用 lambda 表达式来简化 Java 开发中的条件逻辑。 它提供
 * 了方便的方法，用更简洁和更具表现力的函数结构替换冗长的 if...else 语句。
 * <p>
 * 在处理分支逻辑和条件语句时，开发人员可以使用此实用程序类中的方法来简化代码、增强可读性并促进更
 * 函数式的编程风格。
 * <p>
 * <b>使用案例:</b>
 * <pre>
 * // 如果你想将表达式 exp1 || exp2，你可以使用下面的代码
 * var r1 = BranchUtil.or(1 == 1, 2 == 1)
 *     .handle(() -> "1 is equal to 1 or 2 is equal to 1.");
 *
 * // 如果你需要处理 else 分支，你可以使用下面的代码
 * var r2 = BranchUtil.or(1 == 1, 2 == 1)
 *     .handle(() -> "1 is equal to 1 or 2 is equal to 1.",
 *             () -> "1 is not equal to 1 and 2 is not equal to 1.");
 *
 * // 如果您只需要执行没有返回值的代码：
 * BranchUtil.or(1 == 1, 2 == 1)
 *     .handle(() -> {
 *         // do something
 *     }, () -> {
 *         // do something
 *     });
 * // 如果您只需要一个 if 分支，则可以删除第二个 Supplier 实例。
 *
 * // 要检查所有布尔表达式是否都为 true，请使用 “and” 方法：
 * BranchUtil.and(1 == 1, 2 == 1)
 *     .handle(() -> {
 *         // do something
 *     }, () -> {
 *         // do something
 *     });
 * </pre>
 *
 * <p>
 * <b>注意事项:</b>
 * {@link #and(Boolean...)} 和 {@link #or(Boolean...)} 方法接受任意数量的布尔表达式。
 *
 * @param <T> 方法要处理的结果的类型
 * @author Zihlu Wang
 * @version 1.0.0
 * @see java.util.function.Supplier
 * @see java.util.function.BooleanSupplier
 * @see java.lang.Runnable
 * @since 1.0.0
 */
public final class BranchUtil<T> {

    /**
     * 布尔表达式的最终结果。
     */
    private final boolean result;

    /**
     * 创建一个 {@code BranchUtil} 实例。
     *
     * @param result 布尔表达式的结果
     */
    private BranchUtil(boolean result) {
        this.result = result;
    }

    /**
     * 创建一个 {@code BranchUtil} 实例以对提供的布尔表达式进行逻辑 OR 运算。
     *
     * @param booleans 要计算的布尔表达式
     * @param <T>      方法要处理的结果的类型
     * @return 表示逻辑 OR 运算结果的 {@code BranchUtil} 实例
     */
    public static <T> BranchUtil<T> or(Boolean... booleans) {
        var result = Arrays.stream(booleans)
                .filter(Objects::nonNull)
                .anyMatch(Boolean::booleanValue);
        return new BranchUtil<>(result);
    }

    /**
     * 创建一个 {@code BranchUtil} 实例以对提供的布尔表达式进行逻辑 AND 运算。
     *
     * @param booleans 要计算的布尔表达式
     * @param <T>      方法要处理的结果的类型
     * @return 表示逻辑 AND 运算结果的 {@code BranchUtil} 实例
     */
    public static <T> BranchUtil<T> and(Boolean... booleans) {
        var result = Arrays.stream(booleans)
                .filter(Objects::nonNull)
                .allMatch(Boolean::booleanValue);
        return new BranchUtil<>(result);
    }

    /**
     * 创建一个 {@code BranchUtil} 实例以对提供的布尔表达式进行逻辑 OR 运算。
     *
     * @param booleanSuppliers 要计算的布尔表达式
     * @param <T>              方法要处理的结果的类型
     * @return 表示逻辑 OR 运算结果的 {@code BranchUtil} 实例
     */
    public static <T> BranchUtil<T> or(BooleanSupplier... booleanSuppliers) {
        var result = Arrays.stream(booleanSuppliers)
                .filter(Objects::nonNull)
                .anyMatch(BooleanSupplier::getAsBoolean);
        return new BranchUtil<>(result);
    }

    /**
     * 创建一个 {@code BranchUtil} 实例以对提供的布尔表达式进行逻辑 AND 运算。
     *
     * @param booleanSuppliers 要计算的布尔表达式
     * @param <T>              方法要处理的结果的类型
     * @return 表示逻辑 AND 运算结果的 {@code BranchUtil} 实例
     */
    public static <T> BranchUtil<T> and(BooleanSupplier... booleanSuppliers) {
        var result = Arrays.stream(booleanSuppliers)
                .filter(Objects::nonNull)
                .allMatch(BooleanSupplier::getAsBoolean);
        return new BranchUtil<>(result);
    }

    /**
     * 通过根据结果执行适当的处理程序来处理布尔表达式的结果。
     * <p>
     * 如果结果为 {@code true}，则执行 {@code ifHandler}。 如果结果为
     * {@code false} 并且提供了 {@code elseHandler}，则执行它。
     *
     * @param ifHandler   如果结果为 {@code true} 则执行的处理程序
     * @param elseHandler 如果结果为 {@code false} 则执行的处理程序（可选）
     * @return 执行处理程序的结果，如果未提供 {@code elseHandler} 并且需要使用
     * else 分支，则为 {@code null}
     */
    public T handle(Supplier<T> ifHandler, Supplier<T> elseHandler) {
        if (this.result && Objects.nonNull(ifHandler)) {
            return ifHandler.get();
        }

        if (Objects.isNull(elseHandler)) {
            return null;
        }

        return elseHandler.get();
    }

    /**
     * 通过根据结果执行适当的处理程序来处理布尔表达式的结果。
     * <p>
     * 如果结果为 {@code true}，则执行 {@code ifHandler}。 如果结果为
     * {@code false} 并且提供了 {@code elseHandler}，则执行它。
     *
     * @param ifHandler 如果结果为 {@code true} 则执行的处理程序
     * @return 执行处理程序的结果，如果需要运行 else 分支则返回 {@code null}
     */
    public T handle(Supplier<T> ifHandler) {
        return handle(ifHandler, null);
    }

    /**
     * 通过根据结果执行适当的处理程序来处理布尔表达式的结果。
     * <p>
     * 如果结果为 {@code true}，则执行 {@code ifHandler}。 如果结果为 {@code false}
     * 并且提供了 {@code elseHandler}，则执行它。
     *
     * @param ifHandler   如果结果为 {@code true} 则执行的处理程序
     * @param elseHandler 如果结果为 {@code false} 则执行的处理程序（可选）
     */
    public void handle(Runnable ifHandler, Runnable elseHandler) {
        if (this.result && Objects.nonNull(ifHandler)) {
            ifHandler.run();
            return;
        }

        if (Objects.isNull(elseHandler)) {
            return;
        }

        elseHandler.run();
    }

    /**
     * 通过根据结果执行适当的处理程序来处理布尔表达式的结果。
     * <p>
     * 如果结果为 {@code true}，则执行 {@code ifHandler}。 如果结果为
     * {@code false} 并且提供了 {@code elseHandler}，则执行它。
     *
     * @param ifHandler 如果结果为 {@code true} 则执行的处理程序
     */
    public void handle(Runnable ifHandler) {
        handle(ifHandler, null);
    }

}
