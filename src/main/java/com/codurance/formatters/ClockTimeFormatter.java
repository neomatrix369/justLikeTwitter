package com.codurance.formatters;

import com.codurance.clock.CentralSystemClock;
import com.codurance.helper.ImplHelper;

import java.util.Date;

class ClockTimeFormatter {

    private final CentralSystemClock centralSystemClock;

    public ClockTimeFormatter(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public String whenMessageWasPosted(Date anotherDate) {
        Date currentDate = centralSystemClock.getCurrentDateTime();
        long difference = currentDate.getTime() - anotherDate.getTime();

        long diffHours = difference / (ImplHelper.MINUTES_PER_HOUR * ImplHelper.SECONDS_PER_MINUTE * ImplHelper.MILLISECONDS_PER_SECOND);
        long diffMinutes = difference / (ImplHelper.SECONDS_PER_MINUTE * ImplHelper.MILLISECONDS_PER_SECOND);
        long diffSeconds = difference / ImplHelper.MILLISECONDS_PER_SECOND;

        return appropriateTimeDifferenceInWords(diffHours, diffMinutes, diffSeconds);
    }

    private String appropriateTimeDifferenceInWords(long diffHours,
                                                    long diffMinutes,
                                                    long diffSeconds) {
        if (diffHours > 0) {
            return String.format(
                    ImplHelper.TIME_IN_WORDS_PATTERN,
                    diffHours,
                    ImplHelper.makePlural(diffHours, ImplHelper.HOUR_TOKEN));
        }

        if (diffMinutes > 0) {
            return String.format(
                    ImplHelper.TIME_IN_WORDS_PATTERN,
                    diffMinutes,
                    ImplHelper.makePlural(diffMinutes, ImplHelper.MINUTE_TOKEN));
        }

        if (diffSeconds > 0) {
            return String.format(
                    ImplHelper.TIME_IN_WORDS_PATTERN,
                    diffSeconds,
                    ImplHelper.makePlural(diffSeconds, ImplHelper.SECOND_TOKEN));
        }

        return ImplHelper.DEFAULT_TOKEN;
    }
}