package functionality.command;

import domain.command.CommandPattern;
import domain.command.CommandType;
import domain.command.UserTypedCommand;

public class CommandExecutorSelector {

    private static final CommandExecutor NO_COMMAND_EXECUTOR_MATCHED = null;

    public CommandExecutor getCommandExecutorFor(UserTypedCommand userTypedCommand) {
        for (CommandType commandType: CommandType.values()) {
            CommandPattern matchingPattern = commandType.getMatchingPattern();
            if (userTypedCommand.matches(matchingPattern.toString())) {
                return commandType.getCommandExecutor();
            }
        }

        return NO_COMMAND_EXECUTOR_MATCHED;
    }
}