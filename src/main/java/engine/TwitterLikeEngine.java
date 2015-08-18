package engine;

import controller.CommandLineController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterLikeEngine {

    private static final int BEFORE_FIRST_MESSAGE = 0;
    private Map<String, List<String>> timeline = new HashMap<>();

    public List<String> getTimelineFor(String userName) {
        List<String> listOfMessages = timeline.get(userName);
        return listOfMessages;
    }

    public void executeCommand(String userTypedMessage) {
        postMessageToTimeline(userTypedMessage);
    }

    private void postMessageToTimeline(String userTypedMessage) {
        CommandLineController commandLineController = CommandLineController.parse(userTypedMessage);
        List<String> existingMessages = getExistingMessagesFor(commandLineController.getUserName());
        combineMessages(existingMessages, commandLineController);
    }

    private void combineMessages(List<String> existingMessages,
                                 CommandLineController newCommandLineController) {
        existingMessages.add(BEFORE_FIRST_MESSAGE, newCommandLineController.getMessage());
        timeline.put(newCommandLineController.getUserName(), existingMessages);
    }

    private List<String> getExistingMessagesFor(String userName) {
        List<String> messages = timeline.get(userName);
        if (messages == null) {
            messages = new ArrayList<>();
        }
        return messages;
    }
}
