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
 * PropertyEncryptor is a utility class designed for encrypting configuration
 * information in Spring Boot applications.
 * <p>
 * Spring Boot applications often need to store sensitive configuration details
 * such as database passwords, API keys, etc. To ensure that these sensitive
 * pieces of information are not exposed, developers can utilize the
 * {@code PropertyGuard} class to encrypt and store them within configuration
 * files.
 * <p>
 * <b>Usage</b>
 * In {@code application.yml} or {@code application.properties}:
 * <pre>
 *     # original
 *     app.example-properties=Sample Value
 *
 *     # encrypted with key 3856faef0d2d4f33
 *     app.example-properties=pg:t4YBfv8M9ZmTzWgTi2gJqg==
 * </pre>
 * Then, add the command line arguments like {@code --pe.key=3856faef0d2d4f33}.
 * <p>
 * This class is extracted from <a href="https://baomidou.com/pages/e0a5ce/"
 * >MyBatis-Plus</a>.
 * <p>
 * The prefix to specify the encrypted value is {@code pg}.
 *
 * @author hubin@baomidou
 * @version 1.1.0
 * @see org.springframework.boot.env.EnvironmentPostProcessor
 * @since 1.1.0 (3.3.2 of MyBatis-Plus)
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
