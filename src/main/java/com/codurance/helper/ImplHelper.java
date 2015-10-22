package com.codurance.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class ImplHelper {

    private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy hh:mm:ss";

    private ImplHelper() {
    }

    public static Date convertToDateFrom(String dateAsString) throws ParseException {
        DateFormat format = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS, Locale.ENGLISH);
        return format.parse(dateAsString);
    }
}