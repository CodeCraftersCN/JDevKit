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

package cn.org.codecrafters.simplejwt;

import cn.org.codecrafters.simplejwt.exceptions.WeakSecretException;

import java.util.Random;

/**
 * {@code SecretCreator} 是一个实用类，它提供了生成安全秘密字符串的方法。生成的秘密字符串
 * 可用作密码密钥或密码，用于各种安全敏感目的。
 *
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @since 1.0.0
 */
public final class SecretCreator {

    /**
     * 防止实例化的私有构造函数
     */
    private SecretCreator() {
    }

    /**
     * 包含所有小写字符的字符串，可用于生成密文。
     */
    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 包含所有大写字符的字符串，可用于生成密文。
     */
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 包含可用于生成密文的所有数字字符的字符串。
     */
    private static final String DIGITS = "0123456789";

    /**
     * 包含可用于生成密文的所有特殊符号字符的字符串。
     */
    private static final String SPECIAL_SIGNS = "!@#$%^&,*()_+-=,[]{}|;:,'\",.<>/?";

    /**
     * 生成具有指定长度和字符集的安全密文。
     *
     * @param length               要生成的密钥长度
     * @param isContainCapital     指示密钥是否包含大写字母的标志
     * @param isContainDigital     指示密钥是否包含数字的标志
     * @param isContainSpecialSign 指示密钥是否包含特殊字符的标志
     * @return 生成的密钥
     * @throws WeakSecretException 如果要求生成密钥的长度小于 32 个字符
     */
    public static String createSecret(int length,
                                      boolean isContainCapital,
                                      boolean isContainDigital,
                                      boolean isContainSpecialSign) {
        if (length < 32) {
            throw new WeakSecretException("""
                    您请求的密钥仅有 %d 字符长的密钥安全性不足，请考虑更换一个更强的密钥。""".formatted(length));
        }

        final var randomiser = new Random();
        var charset = new StringBuilder(LOWERCASE_CHARACTERS);

        if (isContainCapital) charset.append(UPPERCASE_CHARACTERS);
        if (isContainDigital) charset.append(DIGITS);
        if (isContainSpecialSign) charset.append(SPECIAL_SIGNS);

        var secretBuilder = new StringBuilder();
        var charsetSize = charset.length();
        for (var i = 0; i < length; ++i) {
            secretBuilder.append(charset.charAt(randomiser.nextInt(charsetSize)));
        }

        return secretBuilder.toString();
    }

    /**
     * 生成具有指定长度和字符集的安全密文。
     *
     * @param length               要生成的密钥长度
     * @param isContainCapital     指示密钥是否包含大写字母的标志
     * @param isContainDigital     指示密钥是否包含数字的标志
     * @return 生成的密钥
     * @throws WeakSecretException 如果要求生成密钥的长度小于 32 个字符
     * @see #createSecret(int, boolean, boolean, boolean)
     */
    public static String createSecret(int length,
                                      boolean isContainCapital,
                                      boolean isContainDigital) {
        return createSecret(length, isContainCapital, isContainDigital, false);
    }

    /**
     * 生成具有指定长度和字符集的安全密文。
     *
     * @param length               要生成的密钥长度
     * @param isContainCapital     指示密钥是否包含大写字母的标志
     * @return 生成的密钥
     * @throws WeakSecretException 如果要求生成密钥的长度小于 32 个字符
     * @see #createSecret(int, boolean, boolean, boolean)
     */
    public static String createSecret(int length,
                                      boolean isContainCapital) {
        return createSecret(length, isContainCapital, false, false);
    }

    /**
     * 生成具有指定长度和字符集的安全密文。
     *
     * @param length               要生成的密钥长度
     * @return 生成的密钥
     * @throws WeakSecretException 如果要求生成密钥的长度小于 32 个字符
     * @see #createSecret(int, boolean, boolean, boolean)
     */
    public static String createSecret(int length) {
        return createSecret(length, false, false, false);
    }

}
