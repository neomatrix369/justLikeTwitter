package clock;

import domain.MessageDate;

import java.util.Date;

import static helper.ImplHelper.DEFAULT_TOKEN;
import static helper.ImplHelper.HOUR_TOKEN;
import static helper.ImplHelper.MINUTE_TOKEN;
import static helper.ImplHelper.SECOND_TOKEN;
import static helper.ImplHelper.SIXTY_SECONDS;
import static helper.ImplHelper.THOUSAND_MILLISECONDS;
import static helper.ImplHelper.TIME_IN_WORDS_PATTERN;
import static helper.ImplHelper.makePlural;

public class ClockTimeFormatter {

    private final CentralSystemClock centralSystemClock;

    public ClockTimeFormatter(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public String whenMessageWasPosted(MessageDate anotherDate) {
        Date currentDate = centralSystemClock.getCurrentDateTime();
        long difference = currentDate.getTime() - anotherDate.getTime();

        long diffHours = difference / (SIXTY_SECONDS  * SIXTY_SECONDS * THOUSAND_MILLISECONDS);
        long diffMinutes = difference / (SIXTY_SECONDS * THOUSAND_MILLISECONDS);
        long diffSeconds = difference / THOUSAND_MILLISECONDS;

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