package processors;

import elements.CommandLineEntry;
import elements.CommandType;

public class CommandLineParser {
    private static final int USERNAME_INDEX = 0;
    private static final int MESSAGE_INDEX = 1;
    private static final int OTHER_USERNAME_INDEX = 1;

    private final DateTimeCentral dateTimeCentral;

    public CommandLineParser(DateTimeCentral dateTimeCentral) {
        this.dateTimeCentral = dateTimeCentral;
    }

    public CommandLineEntry parse(String userTypedMessage, CommandType commandType) {
        CommandLineEntry commandLineEntry = new CommandLineEntry(dateTimeCentral);
        String[] splitUserTypedMessage = userTypedMessage.split(commandType.getToken());

        switch (commandType) {
            case POST_MESSAGE: {
                commandLineEntry.setUserName(splitUserTypedMessage[USERNAME_INDEX].trim());
                commandLineEntry.setTimeLineMessage(
                        commandLineEntry.getUserName(),
                        splitUserTypedMessage[MESSAGE_INDEX].trim());
                break;
            }

            case FOLLOWS_USER: {
                commandLineEntry.setUserName(splitUserTypedMessage[USERNAME_INDEX].trim());
                commandLineEntry.setOtherUsersName(splitUserTypedMessage[OTHER_USERNAME_INDEX].trim());
                break;
            }

            case DISPLAY_WALL: {
                commandLineEntry.setUserName(splitUserTypedMessage[USERNAME_INDEX].trim());
                break;
            }
        }

        return commandLineEntry;
    }
}