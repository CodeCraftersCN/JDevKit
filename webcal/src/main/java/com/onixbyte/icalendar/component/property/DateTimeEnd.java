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

import com.onixbyte.icalendar.property.parameter.ValueDataType;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * {@code DateTimeEnd} specifies the date and time that a calendar component ends.
 * <p>
 * THis property can be specified in {@link com.onixbyte.icalendar.component.Event Event} or {@link
 * com.onixbyte.icalendar.component.FreeBusy FreeBusy} calendar components.
 *
 * @param valueDataType the type of value, date-time or date
 * @param tzId          the timezone of value
 * @param localDateTime end time
 * @author zihluwang
 */
public record DateTimeEnd(ValueDataType valueDataType,
                          TimeZoneIdentifier tzId,
                          LocalDateTime localDateTime) implements DateTimeProperty, ComponentProperty {

    private static final String PROPERTY_NAME = "DTEND";

    public DateTimeEnd {
        if (Objects.nonNull(valueDataType) && !SUPPORTED_VALUES.contains(valueDataType)) {
            throw new IllegalArgumentException("ValueDateType only accepts DATE and DATE-TIME");
        }
    }

    @Override
    public String resolve() {
        return DateTimeProperty.composeResolution(PROPERTY_NAME, valueDataType, localDateTime);
    }
}
