package engine;

import controller.CommandLineController;
import domain.CommandLineEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterLikeEngine {

    private static final int BEFORE_FIRST_MESSAGE = 0;
    private Map<String, List<String>> timeline = new HashMap<>();

    private CommandLineController commandLineController = new CommandLineController();

    public List<String> getMessagesFor(String userName) {
        List<String> listOfMessages = timeline.get(userName);
        return listOfMessages;
    }

    public void executeCommand(String userTypedMessage) {
        postMessageToTimeline(userTypedMessage);
    }

    private void postMessageToTimeline(String userTypedMessage) {
        CommandLineEntry commandLineEntry = commandLineController.parse(userTypedMessage);
        List<String> existingMessages = getExistingMessagesFor(commandLineEntry.getUserName());
        combineMessages(existingMessages, commandLineEntry);
    }

    private void combineMessages(List<String> existingMessages,
                                 CommandLineEntry newCommandLineEntry) {
        existingMessages.add(BEFORE_FIRST_MESSAGE, newCommandLineEntry.getMessage());
        timeline.put(newCommandLineEntry.getUserName(), existingMessages);
    }

    private List<String> getExistingMessagesFor(String userName) {
        List<String> messages = timeline.get(userName);
        if (messages == null) {
            messages = new ArrayList<>();
        }
        return messages;
    }
}
