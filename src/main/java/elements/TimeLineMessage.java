package elements;

import processors.DateTimeProcessor;
import processors.DateTimeCentral;

import java.util.Date;

public final class TimeLineMessage {

    private static final String MESSAGE_ON_TIMELINE_PATTERN = "%s %s";

    private String userName;
    private Date dateTime;
    private String message;

    private final DateTimeCentral dateTimeCentral;
    private final DateTimeProcessor dateTimeProcessor;

    public TimeLineMessage(DateTimeCentral dateTimeCentral) {
        this.dateTimeCentral = dateTimeCentral;
        dateTimeProcessor = new DateTimeProcessor(dateTimeCentral);
    }

    public void setMessage(String message) {
        this.dateTime = dateTimeCentral.getCurrentDateTime();
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format(
                MESSAGE_ON_TIMELINE_PATTERN,
                message,
                dateTimeProcessor.whenMessageWasPosted(dateTime));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDateTime() {
        return new Date(dateTime.getTime());
    }
}
