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

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

/**
 * AES Util 可帮助你使用指定密钥和 AES 算法加密和解密数据。
 * <p>
 * 本工具类作者为 hubin@baomidou
 *
 * @author hubin@baomidou
 * @since 1.1.0
 */
@Slf4j
public final class AesUtil {

    private AesUtil() {
    }

    private static final String AES = "AES";

    private static final String AES_CBC_CIPHER = "AES/CBC/PKCS5Padding";

    /**
     * 使用 AES 算法，用给定密钥对给定数据进行加密。
     *
     * @param data 需要加密的数据
     * @param key  加密数据的密钥
     * @return 加密结果，如果加密失败则为 {@code null}
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            var secretKeySpec = new SecretKeySpec(new SecretKeySpec(key, AES).getEncoded(), AES);
            var cipher = Cipher.getInstance(AES_CBC_CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(key));
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedOperationException |
                 InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 BadPaddingException exception) {
            log.error(exception.getMessage());
            for (var stackTraceElement : exception.getStackTrace()) {
                log.error("{}", stackTraceElement.toString());
            }
        }
        return null;
    }

    /**
     * 使用 AES 算法，用给定密钥解密给定数据。
     *
     * @param data 需要解密的数据
     * @param key  加解密数据的密钥
     * @return 解密结果，如果解密失败则为 {@code null}
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            var secretKeySpec = new SecretKeySpec(new SecretKeySpec(key, AES).getEncoded(), AES);
            var cipher = Cipher.getInstance(AES_CBC_CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(key));
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedOperationException |
                 InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 BadPaddingException exception) {
            log.error(exception.getMessage());
            for (var stackTraceElement : exception.getStackTrace()) {
                log.error("{}", stackTraceElement.toString());
            }
        }
        return null;
    }

    /**
     * 使用 AES 算法，用给定密钥对给定数据进行加密。
     *
     * @param data 需要加密的数据
     * @param key  加密数据的密钥
     * @return 加密结果，如果加密失败则为 {@code null}
     */
    public static String encrypt(String data, String key) {
        return Base64.getEncoder().encodeToString(encrypt(data.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 使用 AES 算法，用给定密钥解密给定数据。
     *
     * @param data 需要解密的数据
     * @param key  加解密数据的密钥
     * @return 解密结果，如果解密失败则为 {@code null}
     */
    public static String decrypt(String data, String key) {
        return new String(Objects.requireNonNull(
                decrypt(Base64.getDecoder().decode(data.getBytes()),
                        key.getBytes(StandardCharsets.UTF_8)))
        );
    }

    /**
     * 生成一个 16 个字符长度的随机密钥。
     *
     * @return 生成的安全密文
     */
    public static String generateRandomKey() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
    }

}
