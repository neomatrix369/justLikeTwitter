package com.codurance.domain.message;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.User;

public final class MessagePosted {

    private static final String MESSAGE_ON_TIMELINE_PATTERN = "%s %s";

    private User user;
    private MessageDate messageDate;
    private MessageText messageText;

    private final CentralSystemClock centralSystemClock;

    public MessagePosted(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public void setMessageText(MessageText messageText) {
        this.messageDate = new MessageDate(centralSystemClock.getCurrentDateTime());
        this.messageText = messageText;
    }

    @Override
    public String toString() {
        return String.format(MESSAGE_ON_TIMELINE_PATTERN, messageDate, messageText);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MessageDate getMessageDate() {
        return new MessageDate(messageDate.toDate());
    }

    public MessageText getMessageText() {
        return messageText;
    }
}
