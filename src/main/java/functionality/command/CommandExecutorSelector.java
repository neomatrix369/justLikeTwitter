package functionality.command;

import domain.command.CommandPattern;
import domain.command.CommandType;
import domain.command.Fields;
import domain.command.UserTypedCommand;

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