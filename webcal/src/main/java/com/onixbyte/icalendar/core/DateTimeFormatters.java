package com.onixbyte.icalendar.core;

import com.onixbyte.icalendar.property.parameter.ValueDataType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public final class DateTimeFormatters {

    private DateTimeFormatters() {
    }

    private static DateTimeFormatter utcDateTimeFormatter;

    private static DateTimeFormatter dateFormatter;

    private static DateTimeFormatter localDateTimeFormatter;

    public static DateTimeFormatter utcDateTimeFormatter() {
        if (Objects.isNull(utcDateTimeFormatter)) {
            utcDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
        }
        return utcDateTimeFormatter;
    }

    public static DateTimeFormatter dateFormatter() {
        if (Objects.isNull(dateFormatter)) {
            dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        }
        return dateFormatter;
    }

    public static DateTimeFormatter localDateTimeFormatter() {
        if (Objects.isNull(localDateTimeFormatter)) {
            localDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
        }
        return localDateTimeFormatter;
    }

}