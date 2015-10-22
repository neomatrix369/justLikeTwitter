package com.codurance.functionality.command;

import com.codurance.clock.CentralSystemClock;
import com.codurance.command.CommandPattern;
import com.codurance.command.CommandType;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.Followees;
import com.codurance.domain.message.MessageStore;

public class CommandExecutorSelector {

    private final CentralSystemClock centralSystemClock;
    private final MessageStore messageStore;
    private final Followees followees;

    public CommandExecutorSelector(CentralSystemClock centralSystemClock,
                                   MessageStore messageStore,
                                   Followees followees) {

        this.centralSystemClock = centralSystemClock;
        this.messageStore = messageStore;
        this.followees = followees;
    }

    public CommandExecutor getCommandExecutorFor(UserTypedCommand userTypedCommand) {

        for (CommandType commandType: CommandType.values()) {
            CommandPattern matchingPattern = commandType.getMatchingPattern();
            if (userTypedCommand.matches(matchingPattern.toString())) {
                return prepareCommandExecutor(commandType);
            }
        }

        return new NoCommand(new CommandPattern(userTypedCommand.toString()), new Fields());
    }

    private CommandExecutor prepareCommandExecutor(CommandType commandType) {
        CommandExecutor commandExecutor = commandType.getCommandExecutor();
        commandExecutor.setCentralSystemClock(centralSystemClock);
        commandExecutor.setMessageStore(messageStore);
        commandExecutor.setFollowees(followees);
        return commandExecutor;
    }
}