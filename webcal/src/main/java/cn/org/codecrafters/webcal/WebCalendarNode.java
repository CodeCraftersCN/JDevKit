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
import java.util.ArrayList;
import java.util.List;

/**
 * 抽象封存类 {@code WebCalendarNode} 表示网络日历中的一个节点，如事件、待办事项或闹钟。
 * 它为所有日历组件和事件提供了通用属性和方法。
 * <p>
 * {@code WebCalendarNode} 的子类应实现 {@link #resolve()}方法，
 * 以便为特定日历组件或事件生成相应的 iCalendar 内容。
 *
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @since 1.0.0
 */
public abstract sealed class WebCalendarNode
        permits WebCalendarEvent {

    // 所有日历组件和事件的通用属性
    protected List<String> categories;

    protected Classification classification;

    protected String comment;

    protected String description;

    protected String location;

    protected Integer percentComplete;

    protected Integer priority;

    protected String summary;

    protected LocalDateTime end;

    protected LocalDateTime start;

    protected Duration duration;

    protected String url;

    protected String uid;

    protected String domainName;

    protected String timezone;

    /**
     * WebCalendarNode 类的构造函数，用于初始化与日历组件或事件相关的类别列表。
     */
    protected WebCalendarNode() {
        this.categories = new ArrayList<>();
    }

    /**
     * 设置与日历组件或事件相关的域名。
     *
     * @param domainName 域名
     * @return the WebCalendarNode object
     */
    public WebCalendarNode setDomainName(String domainName) {
        this.domainName = domainName;
        return this;
    }

    /**
     * 将类别列表解析为逗号分隔的字符串。
     *
     * @return 以逗号分隔的类别字符串
     */
    protected String resolveCategories() {
        var builder = new StringBuilder();
        if (categories != null && !categories.isEmpty()) {
            categories.forEach(item -> builder.append(item).append(","));
            return builder.substring(0, builder.length() - 1);
        }
        return builder.toString();
    }

    /**
     * 生成并解析日历组件或事件的 iCalendar 内容。
     *
     * @return 已解决的 iCalendar 内容
     */
    public abstract String resolve();

}

