package com.codurance.command;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.message.MessagePosted;
import com.codurance.domain.message.MessageText;
import com.codurance.domain.User;

public class CommandLineEntry {
    private User user = new User("");
    private User otherUsersName = new User("");
    private MessagePosted messagePosted = new MessagePosted(new CentralSystemClock());

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
