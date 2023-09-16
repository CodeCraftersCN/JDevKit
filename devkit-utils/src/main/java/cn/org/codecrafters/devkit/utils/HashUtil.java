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
 * Utility class for performing hash operations on strings.
 * <p>
 * The HashUtil class provides convenient methods for calculating various hash
 * functions on strings, including MD2, MD5, SHA-1, SHA-224, SHA-256, SHA-384,
 * and SHA-512. It allows developers to easily obtain the hash value of a given
 * string using different algorithms.
 * <p>
 * Example usage:
 * <pre>
 * // Perform MD2 hash operation
 * String md2Hash = HashUtil.md2("someString");
 *
 * // Perform MD5 hash operation
 * String md5Hash = HashUtil.md5("someString");
 *
 * // Perform SHA-1 hash operation
 * String sha1Hash = HashUtil.sha1("someString");
 *
 * // Perform SHA-224 hash operation
 * String sha224Hash = HashUtil.sha224("someString");
 *
 * // Perform SHA-256 hash operation
 * String sha256Hash = HashUtil.sha256("someString");
 *
 * // Perform SHA-384 hash operation
 * String sha384Hash = HashUtil.sha384("someString");
 *
 * // Perform SHA-512 hash operation
 * String sha512Hash = HashUtil.sha512("someString");
 * </pre>
 * The above examples demonstrate how to use the HashUtil class to calculate
 * hash values for a given string using different algorithms.
 * <p>
 * <b>Note:</b>
 * The hash functions provided by the HashUtil class are one-way hash
 * functions, meaning the original data cannot be retrieved from the hash
 * value. These hash functions are commonly used for data integrity checks and
 * password storage, but they should not be used for encryption purposes.
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @see java.security.MessageDigest
 * @since 1.0.0
 */
public final class HashUtil {

    /**
     * Private constructor to prevent instantiation
     */
    private HashUtil() {
    }

    /**
     * Calculates the hash value of the specified string using the specified
     * algorithm and charset.
     *
     * @param method  the hash algorithm to use
     * @param value   the string to calculate the hash value for
     * @param charset the charset to use for encoding the string (default is
     *                UTF-8 if null)
     * @return the hash value as a hexadecimal string, or an empty string if
     * the algorithm is not available
     * @throws RuntimeException if an unknown algorithm name is provided
     *                          (should not occur under controlled usage)
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
            // This should not occur under controlled usage
            // Only trusted algorithms are allowed
            return "";
        }
    }

    /**
     * Calculates the MD2 hash value of the specified string using the given
     * charset.
     *
     * @param value   the string to calculate with the MD2 algorithm
     * @param charset the charset to use for encoding the string (default is
     *                UTF-8 if null)
     * @return the MD2 hash value as a hexadecimal string
     */
    public static String md2(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("MD2", value, charset);
    }

    /**
     * Calculates the MD2 hash value of the specified string using the UTF-8
     * charset.
     *
     * @param value the string to calculate with the MD2 algorithm
     * @return the MD2 hash value as a hexadecimal string
     */
    public static String md2(String value) {
        return hash("MD2", value, StandardCharsets.UTF_8);
    }

    /**
     * Calculates the MD5 hash value of the specified string using the given
     * charset.
     *
     * @param value   the string to calculate with the MD5 algorithm
     * @param charset the charset to use for encoding the string (default is
     *                UTF-8 if null)
     * @return the MD5 hash value as a hexadecimal string
     */
    public static String md5(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("MD5", value, charset);
    }

    /**
     * Calculates the MD5 hash value of the specified string using the UTF-8
     * charset.
     *
     * @param value the string to calculate with the MD5 algorithm
     * @return the MD5 hash value as a hexadecimal string
     */
    public static String md5(String value) {
        return hash("MD5", value, StandardCharsets.UTF_8);
    }

    /**
     * Calculates the SHA-1 hash value of the specified string using the given
     * charset.
     *
     * @param value   the string to calculate with the SHA-1 algorithm
     * @param charset the charset to use for encoding the string (default is
     *                UTF-8 if null)
     * @return the SHA-1 hash value as a hexadecimal string
     */
    public static String sha1(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("SHA-1", value, charset);
    }

    /**
     * Calculates the SHA-1 hash value of the specified string using the UTF-8
     * charset.
     *
     * @param value the string to calculate with the SHA-1 algorithm
     * @return the SHA-1 hash value as a hexadecimal string
     */
    public static String sha1(String value) {
        return hash("SHA-1", value, StandardCharsets.UTF_8);
    }

    /**
     * Calculates the SHA-224 hash value of the specified string using the
     * given charset.
     *
     * @param value   the string to calculate with the SHA-225 algorithm
     * @param charset the charset to use for encoding the string (default is
     *                UTF-8 if null)
     * @return the SHA-224 hash value as a hexadecimal string
     */
    public static String sha224(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("SHA-224", value, charset);
    }

    /**
     * Calculates the SHA-224 hash value of the specified string using the
     * UTF-8 charset.
     *
     * @param value the string to calculate with the SHA-224 algorithm
     * @return the SHA-224 hash value as a hexadecimal string
     */
    public static String sha224(String value) {
        return hash("SHA-224", value, StandardCharsets.UTF_8);
    }

    /**
     * Calculates the SHA-256 hash value of the specified string using the
     * given charset.
     *
     * @param value   the string to calculate with the SHA-256 algorithm
     * @param charset the charset to use for encoding the string (default is
     *                UTF-8 if null)
     * @return the SHA-256 hash value as a hexadecimal string
     */
    public static String sha256(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("SHA-256", value, charset);
    }

    /**
     * Calculates the SHA-256 hash value of the specified string using the
     * UTF-8 charset.
     *
     * @param value the string to calculate with the SHA-256 algorithm
     * @return the SHA-256 hash value as a hexadecimal string
     */
    public static String sha256(String value) {
        return hash("SHA-256", value, StandardCharsets.UTF_8);
    }

    /**
     * Calculates the SHA-384 hash value of the specified string using the
     * given charset.
     *
     * @param value   the string to calculate with the SHA-384 algorithm
     * @param charset the charset to use for encoding the string (default is
     *                UTF-8 if null)
     * @return the SHA-384 hash value as a hexadecimal string
     */
    public static String sha384(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("SHA-384", value, charset);
    }

    /**
     * Calculates the SHA-384 hash value of the specified string using the
     * UTF-8 charset.
     *
     * @param value the string to calculate with the SHA-384 algorithm
     * @return the SHA-384 hash value as a hexadecimal string
     */
    public static String sha384(String value) {
        return hash("SHA-384", value, StandardCharsets.UTF_8);
    }

    /**
     * Calculates the SHA-512 hash value of the specified string using the
     * given charset.
     *
     * @param value   the string to calculate with the SHA-512 algorithm
     * @param charset the charset to use for encoding the string (default is
     *                UTF-8 if null)
     * @return the SHA-512 hash value as a hexadecimal string
     */
    public static String sha512(String value, Charset charset) {
        charset = Optional.ofNullable(charset).orElse(StandardCharsets.UTF_8);
        return hash("SHA-512", value, charset);
    }

    /**
     * Calculates the SHA-512 hash value of the specified string using the
     * UTF-8 charset.
     *
     * @param value the string to calculate with the SHA-512 algorithm
     * @return the SHA-512 hash value as a hexadecimal string
     */
    public static String sha512(String value) {
        return hash("SHA-512", value, StandardCharsets.UTF_8);
    }

}
