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

package cn.org.codecrafters.webcal;

import cn.org.codecrafters.webcal.config.Classification;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * {@code WebCalendarEvent} 类表示网络日历中的一个事件。
 * 它扩展了抽象类 WebCalendarNode，并提供了用于设置事件特定属性的附加方法。
 * <p>
 * 用户可使用该类中的方法为事件添加类别、设置分类、添加注释、描述、位置、设置完成百分比、设置优先级、设置摘要、
 * 设置开始时间、设置结束时间、设置持续时间、设置 URL、设置 UID 和设置时区。设置完属性后，用户可以调用
 * {@link #resolve()}方法为事件生成相应的 iCalendar 内容。
 *
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @since 1.0.0
 */
public final class WebCalendarEvent extends WebCalendarNode {

    private final static String TAG = "VEVENT";

    /**
     * 为事件添加类别。
     *
     * @param categories 要添加的类别
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent addCategories(String... categories) {
        this.categories.addAll(Arrays.asList(categories));
        return this;
    }

    /**
     * 为事件添加类别集合。
     *
     * @param categories 要添加的类别集合
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent addCategories(Collection<String> categories) {
        this.categories.addAll(categories);
        return this;
    }

    /**
     * 为活动添加一个类别。
     *
     * @param category 要添加的类别
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent addCategory(String category) {
        this.categories.add(category);
        return this;
    }

    /**
     * 设置事件的分类。
     *
     * @param classification 设定的分类
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent setClassification(Classification classification) {
        this.classification = classification;
        return this;
    }

    /**
     * 为事件设置注释。
     *
     * @param comment 要设置的注释
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent setComment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * 设置事件描述。
     *
     * @param description 要设置的说明
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 设置活动地点。
     *
     * @param location 设置的位置
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * 设置事件的完成百分比。
     *
     * @param percentComplete 设置的完成百分比
     * @return WebCalendarEvent 对象
     * @throws IllegalArgumentException 如果完成百分比超出范围（0 ~ 100）
     */
    public WebCalendarEvent setPercentComplete(Integer percentComplete) {
        if (percentComplete < 0 || percentComplete > 100) {
            throw new IllegalArgumentException("超出范围的百分比（0 ~ 100）");
        }
        this.percentComplete = percentComplete;
        return this;
    }

    /**
     * 设置事件的优先级。
     *
     * @param priority 设定的优先级
     * @return WebCalendarEvent 对象
     * @throws IllegalArgumentException 如果优先级超出范围 (0 ~ 9)
     */
    public WebCalendarEvent setPriority(Integer priority) {
        if (priority < 0 || priority > 9) {
            throw new IllegalArgumentException("您提供的优先级超出了范围（0 ~ 9）。");
        }
        this.priority = priority;
        return this;
    }

    /**
     * 设置事件摘要。
     *
     * @param summary 设置的摘要
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    /**
     * 设置事件的结束时间。
     *
     * @param end 设定的结束时间
     * @return WebCalendarEvent 对象
     * @throws IllegalStateException 如果字段 DURATION 之前已经设置过
     */
    public WebCalendarEvent setEnd(LocalDateTime end) {
        if (this.duration != null) {
            throw new IllegalStateException("您之前设置了 DURATION 字段，请删除它或删除 setEnd。");
        }
        this.end = end;
        return this;
    }

    /**
     * 设置事件的开始时间。
     *
     * @param start 设定的起始时间
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent setStart(LocalDateTime start) {
        this.start = start;
        return this;
    }

    /**
     * 设置事件的持续时间。
     *
     * @param duration 设定的持续时间
     * @return WebCalendarEvent 对象
     * @throws IllegalStateException 如果字段END 在
     */
    public WebCalendarEvent setDuration(Duration duration) {
        if (this.end != null) {
            throw new IllegalStateException("您之前设置了字段 END，请删除它或删除 setDuration。");
        }
        this.duration = duration;
        return this;
    }

    /**
     * 设置事件的 URL。
     *
     * @param url 要设置的 URL
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 设置事件的 UID。
     *
     * @param uid 要设置的 UID
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent setUid(String uid) {
        this.uid = uid;
        return this;
    }

    /**
     * 设置事件的域名。
     *
     * @param domainName 要设置的域名要设置的域名
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent setDomainName(String domainName) {
        this.domainName = domainName;
        return this;
    }

    /**
     * 设置事件的时区。
     *
     * @param timezone 要设置的时区
     * @return WebCalendarEvent 对象
     */
    public WebCalendarEvent setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    /**
     * 生成并解析事件的 iCalendar 内容。
     *
     * @return 为事件解析的 iCalendar 内容
     */
    @Override
    public String resolve() {
        return "\nBEGIN:" + TAG + "\n" +
                "UID:" + Optional.ofNullable(uid).orElse(UUID.randomUUID().toString()) + "@" + domainName + "\n" +
                Optional.ofNullable(summary).map((item) -> "SUMMARY:" + item + "\n").orElse("") +
                "DTSTART" + Optional.ofNullable(timezone).map(item -> ";TZID=" + item).orElse("") + ":" + start.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\n" +
                Optional.ofNullable(categories)
                        .map((item) -> {
                            if (!item.isEmpty()) {
                                return "CATEGORIES:" + resolveCategories() + "\n";
                            }
                            return null;
                        }).orElse("") +
                Optional.ofNullable(duration)
                        .map((item) -> "DURATION:PT" + item.getSeconds() + "S\n").orElse("") +
                Optional.ofNullable(end)
                        .map((item) -> "DTEND" + Optional.ofNullable(timezone).map(tz -> ";TZID=" + tz).orElse("") + ":" +
                                end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\n").orElse("") +
                Optional.ofNullable(classification)
                        .map((item) -> "CLASS:" + item.name() + "\n").orElse("") +
                Optional.ofNullable(comment).map((item) -> "COMMENT:" + item + "\n").orElse("") +
                Optional.ofNullable(description).map((item) -> "DESCRIPTION:" + item + "\n").orElse("") +
                Optional.ofNullable(location).map((item) -> "LOCATION:" + item + "\n").orElse("") +
                Optional.ofNullable(percentComplete).map((item) -> "PERCENT-COMPLETE:" + item + "\n").orElse("") +
                Optional.ofNullable(priority).map((item) -> "PRIORITY:" + item + "\n").orElse("") +
                "END:" + TAG + "\n";
    }

}
