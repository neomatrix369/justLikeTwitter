package controller;

import domain.CommandLineEntry;

public class CommandLineController {
    private static final String POST_MESSAGE_SEPARATOR = "->";

    private static final int USERNAME_INDEX = 0;
    private static final int MESSAGE_INDEX = 1;

    public static CommandLineEntry parse(String userTypedMessage) {
        CommandLineEntry commandLineEntry = new CommandLineEntry();

        String[] splitUserTypedMessage = userTypedMessage.split(POST_MESSAGE_SEPARATOR);
        commandLineEntry.setUserName(splitUserTypedMessage[USERNAME_INDEX].trim());
        commandLineEntry.setMessage(splitUserTypedMessage[MESSAGE_INDEX].trim());

        return commandLineEntry;
    }
}