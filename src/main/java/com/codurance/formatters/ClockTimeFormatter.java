package com.codurance.formatters;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.message.MessageDate;
import com.codurance.helper.ImplHelper;

import java.util.Date;

import static com.codurance.helper.ImplHelper.DEFAULT_TOKEN;
import static com.codurance.helper.ImplHelper.HOUR_TOKEN;
import static com.codurance.helper.ImplHelper.MILLISECONDS_PER_SECOND;
import static com.codurance.helper.ImplHelper.MINUTE_TOKEN;
import static com.codurance.helper.ImplHelper.SECONDS_PER_MINUTE;
import static com.codurance.helper.ImplHelper.SECOND_TOKEN;
import static com.codurance.helper.ImplHelper.TIME_IN_WORDS_PATTERN;

class ClockTimeFormatter {

    private static final String SUFFIX_S = "s";

    private final CentralSystemClock centralSystemClock;

    public ClockTimeFormatter(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public String whenMessageWasPosted(MessageDate anotherDate) {
        Date currentDate = centralSystemClock.getCurrentDateTime();
        long difference = currentDate.getTime() - anotherDate.getTime();

        long diffHours = difference / (ImplHelper.MINUTES_PER_HOUR * SECONDS_PER_MINUTE * MILLISECONDS_PER_SECOND);
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