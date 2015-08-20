package engine;

import elements.CommandLineEntry;
import elements.TimeLineMessage;
import processors.CommandLineParser;
import processors.DateTimeCentral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static elements.CommandType.FOLLOWS_USER;
import static elements.CommandType.POST_MESSAGE;
import static processors.CommandLineParser.FOLLOWS_USER_TOKEN;
import static processors.CommandLineParser.POST_MESSAGE_TOKEN;

public class JustLikeTwitterEngine {

    private static final int BEFORE_FIRST_MESSAGE = 0;

    private final Map<String, List<TimeLineMessage>> messagesStoreForAllUsers = new HashMap<>();
    private final Map<String, List<String>> followsListForAllUsers = new HashMap<>();

    private final CommandLineParser commandLineParser;

    public JustLikeTwitterEngine(DateTimeCentral dateTimeCentral) {
        this.commandLineParser = new CommandLineParser(dateTimeCentral);
    }

    public List<TimeLineMessage> getMessagesFor(String userName) {
        return messagesStoreForAllUsers.get(userName);
    }

    public String executeCommand(String userTypedCommands) {
        String result = "";

        if (userTypedCommands.contains(POST_MESSAGE_TOKEN)) {
            postMessageToTimeline(userTypedCommands);
        } else if (userTypedCommands.contains(FOLLOWS_USER_TOKEN)) {
            addToFollowsList(userTypedCommands);
        } else {
            result = getTimeLineFor(userTypedCommands);
        }

        return result;
    }

    private void postMessageToTimeline(String userTypedCommands) {
        CommandLineEntry commandLineEntry = commandLineParser.parse(userTypedCommands, POST_MESSAGE);
        List<TimeLineMessage> existingMessages = getExistingMessagesFor(commandLineEntry.getUserName());
        combineMessages(existingMessages, commandLineEntry);
    }

    private void combineMessages(List<TimeLineMessage> existingMessages,
                                 CommandLineEntry newCommandLineEntry) {
        existingMessages.add(BEFORE_FIRST_MESSAGE, newCommandLineEntry.getTimeLineMessage());
        messagesStoreForAllUsers.put(newCommandLineEntry.getUserName(), existingMessages);
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

    private List<TimeLineMessage> getExistingMessagesFor(String userName) {
        List<TimeLineMessage> messages = messagesStoreForAllUsers.get(userName);

        if (messages == null) {
            messages = new ArrayList<>();
        }

        return messages;
    }

    public String getTimeLineFor(String userName) {
        String result = "";
        List<TimeLineMessage> usersMessages = messagesStoreForAllUsers.get(userName);
        for (TimeLineMessage usersMessage: usersMessages) {
            result += usersMessage.toString() + System.lineSeparator();
        }
        return result;
    }

    public List<String> getFollowsListFor(String userName) {
        return followsListForAllUsers.get(userName);
    }
}