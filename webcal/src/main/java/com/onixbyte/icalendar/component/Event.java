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

package com.onixbyte.icalendar.component;

import com.onixbyte.icalendar.component.property.*;

/**
 * Event
 *
 * @author Zihlu WANG
 */
public class Event extends CalendarComponent {

    /*
     * The following properties are REQUIRED, but MUST NOT occur more than once.
     */

    /**
     *
     */
    private DateTimeStamp dtStamp;

    /**
     *
     */
    private UniqueIdentifier uid;

    /*
     * The following property is REQUIRED if the component appears in an iCalendar object that
     * doesn't specify the "METHOD" property; otherwise, it is OPTIONAL; in any case, it MUST NOT
     * occur more than once.
     */

    /**
     *
     */
    private DateTimeStamp dtStart;

    /*
     * The following properties are OPTIONAL, but MUST NOT occur more than once.
     */

    /**
     *
     */
    private Classification classification;

    private DateTimeCreated created;

    private Description description;

    private GeoPosition geo;

    private LastModified lastModified;

    private Location location;

    // private Organi

    @Override
    public String resolve() {
        return "";
    }
}
