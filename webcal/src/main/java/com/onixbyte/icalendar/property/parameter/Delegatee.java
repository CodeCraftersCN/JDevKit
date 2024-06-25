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
 * Delegate
 *
 * @author Zihlu WANG
 */
public final class Delegatee implements PropertyParameter {

    private static final String PARAMETER_NAME = "DELEGATED-TO";

    private final List<CalendarUserAddress> value;

    private Delegatee(List<CalendarUserAddress> value) {
        this.value = value;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<CalendarUserAddress> value;

        private Builder() {
            this.value = new ArrayList<>();
        }

        public Builder addDelegatee(CalendarUserAddress delegatee) {
            value.add(delegatee);
            return this;
        }

        public Builder addDelegatee(URI delegatee) {
            value.add(new CalendarUserAddress(delegatee));
            return this;
        }

        public Builder addDelegatee(String delegatee) {
            value.add(new CalendarUserAddress(delegatee));
            return this;
        }

        public Builder addDelegatees(List<CalendarUserAddress> delegatees) {
            value.addAll(delegatees);
            return this;
        }

        public Builder addDelegatees(Supplier<List<CalendarUserAddress>> delegatees) {
            value.addAll(delegatees.get());
            return this;
        }

        public Delegatee build() {
            return new Delegatee(value);
        }
    }

    @Override
    public String resolve() {
        return PARAMETER_NAME + "=" + String.join(",", value.stream()
                .map((_value) -> "\"" + _value + "\"")
                .toList());
    }
}
