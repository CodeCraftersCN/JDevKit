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

package cn.org.codecrafters.webcal.config;

import lombok.Getter;

/**
 * The Classification enum represents the classification levels of calendar
 * content based on RFC 5545.
 * <p>
 * Calendar events or components can be classified as one of the following
 * levels:
 * <ul>
 *     <li>
 *         {@link #PUBLIC}: Indicates that the calendar content is public and
 *         can be freely distributed.
 *     </li>
 *     <li>
 *         {@link #PRIVATE}: Indicates that the calendar content is private and
 *         should not be shared with others.
 *     </li>
 *     <li>
 *         {@link #CONFIDENTIAL}: Indicates that the calendar content is
 *         confidential and should be kept strictly private.
 *     </li>
 * </ul>
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
@Getter
public enum Classification {

    /**
     * Public classification level.
     * <p>
     * Indicates that the calendar content is public and can be freely
     * distributed.
     */
    PUBLIC("PUBLIC"),

    /**
     * Private classification level.
     * <p>
     * Indicates that the calendar content is private and should not be shared
     * with others.
     */
    PRIVATE("PRIVATE"),

    /**
     * Confidential classification level.
     * <p>
     * Indicates that the calendar content is confidential and should be kept
     * strictly private.
     */
    CONFIDENTIAL("CONFIDENTIAL"),
    ;

    /**
     * -- GETTER --
     * Get the string representation of the classification level.
     */
    private final String classification;

    /**
     * Constructor for Classification enum.
     *
     * @param classification the classification level as a string representation
     */
    Classification(String classification) {
        this.classification = classification;
    }

}
