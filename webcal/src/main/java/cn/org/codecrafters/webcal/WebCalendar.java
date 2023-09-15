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
 * The WebCalendar class represents a web calendar in iCalendar format.
 * <p>
 * It allows users to create and customize calendar components and events and
 * generate an iCalendar string containing all the calendar information.
 * <p>Usage Example:
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
 * The WebCalendar class is designed to generate an iCalendar string conforming
 * to the iCalendar specification, which can be used to share calendar data
 * with other calendar applications or services.
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public final class WebCalendar {

    /**
     * The VCALENDAR tag for iCalendar format
     */
    private final static String TAG = "VCALENDAR";

    /**
     * The name of this calendar.
     */
    private String name;

    /**
     * The company who produces this calendar.
     *
     * <p>
     * This property will be used in {@code PRODID}.
     */
    private String companyName;

    /**
     * The product name.
     *
     * <p>
     * This property will be used in {@code PRODID}
     */
    private String productName;

    /**
     * The producer's domain name.
     */
    private String domainName;

    /**
     * Scale of this calendar.
     */
    private final String scale = "GREGORIAN";

    /**
     * The method of this calendar.
     */
    private String method;

    /**
     * The version of this calendar.
     */
    private final String version = "2.0";

    /**
     * List of calendar components and events
     */
    private final List<WebCalendarNode> nodes;

    /**
     * Constructor for WebCalendar class, initializes the list of calendar
     * components and events.
     */
    public WebCalendar() {
        this.nodes = new ArrayList<>();
    }

    /**
     * Set the name of the web calendar.
     *
     * @param name the name of the web calendar
     * @return the WebCalendar object
     */
    public WebCalendar setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the company name associated with the web calendar.
     *
     * @param companyName the company name
     * @return the WebCalendar object
     */
    public WebCalendar setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    /**
     * Set the domain name associated with the web calendar.
     *
     * @param domainName the domain name
     * @return the WebCalendar object
     */
    public WebCalendar setDomainName(String domainName) {
        this.domainName = domainName;
        return this;
    }

    /**
     * Set the product name of the web calendar.
     *
     * @param productName the product name
     * @return the WebCalendar object
     */
    public WebCalendar setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    /**
     * Set the method for publishing the web calendar.
     *
     * @param method the publishing method
     * @return the WebCalendar object
     */
    public WebCalendar setMethod(String method) {
        this.method = method;
        return this;
    }

    /**
     * Add an node to the web calendar.
     *
     * @param node the calendar component or event to be added
     * @return the WebCalendar object
     */
    public WebCalendar addNode(WebCalendarNode node) {
        this.nodes.add(node);
        return this;
    }

    /**
     * Add an event to the web calendar.
     *
     * @param event the calendar component or event to be added
     * @return the WebCalendar object
     */
    public WebCalendar addEvent(WebCalendarEvent event) {
        this.nodes.add(event);
        return this;
    }

    /**
     * Generate and resolve the iCalendar string for the web calendar.
     *
     * @return the resolved iCalendar string
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

