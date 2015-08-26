package com.codurance.functionality.command;

import com.codurance.clock.CentralSystemClock;
import com.codurance.command.CommandPattern;
import com.codurance.command.CommandType;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.FollowsList;
import com.codurance.domain.message.MessageStore;

public class CommandExecutorSelector {

    private final CentralSystemClock centralSystemClock;
    private final MessageStore messageStore;
    private final FollowsList followsList;

    public CommandExecutorSelector(CentralSystemClock centralSystemClock,
                                   MessageStore messageStore,
                                   FollowsList followsList) {

        this.centralSystemClock = centralSystemClock;
        this.messageStore = messageStore;
        this.followsList = followsList;
    }

    public CommandExecutor getCommandExecutorFor(UserTypedCommand userTypedCommand) {

        for (CommandType commandType: CommandType.values()) {
            CommandPattern matchingPattern = commandType.getMatchingPattern();
            if (userTypedCommand.matches(matchingPattern.toString())) {
                return prepareCommandExecutor(commandType);
            }
        }

        CommandExecutor noCommandExecutorMatched =
                new NoMatchingCommand(new CommandPattern(userTypedCommand.toString()), new Fields());
        return noCommandExecutorMatched;
    }

    private CommandExecutor prepareCommandExecutor(CommandType commandType) {
        CommandExecutor commandExecutor = commandType.getCommandExecutor();
        commandExecutor.setCentralSystemClock(centralSystemClock);
        commandExecutor.setMessageStore(messageStore);
        commandExecutor.setFollowsList(followsList);
        return commandExecutor;
    }
}