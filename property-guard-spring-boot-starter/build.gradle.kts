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

import java.net.URI

val buildGroupId: String by project
val buildVersion: String by project
val projectUrl: String by project
val projectGithubUrl: String by project
val licenseName: String by project
val licenseUrl: String by project

val springBootVersion: String by project

group = buildGroupId
version = buildVersion

dependencies {
    implementation(project(":devkit-utils"))
    implementation("org.springframework.boot:spring-boot-autoconfigure:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-logging:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("propertyGuardSpringBootStarter") {
            groupId = buildGroupId
            artifactId = "property-guard-spring-boot-starter"
            version = buildVersion

            pom {
                name = "Property Guard Spring Boot Starter"
                description = "This module is made for protecting your secret properties in your spring application properties."
                url = projectUrl

                licenses {
                    license {
                        name = licenseName
                        url = licenseUrl
                    }
                }
                
                scm {
                    connection = "scm:git:git://github.com:CodeCraftersCN/JDevKit.git"
                    developerConnection = "scm:git:git://github.com:CodeCraftersCN/JDevKit.git"
                    url = projectGithubUrl
                }

                developers {
                    developer {
                        id = "zihluwang"
                        name = "Zihlu Wang"
                        email = "really@zihlu.wang"
                        timezone = "Asia/Hong_Kong"
                    }
                }
            }

            from(components["java"])

            signing {
                sign(publishing.publications["propertyGuardSpringBootStarter"])
            }
        }

        repositories {
            maven {
                name = "sonatypeNexus"
                url = URI(providers.gradleProperty("repo.maven-central.host").get())
                credentials {
                    username = providers.gradleProperty("repo.maven-central.username").get()
                    password = providers.gradleProperty("repo.maven-central.password").get()
                }
            }

            maven {
                name = "codingNexus"
                url = URI(providers.gradleProperty("repo.coding.host").get())
                credentials {
                    username = providers.gradleProperty("repo.coding.username").get()
                    password = providers.gradleProperty("repo.coding.password").get()
                }
            }

            maven {
                name = "githubPackages"
                url = URI(providers.gradleProperty("repo.github.host").get())
                credentials {
                    username = providers.gradleProperty("repo.github.username").get()
                    password = providers.gradleProperty("repo.github.password").get()
                }
            }
        }
    }
}