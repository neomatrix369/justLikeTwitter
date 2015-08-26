package com.codurance.functionality.command;

import com.codurance.command.CommandPattern;
import com.codurance.command.CommandTokens;
import com.codurance.domain.message.MessageStore;
import com.codurance.domain.message.MessageText;
import com.codurance.helper.ImplHelper;
import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.FollowsList;
import com.codurance.domain.User;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;

public class CommandExecutorImpl implements CommandExecutor {

    private final CommandPattern commandPattern;
    private final Fields fields;

    CentralSystemClock centralSystemClock;

    MessageStore messageStore;
    FollowsList followsList;
    CommandTokens commandTokens;

    CommandExecutorImpl(CommandPattern commandPattern, Fields fields) {
        this.commandPattern = commandPattern;
        this.fields = fields;
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        commandTokens = userTypedCommand.parseUsing(commandPattern, fields);
        return ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;
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
    public void setFollowsList(FollowsList followsList) {
        this.followsList = followsList;
    }

    @Override
    public Fields getFields() {
        return fields;
    }

    User createNewUserFrom(CommandTokens commandTokens, String fieldName) {
        return new User(commandTokens.getValueFor(fieldName));
    }

    MessageText createNewMessageTextFrom(CommandTokens commandTokens, String fieldName) {
        return new MessageText(commandTokens.getValueFor(fieldName));
    }
}