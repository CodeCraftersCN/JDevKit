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

import java.util.ArrayList;
import java.util.List;

/**
 * {@code WebCalendar} 类表示 iCalendar 格式的网络日历。
 * <p>
 * 它允许用户创建和自定义日历组件和事件，并生成包含所有日历信息的 <b>iCalendar</b> 字符串。
 * <p>
 * 使用示例：
 * <pre>
 * WebCalendar calendar = new WebCalendar()
 *         .setName("My Web Calendar")
 *         .setCompanyName("CodeCrafters Inc.")
 *         .setProductName("WebCal")
 *         .setDomainName("codecrafters.org.cn")
 *         .setMethod("PUBLISH")
 *         .addEvent(event1)
 *         .addEvent(event2);
 * String iCalendarString = calendar.resolve();
 * </pre>
 * <p>
 * {@code WebCalendar} 类旨在生成符合 iCalendar 规范的 iCalendar 字符串，
 * 该字符串可用于与其他日历应用程序或服务共享日历数据。
 *
 * @author Zihlu Wang
 * @author Zitai Long
 * @version 1.1.0
 * @since 1.0.0
 */
public final class WebCalendar {

    /**
     * 用于 iCalendar 格式的 {@code VCALENDAR} 标记
     */
    private final static String TAG = "VCALENDAR";

    /**
     * 该日历的名称。
     */
    private String name;

    /**
     * 制作该日历的公司。
     * <p>
     * 该属性将用于 {@code PRODID}。
     */
    private String companyName;

    /**
     * 产品名称。
     * <p>
     * 该属性将在 {@code PRODID} 中使用。
     */
    private String productName;

    /**
     * 制作者的域名。
     */
    private String domainName;

    /**
     * 本日历的规模。
     */
    private final String scale = "GREGORIAN";

    /**
     * 该日历的方法。
     */
    private String method;

    /**
     * 本日历的版本。
     */
    private final String version = "2.0";

    /**
     * 日历组件和事件列表
     */
    private final List<WebCalendarNode> nodes;

    /**
     * WebCalendar 类的构造函数，用于初始化日历组件和事件列表。
     */
    public WebCalendar() {
        this.nodes = new ArrayList<>();
    }

    /**
     * 设置网络日历的名称。
     *
     * @param name 网络日历的名称
     * @return WebCalendar 对象
     */
    public WebCalendar setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 设置与网络日历相关的公司名称。
     *
     * @param companyName 公司名称
     * @return WebCalendar 对象
     */
    public WebCalendar setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    /**
     * 设置与网络日历相关的域名。
     *
     * @param domainName 域名
     * @return WebCalendar 对象
     */
    public WebCalendar setDomainName(String domainName) {
        this.domainName = domainName;
        return this;
    }

    /**
     * 设置网络日历的产品名称。
     *
     * @param productName 产品名称
     * @return WebCalendar 对象
     */
    public WebCalendar setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    /**
     * 设置发布网络日历的方法。
     *
     * @param method 发布方法
     * @return WebCalendar 对象
     */
    public WebCalendar setMethod(String method) {
        this.method = method;
        return this;
    }

    /**
     * 在网络日历中添加的节点。
     *
     * @param node 要添加的日历节点
     * @return WebCalendar 对象
     */
    public WebCalendar addNode(WebCalendarNode node) {
        this.nodes.add(node);
        return this;
    }

    /**
     * 在网络日历中添加事件。
     *
     * @param event 要添加的日历组件或事件
     * @return WebCalendar 对象
     */
    public WebCalendar addEvent(WebCalendarEvent event) {
        this.nodes.add(event);
        return this;
    }

    /**
     * 生成并解析网络日历的 iCalendar 字符串。
     *
     * @return 已解析的 iCalendar 字符串
     */
    public String resolve() {
        var events = new StringBuilder();
        if (!nodes.isEmpty()) {
            nodes.forEach(item ->
                    events.append(item.setDomainName(domainName)
                            .resolve()));
        }

        return "BEGIN:" + TAG + "\n" +
                "PRODID:-//" + companyName + "//" + productName + "//EN\n" +
                "VERSION:" + version + "\n" +
                "X-WR-CALNAME:" + name + "\n" +
                events + "\n" +
                "END:" + TAG;
    }

}

