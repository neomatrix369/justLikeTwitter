package domain;

import processors.DateTimeStampProvider;

public class CommandLineEntry {
    private String userName;
    private TimeLineMessage timeLineMessage;

    private final DateTimeStampProvider dateTimeStampProvider;

    public CommandLineEntry(DateTimeStampProvider dateTimeStampProvider) {
        this.dateTimeStampProvider = dateTimeStampProvider;
    }

    public String getUserName() {
        return userName;
    }

    public TimeLineMessage getTimeLineMessage() {
        return timeLineMessage;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTimeLineMessage(String message) {
        timeLineMessage = new TimeLineMessage(dateTimeStampProvider);
        timeLineMessage.setMessage(message);
    }
}
