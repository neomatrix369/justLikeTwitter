package com.codurance.formatters;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.message.MessageDate;

import java.util.Date;

class ClockTimeFormatter {

    private static final String SUFFIX_S = "s";

    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MILLISECONDS_PER_SECOND = 1000;

    private static final String HOUR_TOKEN = "hour";
    private static final String MINUTE_TOKEN = "minute";
    private static final String SECOND_TOKEN = "second";

    private static final String TIME_IN_WORDS_PATTERN = "(%d %s ago)";
    private static final String DEFAULT_TOKEN = "(just now)";

    private final CentralSystemClock centralSystemClock;

    public ClockTimeFormatter(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public String whenMessageWasPosted(MessageDate anotherDate) {
        Date currentDate = centralSystemClock.getCurrentDateTime();
        long difference = currentDate.getTime() - anotherDate.getTime();

        long diffHours = difference / (MINUTES_PER_HOUR * SECONDS_PER_MINUTE * MILLISECONDS_PER_SECOND);
        long diffMinutes = difference / (SECONDS_PER_MINUTE * MILLISECONDS_PER_SECOND);
        long diffSeconds = difference / MILLISECONDS_PER_SECOND;

        return appropriateTimeDifferenceInWords(diffHours, diffMinutes, diffSeconds);
    }

    private String appropriateTimeDifferenceInWords(long diffHours,
                                                    long diffMinutes,
                                                    long diffSeconds) {
        if (diffHours > 0) {
            return String.format(
                    TIME_IN_WORDS_PATTERN,
                    diffHours,
                    makePlural(diffHours, HOUR_TOKEN));
        }

        if (diffMinutes > 0) {
            return String.format(
                    TIME_IN_WORDS_PATTERN,
                    diffMinutes,
                    makePlural(diffMinutes, MINUTE_TOKEN));
        }

        if (diffSeconds > 0) {
            return String.format(
                    TIME_IN_WORDS_PATTERN,
                    diffSeconds,
                    makePlural(diffSeconds, SECOND_TOKEN));
        }

        return DEFAULT_TOKEN;
    }

    public static String makePlural(long duration, String timeUnit) {
        if (duration > 1) {
            return timeUnit + SUFFIX_S;
        }
        return timeUnit;
    }

}