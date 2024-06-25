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

package com.onixbyte.icalendar.property.parameter;

import com.onixbyte.icalendar.datatype.CalendarUserAddress;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Delegator
 *
 * @author Zihlu WANG
 */
public final class Delegator implements PropertyParameter {

    private static final String PARAMETER_NAME = "DELEGATED-FROM";

    private final List<CalendarUserAddress> value;

    private Delegator(List<CalendarUserAddress> value) {
        this.value = value;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<CalendarUserAddress> value;

        private Builder() {
            value = new ArrayList<>();
        }

        public Builder addDelegator(CalendarUserAddress delegator) {
            value.add(delegator);
            return this;
        }

        public Builder addDelegator(String delegator) {
            value.add(new CalendarUserAddress(delegator));
            return this;
        }

        public Builder addDelegator(URI delegator) {
            value.add(new CalendarUserAddress(delegator));
            return this;
        }

        public Builder addDelegators(List<CalendarUserAddress> delegators) {
            value.addAll(delegators);
            return this;
        }

        public Builder addDelegators(Supplier<List<CalendarUserAddress>> delegators) {
            value.addAll(delegators.get());
            return this;
        }

        public Delegator build() {
            return new Delegator(value);
        }
    }

    @Override
    public String resolve() {
        return PARAMETER_NAME + "=\"" + String.join(",", value.stream()
                .map((_value) -> "\"" + _value + "\"")
                .toList()) + "\"";
    }
}
