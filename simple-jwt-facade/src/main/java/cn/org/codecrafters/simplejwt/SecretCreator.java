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
 * SecretCreator is a utility class that provides methods to generate secure
 * secret strings. The generated secrets can be used as cryptographic keys or
 * passwords for various security-sensitive purposes.
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public final class SecretCreator {

    /**
     * Private constructor to prevent instantiation
     */
    private SecretCreator() {
    }

    /**
     * The string containing all lowercase characters that can be used to
     * generate the secret.
     */
    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * The string containing all uppercase characters that can be used to
     * generate the secret.
     */
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * The string containing all digit characters that can be used to generate
     * the secret.
     */
    private static final String DIGITS = "0123456789";

    /**
     * The string containing all special sign characters that can be used to
     * generate the secret.
     */
    private static final String SPECIAL_SIGNS = "!@#$%^&,*()_+-=,[]{}|;:,'\",.<>/?";

    /**
     * Generates a secure secret with the specified length and character sets.
     *
     * @param length               the length of the secret to be generated
     * @param isContainCapital     flag indicating if the secret should contain
     *                             uppercase letters
     * @param isContainDigital     flag indicating if the secret should contain
     *                             digits
     * @param isContainSpecialSign flag indicating if the secret should contain
     *                             special sign characters
     * @return the generated secure secret
     * @throws WeakSecretException if the requested secret length is less than
     *                             32 characters
     */
    public static String createSecret(int length,
                                      boolean isContainCapital,
                                      boolean isContainDigital,
                                      boolean isContainSpecialSign) {
        if (length < 32) {
            throw new WeakSecretException("""
                    The requested secret, which is only %d characters long, is too weak. \
                    Please replace it with a stronger secret.
                    """.formatted(length));
        }

        final var randomizer = new Random();
        var charset = new StringBuilder(LOWERCASE_CHARACTERS);

        if (isContainCapital) charset.append(UPPERCASE_CHARACTERS);
        if (isContainDigital) charset.append(DIGITS);
        if (isContainSpecialSign) charset.append(SPECIAL_SIGNS);

        var password = new StringBuilder();
        var charsetSize = charset.length();
        for (var i = 0; i < length; ++i) {
            password.append(charset.charAt(randomizer.nextInt(charsetSize)));
        }

        return password.toString();
    }

    /**
     * Generates a secure secret with the specified length, containing
     * uppercase letters and digits.
     *
     * @param length           the length of the secret to be generated
     * @param isContainCapital flag indicating if the secret should contain
     *                         uppercase letters
     * @param isContainDigital flag indicating if the secret should contain
     *                         digits
     * @return the generated secure secret
     * @throws WeakSecretException if the requested secret length is less than
     *                             32 characters
     */
    public static String createSecret(int length,
                                      boolean isContainCapital,
                                      boolean isContainDigital) {
        return createSecret(length, isContainCapital, isContainDigital, false);
    }

    /**
     * Generates a secure secret with the specified length, containing
     * uppercase letters.
     *
     * @param length           the length of the secret to be generated
     * @param isContainCapital flag indicating if the secret should contain
     *                         uppercase letters
     * @return the generated secure secret
     * @throws WeakSecretException if the requested secret length is less than
     *                             32 characters
     */
    public static String createSecret(int length,
                                      boolean isContainCapital) {
        return createSecret(length, isContainCapital, false, false);
    }

    /**
     * Generates a secure secret with the specified length, containing
     * lowercase letters.
     *
     * @param length the length of the secret to be generated
     * @return the generated secure secret
     * @throws WeakSecretException if the requested secret length is less than
     *                             32 characters
     */
    public static String createSecret(int length) {
        return createSecret(length, false, false, false);
    }

}
