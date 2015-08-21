package clock;

import java.util.Date;

import static helper.ImplHelper.DEFAULT_TOKEN;
import static helper.ImplHelper.MINUTE_TOKEN;
import static helper.ImplHelper.SECOND_TOKEN;
import static helper.ImplHelper.SIXTY_SECONDS;
import static helper.ImplHelper.SUFFIX_S;
import static helper.ImplHelper.THOUSAND_MILLISECONDS;
import static helper.ImplHelper.TIME_IN_WORDS_PATTERN;

public class ClockTimeFormatter {

    private final CentralSystemClock centralSystemClock;

    public ClockTimeFormatter(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public String whenMessageWasPosted(Date anotherDate) {
        Date currentDate = centralSystemClock.getCurrentDateTime();
        long difference = currentDate.getTime() - anotherDate.getTime();
        long diffMinutes = difference / (SIXTY_SECONDS * THOUSAND_MILLISECONDS);
        long diffSeconds = difference / THOUSAND_MILLISECONDS;

        return appropriateTimeDifferenceInWords(diffMinutes, diffSeconds);
    }

    private String appropriateTimeDifferenceInWords(long diffMinutes, long diffSeconds) {
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

    private String makePlural(long duration, String timeUnit) {
        if (duration > 1) {
            return timeUnit + SUFFIX_S;
        }
        return timeUnit;
    }
}