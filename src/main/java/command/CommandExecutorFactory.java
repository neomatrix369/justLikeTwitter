package command;

import domain.CommandType;
import domain.PatternCommandTypeMapper;
import domain.UserTypedCommand;

import java.util.Map;

import static helper.ImplHelper.NO_COMMAND_EXECUTOR_MATCHED;

public class CommandExecutorFactory {

    private final Map<CommandType, CommandExecutor> patternCommandMap = PatternCommandTypeMapper.get();

    public CommandExecutor getCommandUsing(UserTypedCommand userTypedCommand) {
        for (Map.Entry<CommandType, CommandExecutor>
                patternCommandExecutorPair: patternCommandMap.entrySet()) {

            CommandType commandType = patternCommandExecutorPair.getKey();
            CommandExecutor commandExecutor = patternCommandExecutorPair.getValue();
            String pattern = commandType.getMatchingPattern();

            if (userTypedCommand.matches(pattern)) {
                return commandExecutor;
            }
        }

        return NO_COMMAND_EXECUTOR_MATCHED;
    }
}