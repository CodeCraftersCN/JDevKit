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

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Utility class for chained high-precision calculations using BigDecimal.
 * <p>
 * The ChainedCalcUtil class provides a convenient way to perform chained
 * high-precision calculations using BigDecimal. It allows users to perform
 * mathematical operations such as addition, subtraction, multiplication,
 * and division with customisable precision and scale. By using this utility
 * class, developers can achieve accurate results and avoid precision loss
 * in their calculations.
 * <p>
 * <b>Usage:</b>
 * <pre>
 *  // Perform addition: 3 + 4
 *  BigDecimal result1 = ChainedCalcUtil.startWith(3)
 *                                      .add(4)
 *                                      .getValue();
 *
 *  // Perform subtraction: 4 - 2
 *  BigDecimal result2 = ChainedCalcUtil.startWith(4)
 *                                      .subtract(2)
 *                                      .getValue();
 *
 *  // Perform multiplication: 3 * 6
 *  BigDecimal result3 = ChainedCalcUtil.startWith(3)
 *                                      .multiply(6)
 *                                      .getValue();
 *
 *  // Perform division: 6 ÷ 2
 *  BigDecimal result4 = ChainedCalcUtil.startWith(6)
 *                                      .divide(2)
 *                                      .getValue();
 *
 *  // Perform division with specified scale: 13 ÷ 7 with a scale of 2
 *  BigDecimal result5 = ChainedCalcUtil.startWith(13)
 *                                      .divideWithScale(7, 2)
 *                                      .getValue();
 *
 *  // Get int, long, or double results
 *  int intResult = ChainedCalcUtil.startWith(3)
 *                                .add(4)
 *                                .getInteger();
 *
 *  long longResult = ChainedCalcUtil.startWith(4)
 *                                  .subtract(2)
 *                                  .getLong();
 *
 *  double doubleResult = ChainedCalcUtil.startWith(6)
 *                                      .divide(2)
 *                                      .getDouble();
 *
 *  // Get BigDecimal result with specified scale
 *  BigDecimal result6 = ChainedCalcUtil.startWith(13)
 *                                      .divide(7)
 *                                      .getValue(2);
 *  </pre>
 * The above expressions perform various mathematical calculations using the
 * ChainedCalcUtil class.
 * <p>
 * <b>Note:</b>
 * The ChainedCalcUtil class internally uses BigDecimal to handle
 * high-precision calculations. It is important to note that BigDecimal
 * operations can be memory-intensive and may have performance implications
 * for extremely large numbers or complex calculations.
 *
 * @author sunzsh
 * @version 1.1.0
 * @see java.math.BigDecimal
 * @since 1.0.0
 */
public final class ChainedCalcUtil {

    /**
     * 计算出来的结果。
     */
    private BigDecimal value;

    /**
     * 创建具有指定初始值的 {@linkplain ChainedCalcUtil} 实例。
     *
     * @param value 计算的初始值
     */
    private ChainedCalcUtil(Number value) {
        this.value = convertBigDecimal(value, null);
    }

    /**
     * 使用给定的数值进行链式运算。
     *
     * @param value 计算的初始值
     * @return 用于执行链式计算的 {@linkplain ChainedCalcUtil} 实例
     */
    public static ChainedCalcUtil startWith(Number value) {
        return new ChainedCalcUtil(value);
    }

    /**
     * 进行加法运算。
     *
     * @param other 要添加到此 {@linkplain ChainedCalcUtil} 的值
     * @return 具有更新值的 {@linkplain ChainedCalcUtil} 实例
     */
    public ChainedCalcUtil add(Number other) {
        return operator(BigDecimal::add, other);
    }

    /**
     * 使用特定的精度进行加法运算。
     *
     * @param other              操作数
     * @param beforeOperateScale 进行运算之前的操作数精度
     * @return 包含 {@code this + other} 的 {@linkplain ChainedCalcUtil} 实例
     */
    public ChainedCalcUtil add(Number other, Integer beforeOperateScale) {
        return operator(BigDecimal::add, other, beforeOperateScale);
    }

    /**
     * 减法运算。
     *
     * @param other 需要减去的数值
     * @return 包含 {@code this - other} 的 {@linkplain ChainedCalcUtil} 实例
     */
    public ChainedCalcUtil subtract(Number other) {
        return operator(BigDecimal::subtract, other);
    }

    /**
     * 使用给定的精度进行减法运算。
     *
     * @param other              需要减去的数值
     * @param beforeOperateScale 进行运算之前的操作数精度
     * @return 包含 {@code this - other} 的 {@linkplain ChainedCalcUtil} 实例
     */
    public ChainedCalcUtil subtract(Number other, Integer beforeOperateScale) {
        return operator(BigDecimal::subtract, other, beforeOperateScale);
    }

    /**
     * 进行乘法运算。
     *
     * @param other 需要乘以的数值
     * @return 包含 {@code this * other} 的 {@linkplain ChainedCalcUtil} 实例
     */
    public ChainedCalcUtil multiply(Number other) {
        return operator(BigDecimal::multiply, other);
    }

    /**
     * 使用给定的精度进行乘法运算。
     *
     * @param other              需要乘以的数值
     * @param beforeOperateScale 进行运算之前的操作数精度
     * @return 包含 {@code this * other} 的 {@linkplain ChainedCalcUtil} 实例
     */
    public ChainedCalcUtil multiply(Number other, Integer beforeOperateScale) {
        return operator(BigDecimal::multiply, other, beforeOperateScale);
    }

    /**
     * 进行除法运算。
     * <p>
     * 该运算方式不能进行结果为无限小数的除法运算。
     *
     * @param other 除数
     * @return 包含 {@code this / other} 的 {@linkplain ChainedCalcUtil} 实例
     */
    public ChainedCalcUtil divide(Number other) {
        return operator(BigDecimal::divide, other);
    }

    /**
     * 进行除法运算。
     *
     * @param other              除数
     * @param beforeOperateScale 进行运算之前的操作数精度
     * @return 包含 {@code this / other} 的 {@linkplain ChainedCalcUtil} 实例
     */
    public ChainedCalcUtil divide(Number other, Integer beforeOperateScale) {
        return operator(BigDecimal::divide, other, beforeOperateScale);
    }

    /**
     * 进行除法运算，并指定计算的精度。
     *
     * @param other 被除数
     * @param scale 小数点后的位数
     * @return 包含 {@code this / other} 的 {@linkplain ChainedCalcUtil} 实例
     */
    public ChainedCalcUtil divideWithScale(Number other, Integer scale) {
        return baseOperator(otherValue ->
                this.value.divide(otherValue, scale, RoundingMode.HALF_UP), other, null);
    }

    /**
     * 进行除法运算，并指定计算的精度。
     *
     * @param other              被除数
     * @param scale              小数点后的位数
     * @param beforeOperateScale 进行运算之前的操作数精度
     * @return 包含 {@code this / other} 的 {@linkplain ChainedCalcUtil} 实例
     */
    public ChainedCalcUtil divideWithScale(Number other, Integer scale, Integer beforeOperateScale) {
        return baseOperator(otherValue -> this.value.divide(otherValue, scale, RoundingMode.HALF_UP), other, beforeOperateScale);
    }

    /**
     * 以具有指定小数位数的 {@link BigDecimal} 形式返回当前值。
     *
     * @param scale 结果的精度
     * @return 具有指定小数位数的 {@link BigDecimal} 形式的当前值
     */
    public BigDecimal getValue(int scale) {
        return value.setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 以 {@link Double} 形式返回当前值。
     *
     * @return {@link Double} 形式的当前值
     */
    public Double getDouble() {
        return value.doubleValue();
    }

    /**
     * 以具有指定小数位数的 {@link Double} 形式返回当前值。
     *
     * @param scale 结果的精度
     * @return 将当前值转换成具指定精度的 {@link Double} 值
     */
    public Double getDouble(int scale) {
        return getValue(scale).doubleValue();
    }

    /**
     * 以 {@link Long} 形式返回当前值。
     *
     * @return {@link Long} 形式的当前值
     */
    public Long getLong() {
        return value.longValue();
    }

    /**
     * 以 {@link Integer} 形式返回当前值。
     *
     * @return {@link Integer} 形式的当前值
     */
    public Integer getInteger() {
        return value.intValue();
    }

    /**
     * 对当前值和另一个值应用指定的运算符函数。
     *
     * @param operator   要应用的操作函数
     * @param otherValue 要操作的操作数
     * @return {@linkplain ChainedCalcUtil} 实例
     */
    private ChainedCalcUtil operator(BiFunction<BigDecimal, BigDecimal, BigDecimal> operator, Object otherValue) {
        return operator(operator, otherValue, 9);
    }

    /**
     * 对当前值和另一个值应用指定的运算符函数。
     *
     * @param operator           要应用的操作函数
     * @param other              要操作的操作数
     * @param beforeOperateScale 操作之前的操作数精度
     * @return {@linkplain ChainedCalcUtil} 实例
     */
    private ChainedCalcUtil operator(BiFunction<BigDecimal, BigDecimal, BigDecimal> operator, Object other, Integer beforeOperateScale) {
        return baseOperator((otherValue) -> operator.apply(this.value, otherValue), other, beforeOperateScale);
    }

    /**
     * 对当前值和另一个值应用指定的运算符函数。
     *
     * @param operatorFunction   要应用的操作函数
     * @param anotherValue       要操作的操作数
     * @param beforeOperateScale 操作之前的操作数精度
     * @return {@linkplain ChainedCalcUtil} 实例
     */
    private synchronized ChainedCalcUtil baseOperator(Function<BigDecimal, BigDecimal> operatorFunction,
                                                      Object anotherValue, Integer beforeOperateScale) {
        if (Objects.isNull(anotherValue)) {
            return this;
        }
        if (anotherValue instanceof ChainedCalcUtil) {
            this.value = operatorFunction.apply(((ChainedCalcUtil) anotherValue).value);
            return this;
        }
        this.value = operatorFunction.apply(convertBigDecimal(anotherValue, beforeOperateScale));
        return this;
    }

    /**
     * 将指定值转换为 {@linkplain BigDecimal}。
     *
     * @param value 要转换的数据
     * @param scale 转换后的精度
     * @return 转换后的 {@linkplain BigDecimal} 实例
     */
    private BigDecimal convertBigDecimal(Object value, Integer scale) {
        if (Objects.isNull(value)) {
            return BigDecimal.ZERO;
        }
        BigDecimal res;
        if (value instanceof Number num) {
            res = BigDecimal.valueOf(num.doubleValue());
        } else {
            try {
                res = new BigDecimal(value.toString());
            } catch (NumberFormatException ignored) {
                return BigDecimal.ZERO;
            }
        }

        if (Objects.nonNull(scale)) {
            return res.setScale(scale, RoundingMode.HALF_UP);
        }
        return res;
    }

}
