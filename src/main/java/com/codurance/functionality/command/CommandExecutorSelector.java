package com.codurance.functionality.command;

import com.codurance.command.CommandPattern;
import com.codurance.command.CommandType;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;

public class CommandExecutorSelector {

    public CommandExecutor getCommandExecutorFor(UserTypedCommand userTypedCommand) {

        for (CommandType commandType: CommandType.values()) {
            CommandPattern matchingPattern = commandType.getMatchingPattern();
            if (userTypedCommand.matches(matchingPattern.toString())) {
                return commandType.getCommandExecutor();
            }
        }

        CommandExecutor noCommandExecutorMatched =
                new NoMatchingCommand(new CommandPattern(userTypedCommand.toString()), new Fields());
        return noCommandExecutorMatched;
    }
}