package domain;

import clock.CentralSystemClock;

public class CommandLineEntry {
    private User user;
    private User otherUsersName;
    private MessagePosted messagePosted;

    private final CentralSystemClock centralSystemClock;

    public CommandLineEntry(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public User getUser() {
        return user;
    }

    public MessagePosted getMessagePosted() {
        return messagePosted;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessagePosted(User user, String message) {
        messagePosted = new MessagePosted(centralSystemClock);
        messagePosted.setMessage(message);
        messagePosted.setUser(user);
    }

    public User getOtherUsersName() {
        return otherUsersName;
    }

    public void setOtherUsersName(User otherUsersName) {
        this.otherUsersName = otherUsersName;
    }
}
