package engine;

import elements.CommandLineEntry;
import processors.DateTimeStampProvider;
import elements.TimeLineMessage;
import processors.CommandLineParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JustLikeTwitterEngine {

    private static final int BEFORE_FIRST_MESSAGE = 0;
    private static final String SPACE_DELIMITER = " ";

    private final Map<String, List<TimeLineMessage>> dataStoreForAllUsers = new HashMap<>();

    private final CommandLineParser commandLineParser;

    public JustLikeTwitterEngine(DateTimeStampProvider dateTimeStampProvider) {
        this.commandLineParser = new CommandLineParser(dateTimeStampProvider);
    }

    public List<TimeLineMessage> getMessagesFor(String userName) {
        return dataStoreForAllUsers.get(userName);
    }

    public String executeCommand(String userTypedCommands) {
        String result = "";

        if (userTypedCommands.contains(SPACE_DELIMITER)) {
            postMessageToTimeline(userTypedCommands);
        } else {
            result = getTimeLineFor(userTypedCommands);
        }

        return result;
    }

    public String getTimeLineFor(String userName) {
        String result = "";
        List<TimeLineMessage> usersMessages = dataStoreForAllUsers.get(userName);
        for (TimeLineMessage usersMessage: usersMessages) {
            result += usersMessage.toString() + System.lineSeparator();
        }
        return result;
    }

    private void postMessageToTimeline(String userTypedMessage) {
        CommandLineEntry commandLineEntry = commandLineParser.parse(userTypedMessage);
        List<TimeLineMessage> existingMessages = getExistingMessagesFor(commandLineEntry.getUserName());
        combineMessages(existingMessages, commandLineEntry);
    }

    private void combineMessages(List<TimeLineMessage> existingMessages,
                                 CommandLineEntry newCommandLineEntry) {
        existingMessages.add(BEFORE_FIRST_MESSAGE, newCommandLineEntry.getTimeLineMessage());
        dataStoreForAllUsers.put(newCommandLineEntry.getUserName(), existingMessages);
    }

    private List<TimeLineMessage> getExistingMessagesFor(String userName) {
        List<TimeLineMessage> messages = dataStoreForAllUsers.get(userName);
        if (messages == null) {
            messages = new ArrayList<>();
        }
        return messages;
    }
}