package com.codurance.functionality;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.FollowsList;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.command.CommandExecutor;
import com.codurance.functionality.command.CommandExecutorSelector;

public class JustLikeTwitterEngine {
    private final MessageStore messageStore;
    private final FollowsList followsList;
    private final CentralSystemClock centralSystemClock;

    public JustLikeTwitterEngine(MessageStore messageStore,
                                 FollowsList followsList,
                                 CentralSystemClock centralSystemClock) {
        this.messageStore = messageStore;
        this.followsList = followsList;
        this.centralSystemClock = centralSystemClock;
    }

    public String executeCommand(UserTypedCommand userTypedCommand) {
        CommandExecutorSelector commandExecutorSelector = new CommandExecutorSelector();

        CommandExecutor commandExecutor = commandExecutorSelector.getCommandExecutorFor(userTypedCommand);
        commandExecutor.setMessageStore(messageStore);
        commandExecutor.setCentralSystemClock(centralSystemClock);
        commandExecutor.setFollowsList(followsList);

        return commandExecutor.execute(userTypedCommand);
    }
}