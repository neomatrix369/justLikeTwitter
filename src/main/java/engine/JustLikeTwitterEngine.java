package engine;

import elements.CommandType;
import elements.MessageStore;
import elements.TimeLineMessage;
import processors.DateTimeCentral;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JustLikeTwitterEngine {
    private final MessageStore messageStore = new MessageStore();
    private final Map<String, List<String>> followsList = new HashMap<>();
    private final DateTimeCentral dateTimeCentral;

    public JustLikeTwitterEngine(DateTimeCentral dateTimeCentral) {
        this.dateTimeCentral = dateTimeCentral;
    }

    public List<TimeLineMessage> getMessagesFor(String userName) {
        return messageStore.getMessagesFor(userName);
    }

    public String executeCommand(String userTypedCommand) {
        CommandExecutorFactory commandExecutorFactory =
                new CommandExecutorFactory(dateTimeCentral, messageStore, followsList);
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