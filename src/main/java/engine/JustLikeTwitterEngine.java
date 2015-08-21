package engine;

import elements.CommandType;
import elements.MessageStore;
import clock.CentralSystemClock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JustLikeTwitterEngine {
    private final MessageStore messageStore;
    private final Map<String, List<String>> followsList = new HashMap<>();
    private final CentralSystemClock centralSystemClock;

    public JustLikeTwitterEngine(MessageStore messageStore,
                                 CentralSystemClock centralSystemClock) {
        this.messageStore = messageStore;
        this.centralSystemClock = centralSystemClock;
    }

    public String executeCommand(String userTypedCommand) {
        CommandExecutorFactory commandExecutorFactory =
                new CommandExecutorFactory(centralSystemClock, messageStore, followsList);
        CommandExecutor command = commandExecutorFactory.getCommand(userTypedCommand);
        return command.execute();
    }

    public String getTimeLineFor(String userName) {
        return executeCommand(userName);
    }

    public String getWallFor(String userName) {
        String userWall = userName + CommandType.DISPLAY_WALL.getTokenSeparator();
        return executeCommand(userWall);
    }

    public List<String> getFollowsListFor(String userName) {
        return followsList.get(userName);
    }
}