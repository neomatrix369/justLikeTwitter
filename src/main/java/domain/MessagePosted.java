package domain;

import clock.CentralSystemClock;

import static helper.ImplHelper.MESSAGE_ON_TIMELINE_PATTERN;

public final class MessagePosted {

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

    public User toUser() {
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
