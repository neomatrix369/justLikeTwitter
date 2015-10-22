package com.codurance.functionality.command;

import com.codurance.clock.CentralSystemClock;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.message.MessageStore;
import com.codurance.domain.Followees;
import com.codurance.command.Fields;

public interface CommandExecutor {
    String execute(UserTypedCommand userTypedCommand);

    void setMessageStore(MessageStore messageStore);

    void setCentralSystemClock(CentralSystemClock centralSystemClock);

    void setFollowees(Followees followees);

    Fields getFields();
}