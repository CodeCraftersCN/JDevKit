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
import java.util.Base64;
import java.util.Objects;

/**
 * {@code Base64Util} 类提供了使用 Base64 编码对字符串进行编码和解码的静态方法。字符串编
 * 码和解码的静态方法。它利用来自 Java 标准库中的 {@link Base64} 类来执行编码和解码操作。
 * 该实用程序类提供了方便的方法，可对不同字符集的字符串进行编码和解码。编码和解码字符串的便捷方法。
 * <p>
 * 该类被设计为具有私有构造函数的最终类，以防止实例化。该类中的所有方法都是静态的，可以方便地访问
 * Base64 编码和解码功能。
 * <p>
 * 使用案例
 * <pre>
 * String original = "Hello, World!";
 *
 * // 使用 UTF-8 字符集为字符串编码
 * String encoded = Base64Util.encode(original);
 * System.out.println("Encoded string: " + encoded);
 *
 * // 使用 UTF-8 字符集为 Base64 字符串解码
 * String decoded = Base64Util.decode(encoded);
 * System.out.println("Decoded string: " + decoded);
 * </pre>
 * <p>
 * <b>注意事项:</b> 如果没有提供特定的字符集，该实用程序类将使用默认字符集（UTF-8）。建议明确
 * 指定字符集，以确保编码和解码的一致性。
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public final class Base64Util {

    private static Base64.Encoder encoder;

    private static Base64.Decoder decoder;

    /**
     * Ensure that there is only one Base64 Encoder.
     *
     * @return the {@link Base64.Encoder} instance
     */
    private static Base64.Encoder getEncoder() {
        if (Objects.isNull(encoder)) {
            encoder = Base64.getEncoder();
        }
        return encoder;
    }

    /**
     * Ensure that there is only one Base64 Encoder.
     *
     * @return the {@link Base64.Encoder} instance
     */
    private static Base64.Decoder getDecoder() {
        if (Objects.isNull(decoder)) {
            decoder = Base64.getDecoder();
        }
        return decoder;
    }

    /**
     * 私有构造函数可防止类的实例化。
     */
    private Base64Util() {
    }

    /**
     * 使用指定的字符集对给定字符串进行编码。
     *
     * @param value   要编码的字符串
     * @param charset 用于编码的字符集
     * @return Base64 编码的字符串
     */
    public static String encode(String value, Charset charset) {
        var encoded = getEncoder().encode(value.getBytes(charset));

        return new String(encoded);
    }

    /**
     * 使用 UTF-8 字符集对给定字符串进行编码。
     *
     * @param value   要编码的字符串
     * @return Base64 编码的字符串
     */
    public static String encode(String value) {
        return encode(value, StandardCharsets.UTF_8);
    }

    /**
     * 使用指定的字符集解码给定的 Base64 编码字符串。
     *
     * @param value   要解码的 Base64 字符串
     * @param charset 用于解码的字符集
     * @return 解码后的字符串
     */
    public static String decode(String value, Charset charset) {
        var decoded = getDecoder().decode(value.getBytes(charset));

        return new String(decoded);
    }

    /**
     * 使用默认的 UTF-8 字符集解码给定的 Base64 编码字符串。
     *
     * @param value 要解码的 Base64 编码字符串
     * @return 解码后的字符串
     */
    public static String decode(String value) {
        return decode(value, StandardCharsets.UTF_8);
    }

}