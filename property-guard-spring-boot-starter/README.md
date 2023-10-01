# Property Guard

## Introduction

This feature is designed to protect the security of configurations and data, to a certain extent, to control the flow of developers leading to the leakage of sensitive information.

## Prerequisites

This whole `JDevKit` is developed by **JDK 17**, which means you have to use JDK 17 for better experience. Except this, this module is designed for Spring Boot framework, so you have to install Spring Boot (v3) in your application.

## Installation

### If you are using `Maven`

It is quite simple to install this module by `Maven`. The only thing you need to do is find your `pom.xml` file in the project, then find the `<dependencies>` node in the `<project>` node, and add the following codes to `<dependencies>` node:

```xml
<dependency>
	<groupId>cn.org.codecrafters</groupId>
    <artifactId>property-guard-spring-boot-starter</artifactId>
    <version>${property-guard-spring-boot-starter.version}</version>
</dependency>
```

And run `mvn dependency:get` in your project root folder(i.e., if your `pom.xml` is located at `/path/to/your/project/pom.xml`, then your current work folder should be `/path/to/your/project`), then `Maven` will automatically download the `jar` archive from `Maven Central Repository`. This could be **MUCH EASIER** if you are using IDE(i.e., IntelliJ IDEA), the only thing you need to do is click the refresh button of `Maven`.

If you are restricted using the Internet, and have to make `Maven` offline, you could follow the following steps.

1. Download the `jar` file from any place you can get and transfer the `jar` files to your work computer.
2. Move the `jar` files to your local `Maven` Repository as the path of `/path/to/maven_local_repo/cn/org/codecrafters/property-guard-spring-boot-starter/`.

### If you are using `Gradle`

Add this module to your project with `Gradle` is much easier than doing so with `Maven`.

Find `build.gradle` in the needed project, and add the following code to the `dependencies` closure in the build script:

```groovy
implementation 'cn.org.codecrafters:property-guard-spring-boot-starter:${property-guard-spring-boot-starter.version}'
```

### If you are not using `Maven` or `Gradle`

1. Download the `jar` file from the Internet.
2. Create a folder in your project and name it as a name you like(i.e., for me, I prefer `vendor`).
3. Put the `jar` file to the folder you just created in Step 2.
4. Add this folder to your project `classpath`.

## Usage

First, you need a 16-bit-long secret. If you don't have a good way to get a secret, you could consider using our `cn.org.codecrafters.devkit.utils.AesUtil` or `cn.org.codecrafters.simplejwt.SecretCreator` to create a secret.

For example:
```java
import cn.org.codecrafters.devkit.utils.AesUtil;
import cn.org.codecrafters.simplejwt.SecretCreator;

class GenerateRandomKeySample {
    public static void main(String[] args) {
        var secret1 = AesUtil.generateRandomSecret();
        var secret2 = SecretCreator.createSecret(16, true, true, true);
    }
}
```

Then, remember this secret and encrypt the configuration properties that are required high security. For example:

```java
import cn.org.codecrafters.devkit.utils.AesUtil;

class EncryptSample {
    public static void main(String[] args) {
        var dataNeedEncryption = "Sample Value";
        var key = "3856faef0d2d4f33";
        var encryptedData = AesUtil.encrypt(dataNeedEncryption, key);
    }
}
```

After that, copy the encrypted data to `application.properties` or `application.yml`.

For `yml`:
```yaml
app:
  sample-configuration: pe:t4YBfv8M9ZmTzWgTi2gJqg== # "pe:" is the prefix that declare that this property is encrypted. 
```

For `properties`:
```properties
app.sample-configuration=pe:t4YBfv8M9ZmTzWgTi2gJqg==
```

## Contact

If you have any suggestions, ideas, don't hesitate contacting us via [GitHub Issues](https://github.com/CodeCraftersCN/jdevkit/issues/new) or [Discord Community](https://discord.gg/NQK9tjcBB8).

If you face any bugs while using our library and you are able to fix any bugs in our library, we would be happy to accept pull requests from you on [GitHub](https://github.com/CodeCraftersCN/jdevkit/compare).