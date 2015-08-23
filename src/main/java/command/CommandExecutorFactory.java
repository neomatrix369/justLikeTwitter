package command;

import domain.CommandPattern;
import domain.CommandType;
import domain.PatternCommandTypeMapper;
import domain.UserTypedCommand;

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