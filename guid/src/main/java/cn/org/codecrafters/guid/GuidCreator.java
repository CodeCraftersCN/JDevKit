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

package cn.org.codecrafters.guid;

/**
 * The {@code GuidCreator} is a generic interface for generating globally unique
 * identifiers (GUIDs) of a specific type.
 * <p>
 * The type of ID is determined by the class implementing this interface.
 *
 * @param <IdType> this represents the type of the Global Unique Identifier
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
public interface GuidCreator<IdType> {

    /**
     * Generates and returns the next globally unique ID.
     * <p>
     * The exact implementation of how the globally unique ID is generated and
     * returned will depend on the class implementing this method.
     *
     * @return the next globally unique ID
     */
    IdType nextId();

}