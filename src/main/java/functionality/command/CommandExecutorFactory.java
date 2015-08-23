package functionality.command;

import domain.command.CommandPattern;
import domain.command.CommandType;
import domain.command.PatternCommandTypeMapper;
import domain.command.UserTypedCommand;

import java.util.Map;

public class CommandExecutorFactory {

    private CommandExecutor NO_COMMAND_EXECUTOR_MATCHED = null;

    private final Map<CommandType, CommandExecutor> patternCommandMap = PatternCommandTypeMapper.get();

    public CommandExecutor getCommandUsing(UserTypedCommand userTypedCommand) {
        for (Map.Entry<CommandType, CommandExecutor>
                patternCommandExecutorPair: patternCommandMap.entrySet()) {

            CommandType commandType = patternCommandExecutorPair.getKey();
            CommandExecutor commandExecutor = patternCommandExecutorPair.getValue();
            CommandPattern matchingPattern = commandType.getMatchingPattern();

            if (userTypedCommand.matches(matchingPattern.toString())) {
                return commandExecutor;
            }
        }

        return NO_COMMAND_EXECUTOR_MATCHED;
    }
}