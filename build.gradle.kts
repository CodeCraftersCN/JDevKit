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

val projectUrl by extra("https://codecrafters.org.cn/JDevKit")
val projectGithubUrl by extra("https://github.com/CodeCraftersCN/JDevKit")
val globalGroupId by extra("cn.org.codecrafters")
val globalVersion by extra("1.2.2-gradle")
val licenseName by extra("The Apache License, Version 2.0")
val licenseUrl by extra("https://www.apache.org/licenses/LICENSE-2.0.txt")

val logbackVersion: String by project
val junitVersion: String by project
val slf4jVersion: String by project
val lombokVersion: String by project

subprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")

    val implementation by configurations
    val testImplementation by configurations
    val compileOnly by configurations
    val annotationProcessor by configurations
    val testAnnotationProcessor by configurations
    val testCompileOnly by configurations

    dependencies {
        compileOnly("org.slf4j:slf4j-api:$slf4jVersion")
        compileOnly("org.projectlombok:lombok:$lombokVersion")
        implementation("ch.qos.logback:logback-classic:$logbackVersion")
        annotationProcessor("org.slf4j:slf4j-api:$slf4jVersion")
        annotationProcessor("org.projectlombok:lombok:$lombokVersion")

        testCompileOnly("org.slf4j:slf4j-api:$slf4jVersion")
        testCompileOnly("org.projectlombok:lombok:$lombokVersion")
        testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
        testAnnotationProcessor("org.slf4j:slf4j-api:$slf4jVersion")
        testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
    }

    repositories {
        mavenLocal()
        maven(url = "https://codecrafters.coding.net/public-artifacts/base/public/packages/")
        maven(url = "https://maven.proxy.ustclug.org.cn/maven2/")
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}