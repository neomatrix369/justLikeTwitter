package processors;

import elements.CommandLineEntry;
import elements.CommandType;

public class CommandLineParser {
    public static final String POST_MESSAGE_TOKEN = " -> ";
    public static final String FOLLOWS_USER_TOKEN = " follows ";

    private static final int USERNAME_INDEX = 0;
    private static final int MESSAGE_INDEX = 1;
    private static final int OTHER_USERNAME_INDEX = 1;

    private final DateTimeCentral dateTimeCentral;

    public CommandLineParser(DateTimeCentral dateTimeCentral) {
        this.dateTimeCentral = dateTimeCentral;
    }

    public CommandLineEntry parse(String userTypedMessage, CommandType commandType) {
        CommandLineEntry commandLineEntry = new CommandLineEntry(dateTimeCentral);

        switch (commandType) {
            case POST_MESSAGE: {
                String[] splitUserTypedMessage = userTypedMessage.split(POST_MESSAGE_TOKEN);
                commandLineEntry.setUserName(splitUserTypedMessage[USERNAME_INDEX].trim());
                commandLineEntry.setTimeLineMessage(splitUserTypedMessage[MESSAGE_INDEX].trim());
                break;
            }

            case FOLLOWS_USER: {
                String[] splitUserTypedMessage = userTypedMessage.split(FOLLOWS_USER_TOKEN);
                commandLineEntry.setUserName(splitUserTypedMessage[USERNAME_INDEX].trim());
                commandLineEntry.setOtherUsersName(splitUserTypedMessage[OTHER_USERNAME_INDEX].trim());
                break;
            }
        }

        return commandLineEntry;
    }
}