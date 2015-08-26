package formatters;

import clock.CentralSystemClock;

import java.util.Date;

import static helper.ImplHelper.DEFAULT_TOKEN;
import static helper.ImplHelper.HOUR_TOKEN;
import static helper.ImplHelper.MINUTE_TOKEN;
import static helper.ImplHelper.SECOND_TOKEN;
import static helper.ImplHelper.MINUTES_PER_HOUR;
import static helper.ImplHelper.SECONDS_PER_MINUTE;
import static helper.ImplHelper.MILLISECONDS_PER_SECOND;
import static helper.ImplHelper.TIME_IN_WORDS_PATTERN;
import static helper.ImplHelper.makePlural;

class ClockTimeFormatter {

    private final CentralSystemClock centralSystemClock;

    public ClockTimeFormatter(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public String whenMessageWasPosted(Date anotherDate) {
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
}