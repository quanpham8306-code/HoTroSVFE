package org.example.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final DateTimeFormatter DMY =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String format(LocalDate date) {
        return date == null ? "" : date.format(DMY);
    }
}
