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
 * The abstract sealed class WebCalendarNode represents a node in the web
 * calendar, such as an event, a to-do item, or an alarm. It provides common
 * properties and methods for all calendar components and events.
 *
 * <p>
 * Subclasses of WebCalendarNode should implement the {@link #resolve()} method
 * to generate the corresponding iCalendar content for the specific calendar
 * component or event.
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public abstract sealed class WebCalendarNode
        permits WebCalendarEvent {

    // Common properties for all calendar components and events
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
     * Constructor for WebCalendarNode class, initializes the list of
     * categories associated with the calendar component or event.
     */
    protected WebCalendarNode() {
        this.categories = new ArrayList<>();
    }

    /**
     * Set the domain name associated with the calendar component or event.
     *
     * @param domainName the domain name
     * @return the WebCalendarNode object
     */
    public WebCalendarNode setDomainName(String domainName) {
        this.domainName = domainName;
        return this;
    }

    /**
     * Resolve the list of categories into a comma-separated string.
     *
     * @return the comma-separated string of categories
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
     * Generate and resolve the iCalendar content for the calendar component or
     * event.
     *
     * @return the resolved iCalendar content
     */
    public abstract String resolve();

}

