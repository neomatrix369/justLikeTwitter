package com.codurance.functionality.command;

import com.codurance.clock.CentralSystemClock;
import com.codurance.command.CommandPattern;
import com.codurance.command.CommandTokens;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.Followees;
import com.codurance.domain.message.MessageStore;

public class CommandExecutorImpl implements CommandExecutor {

    protected static final String NOTHING_FOR_THIS_COMMAND_EXECUTION = "";

    private final CommandPattern commandPattern;
    private final Fields fields;

    CentralSystemClock centralSystemClock;

    MessageStore messageStore;
    Followees followees;
    CommandTokens commandTokens;

    CommandExecutorImpl(CommandPattern commandPattern, Fields fields) {
        this.commandPattern = commandPattern;
        this.fields = fields;
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        commandTokens = userTypedCommand.parseUsing(commandPattern, fields);
        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    @Override
    public void setCentralSystemClock(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    @Override
    public void setMessageStore(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    @Override
    public void setFollowees(Followees followees) {
        this.followees = followees;
    }

    @Override
    public Fields getFields() {
        return fields;
    }
}