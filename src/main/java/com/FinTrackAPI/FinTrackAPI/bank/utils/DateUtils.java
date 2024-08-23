package com.FinTrackAPI.FinTrackAPI.bank.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate validateAndConvertDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("The date '" + dateStr + "' is not in the format DD/MM/YYYY.");       }
    }
}