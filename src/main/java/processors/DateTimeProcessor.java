package processors;

import java.util.Date;

public class DateTimeProcessor {
    private static final int THOUSAND_MILLISECONDS = 1000;
    private static final int SIXTY_SECONDS = 60;

    private static final String TIME_IN_WORDS_PATTERN = "%d %s";
    private static final String WHEN_MESSAGE_WAS_POSTED_PATTERN = "(%s ago)";

    private static final String MINUTE_SINGULAR = "minute";
    private static final String SECOND_SINGULAR = "second";
    private static final String DEFAULT_TOKEN = "just now";
    private static final String SUFFIX_S = "s";

    private final DateTimeCentral dateTimeCentral;

    public DateTimeProcessor(DateTimeCentral dateTimeCentral) {
        this.dateTimeCentral = dateTimeCentral;
    }

    public String whenMessageWasPosted(Date dateTime) {
        String durationAsString = timeDifferenceInWords(dateTime);
        return String.format(WHEN_MESSAGE_WAS_POSTED_PATTERN, durationAsString);
    }

    private String timeDifferenceInWords(Date anotherDate) {
        Date currentDate = dateTimeCentral.getCurrentDateTime();
        long difference = currentDate.getTime() - anotherDate.getTime();
        long diffMinutes = difference / (SIXTY_SECONDS * THOUSAND_MILLISECONDS);
        long diffSeconds = difference / THOUSAND_MILLISECONDS;

        return appropriateTimeDifferenceInWords(diffMinutes, diffSeconds);
    }

    private String appropriateTimeDifferenceInWords(long diffMinutes, long diffSeconds) {
        if (diffMinutes > 0) {
            return String.format(TIME_IN_WORDS_PATTERN, diffMinutes, makePlural(diffMinutes, MINUTE_SINGULAR));
        }

        if (diffSeconds > 0) {
            return String.format(TIME_IN_WORDS_PATTERN, diffSeconds, makePlural(diffSeconds, SECOND_SINGULAR));
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