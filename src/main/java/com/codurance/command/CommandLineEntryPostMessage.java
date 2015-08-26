package com.codurance.command;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.message.MessagePosted;
import com.codurance.domain.message.MessageText;
import com.codurance.domain.User;

public class CommandLineEntryPostMessage {
    private final CentralSystemClock centralSystemClock;
    private MessagePosted messagePosted;
    private final User user;

    public CommandLineEntryPostMessage(CentralSystemClock centralSystemClock,
                                       User user,
                                       MessageText messageText) {
        this.centralSystemClock = centralSystemClock;
        this.user = user;
        setMessagePosted(messageText);
    }

    public MessagePosted getMessagePosted() {
        return messagePosted;
    }

    private void setMessagePosted(MessageText messageText) {
        this.messagePosted = new MessagePosted(centralSystemClock);
        this.messagePosted.setUser(user);
        this.messagePosted.setMessageText(messageText);
    }
}
