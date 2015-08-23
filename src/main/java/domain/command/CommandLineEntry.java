package domain.command;

import clock.CentralSystemClock;
import domain.MessagePosted;
import domain.MessageText;
import domain.User;

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

    public void setMessagePosted(User user, MessageText messageText) {
        messagePosted = new MessagePosted(centralSystemClock);
        messagePosted.setMessageText(messageText);
        messagePosted.setUser(user);
    }

    public User getOtherUsersName() {
        return otherUsersName;
    }

    public void setOtherUsersName(User otherUsersName) {
        this.otherUsersName = otherUsersName;
    }
}
