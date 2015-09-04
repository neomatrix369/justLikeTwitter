package com.codurance.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class ImplHelper {
    public static final int MILLISECONDS_PER_SECOND = 1000;
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int MINUTES_PER_HOUR = 60;

    public static final String TIME_IN_WORDS_PATTERN = "(%d %s ago)";

    public static final String HOUR_TOKEN = "hour";
    public static final String MINUTE_TOKEN = "minute";
    public static final String SECOND_TOKEN = "second";

    public static final String DEFAULT_TOKEN = "(just now)";

    public static final String MESSAGE_ON_TIMELINE_PATTERN = "%s %s";

    public static final String NOTHING_FOR_THIS_COMMAND_EXECUTION = "";

    public static final String COMMAND_PROMPT_INDICATOR = "> ";
    public static final String NOTHING = "";

    public static final int START_FROM_ONE = 1;
    public static final int FOREVER = -1;

    public static final String COLUMN_SEPARATOR = ",";
    public static final int DATE_COL_INDEX = 0;
    public static final int MESSAGE_COL_INDEX = 1;

    public static final String STRING_ENCODING = "UTF-8";

    public static final String APP_USAGE_FILEPATH = "../../AppUsage.txt";

    private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy hh:mm:ss";

    private ImplHelper() {
    }

    public static Date convertToDateFrom(String dateAsString) throws ParseException {
        DateFormat format = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS, Locale.ENGLISH);
        return format.parse(dateAsString);
    }
}