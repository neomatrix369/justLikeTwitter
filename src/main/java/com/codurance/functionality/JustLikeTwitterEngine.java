package com.codurance.functionality;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.FollowsList;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.command.CommandExecutor;
import com.codurance.functionality.command.CommandExecutorSelector;

public class JustLikeTwitterEngine {
    private final CentralSystemClock centralSystemClock;
    private final MessageStore messageStore;
    private final FollowsList followsList;

    public JustLikeTwitterEngine(CentralSystemClock centralSystemClock,
                                 MessageStore messageStore,
                                 FollowsList followsList) {
        this.centralSystemClock = centralSystemClock;
        this.messageStore = messageStore;
        this.followsList = followsList;
    }

    public String executeCommand(UserTypedCommand userTypedCommand) {
        CommandExecutorSelector commandExecutorSelector =
                new CommandExecutorSelector(centralSystemClock, messageStore, followsList);

        CommandExecutor commandExecutor = commandExecutorSelector.getCommandExecutorFor(userTypedCommand);

        return commandExecutor.execute(userTypedCommand);
    }
}