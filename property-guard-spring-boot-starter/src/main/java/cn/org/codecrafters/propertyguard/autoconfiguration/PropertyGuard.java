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

package cn.org.codecrafters.propertyguard.autoconfiguration;

import cn.org.codecrafters.devkit.utils.AesUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.util.HashMap;
import java.util.Optional;

/**
 * {@link PropertyGuard} 是您 Spring Boot 应用中的配置守护者，它将保护您的配置不会因为特殊
 * 情况而泄漏。通常，一个 Spring Boot 应用需要将许多属于机密字段的配置属性填写在 {@code
 * application.yml} 文件中，例如数据库的密码、API 密钥等等。为了保证这些机密内容不被暴露，通过
 * {@link PropertyGuard} 便可以保护您的配置属性不被泄漏。
 * <p>
 * <b>使用方式：</b>
 * 在 {@code application.yml} 或 {@code application.properties} 文件中，你需要将您原
 * 本的内容使用自定义密钥通过 Aes 算法进行加密，并在属性值最前方加上前缀 {@code pg:}。
 * <pre>
 *     # 原始内容
 *     app.example-properties=Sample Value
 *
 *     # 通过密钥 3856faef0d2d4f33 加密后的内容
 *     app.example-properties=pg:t4YBfv8M9ZmTzWgTi2gJqg==
 * </pre>
 * 此后，给您的应用加上如下格式的命令行参数：
 * {@code --pg.key=<您的密钥，如上文案例则为3856faef0d2d4f33>}。
 * <p>
 * 该工具类抽取自 <a href="https://baomidou.com/pages/e0a5ce/">MyBatis Plus</a>。
 *
 * @author hubin@baomidou
 * @version 1.1.0
 * @see org.springframework.boot.env.EnvironmentPostProcessor
 * @since 1.1.0 (MyBatis-Plus 版本 3.3.2)
 */
public class PropertyGuard implements EnvironmentPostProcessor {

    private final String PREFIX = "pg";

    /**
     * Process the encryption environment variables.
     *
     * @param environment the environment to post-process
     * @param application the application to which the environment belongs
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // Get the key for encryption from command line.
        var encryptionKey = "";
        for (var ps : environment.getPropertySources()) {
            if (ps instanceof SimpleCommandLinePropertySource source) {
                encryptionKey = source.getProperty("%s.key".formatted(PREFIX));
                break;
            }
        }

        if (Optional.ofNullable(encryptionKey).map((key) -> !key.isEmpty()).orElse(false)) {
            var map = new HashMap<String, Object>();
            for (var propertySources : environment.getPropertySources()) {
                if (propertySources instanceof OriginTrackedMapPropertySource source) {
                    for (var name : source.getPropertyNames()) {
                        if (source.getProperty(name) instanceof String str) {
                            if (str.startsWith("%s:".formatted(PREFIX))) {
                                map.put(name, AesUtil.decrypt(str.substring(3), encryptionKey));
                            }
                        }
                    }
                }
            }
            // Put the decrypted data into environment variables, and made them at top-level.
            if (!map.isEmpty()) {
                environment.getPropertySources().addFirst(new MapPropertySource("custom-encrypt", map));
            }
        }
    }
}
