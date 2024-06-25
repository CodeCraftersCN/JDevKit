/*
 * Copyright (C) 2024-2024 OnixByte.
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

package com.onixbyte.icalendar.component.property;

import com.onixbyte.icalendar.calendar.property.Method;
import com.onixbyte.icalendar.core.DateTimeFormatters;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * In the case of an {@link com.onixbyte.icalendar.component.Calendar iCalendar} object that
 * specifies a "{@link Method Method}" property, this property specifies the date and time that the
 * instance of the {@link com.onixbyte.icalendar.component.Calendar iCalendar} object was created.
 * In the case of an {@link com.onixbyte.icalendar.component.Calendar iCalendar} object that doesn't
 * specify a "{@link Method Method}" property, this property specifies the date and time that
 * the information associated with the calendar component was last revised in the calendar store.
 * <p>
 * The value MUST be specified in the UTC time format.
 * <p>
 * This property is also useful to protocols such as [2447bis] that have inherent latency issues
 * with the delivery of content. This property will assist in the proper sequencing of messages
 * containing {@link com.onixbyte.icalendar.component.Calendar iCalendar} objects.
 * <p>
 * In the case of an {@link com.onixbyte.icalendar.component.Calendar iCalendar} object that
 * specifies a "{@link Method Method}" property, this property differs from the
 * "{@link DateTimeCreated CREATED}" and "{@link LastModified LAST-MODIFIED}" properties. These two
 * properties are used to specify when the particular calendar data in the calendar store was
 * created and last modified. This is different from when the {@link
 * com.onixbyte.icalendar.component.Calendar iCalendar} object representation of the calendar
 * service information was created or last modified.
 * <p>
 * In the case of an {@link com.onixbyte.icalendar.component.Calendar iCalendar} object that
 * specifies a "{@link Method METHOD}" property, this property is equivalent to the "{@link
 * LastModified LAST-MODIFIED}" property.
 *
 * @author zihluwang
 */
public record DateTimeStamp(LocalDateTime dateTimeStamp) implements DateTimeProperty, ComponentProperty {

    private static final String PROPERTY_NAME = "DTSTAMP";

    @Override
    public String resolve() {
        return PROPERTY_NAME + ":" + dateTimeStamp
                .atZone(ZoneId.systemDefault())
                .withZoneSameInstant(ZoneId.of("UTC"))
                .format(DateTimeFormatters.utcDateTimeFormatter()) + "\n";
    }
}
