package domain;

import processors.DateTimeStampProcessor;
import processors.DateTimeStampProvider;

import java.util.Date;

public class TimeLineMessage {

    public static final String MESSAGE_ON_TIMELINE_PATTERN = "%s %s";
    private Date dateTimeStamp;
    private String message;
    private DateTimeStampProvider dateTimeStampProvider;
    private DateTimeStampProcessor dateTimeStampProcessor;

    public TimeLineMessage(DateTimeStampProvider dateTimeStampProvider) {
        this.dateTimeStampProvider = dateTimeStampProvider;
        dateTimeStampProcessor = new DateTimeStampProcessor(dateTimeStampProvider);
    }

    public void setMessage(String message) {
        this.dateTimeStamp = dateTimeStampProvider.getCurrentDateTimeStamp();
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format(
                MESSAGE_ON_TIMELINE_PATTERN,
                message,
                dateTimeStampProcessor.whenMessageWasPosted(dateTimeStamp));
    }
}
