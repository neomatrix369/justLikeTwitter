package com.codurance.command;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.message.MessagePosted;
import com.codurance.domain.message.MessageText;
import com.codurance.domain.User;

public class CommandLineEntryPostMessage {
    private User user;
    private MessagePosted messagePosted;
    private CentralSystemClock centralSystemClock;

    public CommandLineEntryPostMessage(CentralSystemClock centralSystemClock,
                                       User user,
                                       MessageText messageText) {
        this.centralSystemClock = centralSystemClock;
        this.user = user;
        setMessagePosted(messageText);
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

    public void setMessagePosted(MessageText messageText) {
        this.messagePosted = new MessagePosted(centralSystemClock);
        this.messagePosted.setUser(user);
        this.messagePosted.setMessageText(messageText);
    }
}
