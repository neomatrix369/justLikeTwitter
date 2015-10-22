package com.codurance.functionality;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.Followees;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.command.CommandExecutor;
import com.codurance.functionality.command.CommandExecutorSelector;

public class JustLikeTwitterEngine {
    private final CentralSystemClock centralSystemClock;
    private final MessageStore messageStore;
    private final Followees followees;

    public JustLikeTwitterEngine(CentralSystemClock centralSystemClock,
                                 MessageStore messageStore,
                                 Followees followees) {
        this.centralSystemClock = centralSystemClock;
        this.messageStore = messageStore;
        this.followees = followees;
    }

    public String executeCommand(UserTypedCommand userTypedCommand) {
        CommandExecutorSelector commandExecutorSelector =
                new CommandExecutorSelector(centralSystemClock, messageStore, followees);

        CommandExecutor commandExecutor = commandExecutorSelector.getCommandExecutorFor(userTypedCommand);

        return commandExecutor.execute(userTypedCommand);
    }
}