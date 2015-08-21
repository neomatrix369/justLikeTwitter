package engine;

import clock.CentralSystemClock;
import elements.CommandType;
import elements.FollowsList;
import elements.MessageStore;
import elements.PatternCommandTypeMapper;
import engine.commands.CommandExecutor;

import java.util.Map;

import static helper.ImplHelper.NO_COMMAND_EXECUTOR_MATCHED;

class CommandExecutorFactory {

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

    public CommandExecutor getCommand(String userTypedCommand) {
        for (Map.Entry<CommandType, CommandExecutor>
                patternCommandExecutorPair: patternCommandMap.entrySet()) {

            CommandType pairKey = patternCommandExecutorPair.getKey();
            String pattern = pairKey.getMatchingPattern();
            String tokenSeparator = pairKey.getTokenSeparator();

            CommandExecutor commandExecutor = preparedCommandExecutor(
                    userTypedCommand,
                    patternCommandExecutorPair,
                    pattern,
                    tokenSeparator);

            if (commandExecutor != NO_COMMAND_EXECUTOR_MATCHED) {
                return commandExecutor;
            }
        }

        return NO_COMMAND_EXECUTOR_MATCHED;
    }

    private CommandExecutor preparedCommandExecutor(String userTypedCommand,
                                                    Map.Entry<CommandType, CommandExecutor> patternCommandExecutorPair,
                                                    String pattern,
                                                    String tokenSeparator) {
        if (userTypedCommand.matches(pattern)) {
            CommandExecutor commandExecutor = patternCommandExecutorPair.getValue();

            String[] tokens = userTypedCommand.split(tokenSeparator);
            commandExecutor.setParsedTokens(tokens);
            commandExecutor.setMessageStore(messageStore);
            commandExecutor.setCentralSystemClock(centralSystemClock);
            commandExecutor.setFollowsList(followsList);
            return commandExecutor;
        }
        return NO_COMMAND_EXECUTOR_MATCHED;
    }
}