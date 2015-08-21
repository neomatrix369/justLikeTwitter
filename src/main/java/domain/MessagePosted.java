package domain;

import clock.CentralSystemClock;

import java.util.Date;

import static helper.ImplHelper.MESSAGE_ON_TIMELINE_PATTERN;

public final class MessagePosted {

    private User user;
    private Date dateTime;
    private MessageText messageText;

    private final CentralSystemClock centralSystemClock;

    public MessagePosted(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public void setMessageText(MessageText messageText) {
        this.dateTime = centralSystemClock.getCurrentDateTime();
        this.messageText = messageText;
    }

    @Override
    public String toString() {
        return String.format(MESSAGE_ON_TIMELINE_PATTERN, dateTime, messageText);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateTime() {
        return new Date(dateTime.getTime());
    }

    public MessageText getMessageText() {
        return messageText;
    }
}
