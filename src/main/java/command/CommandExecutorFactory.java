package command;

import clock.CentralSystemClock;
import domain.CommandTokens;
import domain.CommandType;
import domain.FollowsList;
import domain.MessageStore;
import domain.PatternCommandTypeMapper;
import domain.TypedCommand;

import java.util.Map;

import static helper.ImplHelper.NO_COMMAND_EXECUTOR_MATCHED;

public class CommandExecutorFactory {

    private final Map<CommandType, CommandExecutor> patternCommandMap = PatternCommandTypeMapper.get();

    private final CentralSystemClock centralSystemClock;
    private final MessageStore messageStore;
    private final FollowsList followsList;

    public CommandExecutorFactory(CentralSystemClock centralSystemClock,
                                  MessageStore messageStore,
                                  FollowsList followsList) {
        this.centralSystemClock = centralSystemClock;
        this.messageStore = messageStore;
        this.followsList = followsList;
    }

    public CommandExecutor getCommand(TypedCommand userTypedCommand) {
        for (Map.Entry<CommandType, CommandExecutor>
                patternCommandExecutorPair: patternCommandMap.entrySet()) {

            CommandType pairKey = patternCommandExecutorPair.getKey();
            String pattern = pairKey.getMatchingPattern();
            String tokenSeparator = pairKey.getTokenSeparator();
            String[] fieldNames = pairKey.getFieldNames();

            CommandExecutor commandExecutor = preparedCommandExecutor(
                    userTypedCommand,
                    patternCommandExecutorPair,
                    pattern,
                    tokenSeparator,
                    fieldNames);

            if (commandExecutor != NO_COMMAND_EXECUTOR_MATCHED) {
                return commandExecutor;
            }
        }

        return NO_COMMAND_EXECUTOR_MATCHED;
    }

    private CommandExecutor preparedCommandExecutor(TypedCommand userTypedCommand,
                                                    Map.Entry<CommandType, CommandExecutor> patternCommandExecutorPair,
                                                    String pattern,
                                                    String tokenSeparator,
                                                    String[] fieldNames) {
        if (userTypedCommand.matches(pattern)) {
            CommandExecutor commandExecutor = patternCommandExecutorPair.getValue();

            CommandTokens commandTokens = userTypedCommand.parse(tokenSeparator, fieldNames);
            commandExecutor.setCommandTokens(commandTokens);
            commandExecutor.setMessageStore(messageStore);
            commandExecutor.setCentralSystemClock(centralSystemClock);
            commandExecutor.setFollowsList(followsList);
            return commandExecutor;
        }
        return NO_COMMAND_EXECUTOR_MATCHED;
    }
}