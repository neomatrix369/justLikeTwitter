package engine;

import elements.CommandLineEntry;
import elements.MessageStore;
import elements.TimeLineMessage;
import processors.CommandLineParser;
import processors.DateTimeCentral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static elements.CommandType.DISPLAY_WALL;
import static elements.CommandType.FOLLOWS_USER;
import static elements.CommandType.POST_MESSAGE;

public class JustLikeTwitterEngine {

    private static final String HYPHEN_SEPARATOR = " - ";

    private final MessageStore messagesStoreForAllUsers = new MessageStore();
    private final Map<String, List<String>> followsListForAllUsers = new HashMap<>();

    private final CommandLineParser commandLineParser;

    public JustLikeTwitterEngine(DateTimeCentral dateTimeCentral) {
        this.commandLineParser = new CommandLineParser(dateTimeCentral);
    }

    public List<TimeLineMessage> getMessagesFor(String userName) {
        return messagesStoreForAllUsers.getMessagesFor(userName);
    }

    public String executeCommand(String userTypedCommands) {
        String result = "";

        if (userTypedCommands.contains(POST_MESSAGE.getToken())) {
            postMessageToTimeline(userTypedCommands);
        } else if (userTypedCommands.contains(FOLLOWS_USER.getToken())) {
            addToFollowsList(userTypedCommands);
        } else if (userTypedCommands.contains(DISPLAY_WALL.getToken())) {
            result = displayWall(userTypedCommands);
        } else {
            result = getTimeLineFor(userTypedCommands);
        }

        return result;
    }

    private void postMessageToTimeline(String userTypedCommands) {
        CommandLineEntry commandLineEntry = commandLineParser.parse(userTypedCommands, POST_MESSAGE);
        messagesStoreForAllUsers.addMessage(commandLineEntry.getTimeLineMessage());
    }

    private void addToFollowsList(String userTypedCommands) {
        CommandLineEntry commandLineEntry = commandLineParser.parse(userTypedCommands, FOLLOWS_USER);
        List<String> existingFollowsList = getExistingFollowsListFor(commandLineEntry.getUserName());
        combineFollowsList(existingFollowsList, commandLineEntry);
    }

    private List<String> getExistingFollowsListFor(String userName) {
        List<String> followsList = followsListForAllUsers.get(userName);

        if (followsList == null) {
            followsList = new ArrayList<>();
        }

        return followsList;
    }

    private void combineFollowsList(List<String> existingFollowsList, CommandLineEntry newCommandLineEntry) {
        existingFollowsList.add(newCommandLineEntry.getOtherUsersName());
        followsListForAllUsers.put(newCommandLineEntry.getUserName(), existingFollowsList);
    }

    private String displayWall(String userTypedCommands) {
        CommandLineEntry commandLineEntry = commandLineParser.parse(userTypedCommands, DISPLAY_WALL);
        return getWallFor(commandLineEntry.getUserName());
    }

    public String getTimeLineFor(String userName) {
        StringBuilder result = new StringBuilder();
        List<TimeLineMessage> usersMessages = messagesStoreForAllUsers.getMessagesFor(userName);
        for (TimeLineMessage usersMessage: usersMessages) {
            result.append(usersMessage.toString())
                  .append(System.lineSeparator());
        }
        return result.toString();
    }

    public List<String> getFollowsListFor(String userName) {
        return followsListForAllUsers.get(userName);
    }

    public String getWallFor(String userName) {
        List<String> newFollowsList = addThisUserToFollowsList(userName);
        return getFormattedMessagesForWallUsing(newFollowsList);
    }

    private String getFormattedMessagesForWallUsing(List<String> followsList) {
        StringBuilder result = new StringBuilder();

        List<TimeLineMessage> usersMessages = messagesStoreForAllUsers.getMessagesFor(followsList);

        for (TimeLineMessage usersMessage: usersMessages) {
            result.append(usersMessage.getUserName())
                    .append(HYPHEN_SEPARATOR)
                    .append(usersMessage.toString())
                    .append(System.lineSeparator());
        }

        return result.toString();
    }

    private List<String> addThisUserToFollowsList(String userName) {
        List<String> followsList = getFollowsListFor(userName);
        followsList.add(userName);
        return followsList;
    }
}