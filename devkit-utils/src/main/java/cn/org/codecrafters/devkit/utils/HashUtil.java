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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

/**
 * 对字符串执行散列操作的实用程序类。
 * <p>
 * HashUtil 类提供了计算字符串上各种散列函数的便捷方法，包括 MD2、MD5、SHA-1、SHA-224、
 * SHA-256、SHA-384 和 SHA-512。它允许开发人员使用不同的算法轻松获取给定字符串的哈希值。
 * <p>
 * 使用案例：
 * <pre>
 * // 执行 MD2 哈希运算
 * String md2Hash = HashUtil.md2("someString");
 *
 * // 执行 MD5 哈希运算
 * String md5Hash = HashUtil.md5("someString");
 *
 * // 执行 SHA-1 哈希运算
 * String sha1Hash = HashUtil.sha1("someString");
 *
 * // 执行 SHA-224 哈希运算
 * String sha224Hash = HashUtil.sha224("someString");
 *
 * // 执行 SHA-256 哈希运算
 * String sha256Hash = HashUtil.sha256("someString");
 *
 * // 执行 SHA-384 哈希运算
 * String sha384Hash = HashUtil.sha384("someString");
 *
 * // 执行 SHA-512 哈希运算
 * String sha512Hash = HashUtil.sha512("someString");
 * </pre>
 * 上述示例演示了如何使用 HashUtil 类，使用不同的算法计算给定字符串的哈希值。
 * <p>
 * <b>请注意：</b>
 * HashUtil 类提供的散列函数是单向散列函数，这意味着无法从散列值中检索到原始数据。这些散列函数通常用于数据
 * 完整性检查和密码存储，但不能用于加密目的。
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @see java.security.MessageDigest
 * @since 1.0.0
 */
public final class HashUtil {

    /**
     * 防止实例化的私有构造函数
     */
    private HashUtil() {
    }

    /**
     * 使用指定的算法和字符集计算指定字符串的哈希值。
     *
     * @param method  使用的哈希算法
     * @param value   要计算哈希值的字符串
     * @param charset 字符集（如果为空，默认为 UTF-8）
     * @return 以十六进制字符串表示的哈希值，如果算法不可用，则表示空字符串
     * @throws RuntimeException 如果提供了未知的算法名称（在受控使用情况下不应出现这种情况）
     */
    private static String hash(String method, String value, Charset charset) {
        try {
            var messageDigest = MessageDigest.getInstance(method);
            messageDigest.update(value.getBytes(charset));
            var bytes = messageDigest.digest();
            var builder = new StringBuilder();

            for (var b : bytes) {
                var str = Integer.toHexString(b & 0xff);
                if (str.length() == 1) {
                    builder.append(0);
                }
                builder.append(str);
            }

            return builder.toString();
        } catch (NoSuchAlgorithmException ignored) {
            // 在受控使用情况下不应出现这种情况
            // 只允许使用受信任的算法
            return "";
        }
    }

    /**
     * 使用给定的字符集计算指定字符串的 MD2 哈希值。
     *
     * @param value   要使用 MD2 算法计算哈希值的字符串
     * @param charset 字符集（如果为空，默认为 UTF-8）
     * @return 以十六进制字符串表示的 MD2 哈希值
     */
    public static String md2(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("MD2", value, charset);
    }

    /**
     * 使用 UTF-8 字符集计算指定字符串的 MD2 哈希值。
     *
     * @param value 要使用 MD2 算法计算的字符串
     * @return 以十六进制字符串表示的 MD2 哈希值
     */
    public static String md2(String value) {
        return hash("MD2", value, StandardCharsets.UTF_8);
    }

    /**
     * 使用给定的编码计算字符串的 MD5 哈希值。
     *
     * @param value   要使用 MD5 算法计算的字符串
     * @param charset 字符集（如果为空，默认为 UTF-8）
     * @return 以十六进制字符串表示的 MD5 哈希值
     */
    public static String md5(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("MD5", value, charset);
    }

    /**
     * 使用 UTF-8 字符集计算指定字符串的 MD5 哈希值。
     *
     * @param value 要使用 MD5 算法计算的字符串
     * @return 以十六进制字符串表示的 MD5 哈希值
     */
    public static String md5(String value) {
        return hash("MD5", value, StandardCharsets.UTF_8);
    }

    /**
     * 使用给定的编码计算字符串的 SHA-1 哈希值。
     *
     * @param value   要使用 SHA-1 算法计算的字符串
     * @param charset 字符集（如果为空，默认为 UTF-8）
     * @return 以十六进制字符串表示的 SHA-1 哈希值
     */
    public static String sha1(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("SHA-1", value, charset);
    }

    /**
     * 使用 UTF-8 字符集计算指定字符串的 SHA-1 哈希值。
     *
     * @param value 要使用 SHA-1 算法计算的字符串
     * @return 以十六进制字符串表示的 SHA-1 哈希值
     */
    public static String sha1(String value) {
        return hash("SHA-1", value, StandardCharsets.UTF_8);
    }

    /**
     * 使用给定的编码计算字符串的 SHA-224 哈希值。
     *
     * @param value   要使用 SHA-224 算法计算的字符串
     * @param charset 字符集（如果为空，默认为 UTF-8）
     * @return 以十六进制字符串表示的 SHA-224 哈希值
     */
    public static String sha224(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("SHA-224", value, charset);
    }

    /**
     * 使用 UTF-8 字符集计算指定字符串的 SHA-224 哈希值。
     *
     * @param value 要使用 SHA-224 算法计算的字符串
     * @return 以十六进制字符串表示的 SHA-224 哈希值
     */
    public static String sha224(String value) {
        return hash("SHA-224", value, StandardCharsets.UTF_8);
    }

    /**
     * 使用给定的编码计算字符串的 SHA-256 哈希值。
     *
     * @param value   要使用 SHA-256 算法计算的字符串
     * @param charset 字符集（如果为空，默认为 UTF-8）
     * @return 以十六进制字符串表示的 SHA-256 哈希值
     */
    public static String sha256(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("SHA-256", value, charset);
    }

    /**
     * 使用 UTF-8 字符集计算指定字符串的 SHA-256 哈希值。
     *
     * @param value 要使用 SHA-256 算法计算的字符串
     * @return 以十六进制字符串表示的 SHA-256 哈希值
     */
    public static String sha256(String value) {
        return hash("SHA-256", value, StandardCharsets.UTF_8);
    }

    /**
     * 使用给定的编码计算字符串的 SHA-384 哈希值。
     *
     * @param value   要使用 SHA-384 算法计算的字符串
     * @param charset 字符集（如果为空，默认为 UTF-8）
     * @return 以十六进制字符串表示的 SHA-384 哈希值
     */
    public static String sha384(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("SHA-384", value, charset);
    }

    /**
     * 使用 UTF-8 字符集计算指定字符串的 SHA-384 哈希值。
     *
     * @param value 要使用 SHA-384 算法计算的字符串
     * @return 以十六进制字符串表示的 SHA-384 哈希值
     */
    public static String sha384(String value) {
        return hash("SHA-384", value, StandardCharsets.UTF_8);
    }

    /**
     * 使用给定的编码计算字符串的 SHA-512 哈希值。
     *
     * @param value   要使用 SHA-512 算法计算的字符串
     * @param charset 字符集（如果为空，默认为 UTF-8）
     * @return 以十六进制字符串表示的 SHA-512 哈希值
     */
    public static String sha512(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("SHA-512", value, charset);
    }

    /**
     * 使用 UTF-8 字符集计算指定字符串的 SHA-512 哈希值。
     *
     * @param value 要使用 SHA-512 算法计算的字符串
     * @return 以十六进制字符串表示的 SHA-512 哈希值
     */
    public static String sha512(String value) {
        return hash("SHA-512", value, StandardCharsets.UTF_8);
    }

}
