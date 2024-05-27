package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getLocalNow(String dateFormat) {
        return getLocalNow().format(DateTimeFormatter.ofPattern(dateFormat));
    }

    public static LocalDateTime getLocalNow() {
        return LocalDateTime.now();
    }

    public static String translateDate(LocalDateTime dateTime, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return dateTime.format(formatter);
    }
}
