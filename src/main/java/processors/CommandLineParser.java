package processors;

import domain.CommandLineEntry;

public class CommandLineParser {
    private static final String POST_MESSAGE_SEPARATOR = "->";
    private static final int USERNAME_INDEX = 0;
    private static final int MESSAGE_INDEX = 1;

    private final DateTimeStampProvider dateTimeStampProvider;

    public CommandLineParser(DateTimeStampProvider dateTimeStampProvider) {
        this.dateTimeStampProvider = dateTimeStampProvider;
    }

    public CommandLineEntry parse(String userTypedMessage) {
        CommandLineEntry commandLineEntry = new CommandLineEntry(dateTimeStampProvider);

        String[] splitUserTypedMessage = userTypedMessage.split(POST_MESSAGE_SEPARATOR);
        commandLineEntry.setUserName(splitUserTypedMessage[USERNAME_INDEX].trim());
        commandLineEntry.setTimeLineMessage(splitUserTypedMessage[MESSAGE_INDEX].trim());

        return commandLineEntry;
    }
}