package engine;

import elements.CommandType;
import elements.MessageStore;
import engine.commands.DisplayWallCommand;
import engine.commands.FollowUserCommand;
import engine.commands.PostMessageCommand;
import engine.commands.ReadPostCommand;
import processors.DateTimeCentral;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static elements.CommandType.DISPLAY_WALL;
import static elements.CommandType.FOLLOWS_USER;
import static elements.CommandType.POST_MESSAGE;
import static elements.CommandType.READ_POST;

public class ExecuteCommandFactory {

    private final DateTimeCentral dateTimeCentral;
    private final MessageStore messageStore;
    private Map<String, List<String>> followsList;

    private static Map<CommandType, CommandExecutor> patternCommandMap =
            new HashMap<CommandType, CommandExecutor>() {
        {
            put(POST_MESSAGE, new PostMessageCommand() );
            put(READ_POST, new ReadPostCommand() );
            put(FOLLOWS_USER, new FollowUserCommand() );
            put(DISPLAY_WALL, new DisplayWallCommand() );
        }
    };

    public ExecuteCommandFactory(DateTimeCentral dateTimeCentral,
                                 MessageStore messageStore,
                                 Map<String, List<String>> followsList) {
        this.dateTimeCentral = dateTimeCentral;
        this.messageStore = messageStore;
        this.followsList = followsList;
    }

    public CommandExecutor getCommand(String userTypedCommand) {
        for (Map.Entry<CommandType, CommandExecutor> patternCommandExecutorPair: patternCommandMap.entrySet()) {
            CommandType pairKey = patternCommandExecutorPair.getKey();
            String pattern = pairKey.getMatchingPattern();
            String separator = pairKey.getTokenSeparator();

            if (userTypedCommand.matches(pattern)) {
                CommandExecutor commandExecutor = patternCommandExecutorPair.getValue();
                String[] tokens = userTypedCommand.split(separator);
                commandExecutor.setParsedTokens(tokens);
                commandExecutor.setMessageStore(messageStore);
                commandExecutor.setDateTimeCentral(dateTimeCentral);
                commandExecutor.setFollowsList(followsList);
                return commandExecutor;
            }
        }

        return null;
    }
}
