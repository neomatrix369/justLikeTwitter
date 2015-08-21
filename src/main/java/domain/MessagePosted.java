package domain;

import clock.CentralSystemClock;

import java.util.Date;

import static helper.ImplHelper.MESSAGE_ON_TIMELINE_PATTERN;

public final class MessagePosted {

    private String userName;
    private Date dateTime;
    private String message;

    private final CentralSystemClock centralSystemClock;

    public MessagePosted(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public void setMessage(String message) {
        this.dateTime = centralSystemClock.getCurrentDateTime();
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format(MESSAGE_ON_TIMELINE_PATTERN, dateTime, message);
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

    public String getMessage() {
        return message;
    }
}
