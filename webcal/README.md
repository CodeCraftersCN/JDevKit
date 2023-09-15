# Module `webcal`

## Introduction

The module `webcal` is a Java library that facilitates the generation and resolution of iCalendar content for web-based calendar applications. It provides a flexible and easy-to-use API for creating web calendars with customisable settings and events.

### Key features

- Create and manage web calendars with events, including event details such as summary, description, location, and more.
- Define event classifications and categories for better organisation and filtering of calendar data.
- Set event start and end times, durations, and time zones to handle various scheduling scenarios.
- Configure event priorities and completion percentages for visual representation in the calendar.
- Generate iCalendar format output suitable for web calendar applications.

With the `webcal` module, developers can easily integrate calendar functionality into web applications, enabling users to view, add, and manage events in a structured and standardized format. It is designed to simplify calendar-related tasks and enhance the overall user experience when dealing with calendar data on the web.

Please note that the `webcal` module adheres to the iCalendar standard specified in RFC 5545, ensuring compatibility with other calendar applications that support this format.

## Prerequisites

- This whole `JDevKit` is developed by **JDK 17**, which means you have to use JDK 17 for better experience.

## Installation

## Installation

### If you are using `Maven`

It is quite simple to install this module by `Maven`. The only thing you need to do is find your `pom.xml` file in the project, then find the `<dependencies>` node in the `<project>` node, and add the following codes to `<dependencies>` node:

```xml
<dependency>
	<groupId>${implementation-builder-group-id}</groupId>
    <artifactId>simple-jwt-${any-implementation}</artifactId>
    <version>${simple-jwt-${any-implementation}.version}</version>
</dependency>
<dependency>
	<groupId>cn.org.codecrafters</groupId>
    <artifactId>simple-jwt-spring-boot-starter</artifactId>
    <version>${simple-jwt-spring-boot-starter.version}</version>
</dependency>
```

And run `mvn dependency:get` in your project root folder(i.e., if your `pom.xml` is located at `/path/to/your/project/pom.xml`, then your current work folder should be `/path/to/your/project`), then `Maven` will automatically download the `jar` archive from `Maven Central Repository`. This could be **MUCH EASIER** if you are using IDE(i.e., IntelliJ IDEA), the only thing you need to do is click the refresh button of `Maven`.

If you are restricted using the Internet, and have to make `Maven` offline, you could follow the following steps.

1. Download the `jar` file from any place you can get and transfer the `jar` files to your work computer.
2. Move the `jar` files to your local `Maven` Repository as the path of `/path/to/maven_local_repo/cn/org/codecrafters/simple-jwt-spring-boot-starter/` and `/path/to/maven_local_repo/${implementation-builder-group-seperated-by-system-seperator}/${implementation_artifact_id}`.

### If you are using `Gradle`

Add this module to your project with `Gradle` is much easier than doing so with `Maven`.

Find `build.gradle` in the needed project, and add the following code to the `dependencies` closure in the build script:

```groovy
implementation '${implementation-builder-group-id}:simple-jwt-${any-implementation}:${simple-jwt-${any-implementation}.version}'
implementation 'cn.org.codecrafters:simple-jwt-spring-boot-starter:${simple-jwt-spring-boot-starter.version}'
```

### If you are not using `Maven` or `Gradle`

1. Download the `jar` file from the Internet.
2. Create a folder in your project and name it as a name you like(i.e., for me, I prefer `vendor`).
3. Put the `jar` file to the folder you just created in Step 2.
4. Add this folder to your project `classpath`.

## Create a web calendar

```java
var calendar = new WebCalendar()
    // set the properties for web calendar here.
    ;
```

## Add a calendar node to this calendar

```java
calendar.addNode(new WebCalendarNode()
                // set properties for this node here.
                );
```

## Resolve this calendar.

```java
var calendarString = calendar.resolve();
```

## Contact

If you have any suggestions, ideas, don't hesitate contacting us via [GitHub Issues](https://github.com/CodeCraftersCN/jdevkit/issues/new) or [Discord Community](https://discord.gg/NQK9tjcBB8). 

If you face any bugs while using our library and you are able to fix any bugs in our library, we would be happy to accept pull requests from you on [GitHub](https://github.com/CodeCraftersCN/jdevkit/compare).