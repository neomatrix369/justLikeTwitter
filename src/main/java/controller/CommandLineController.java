package controller;

public class CommandLineController {
    private static final String POST_MESSAGE_SEPARATOR = "->";
    private static final int USERNAME_INDEX = 0;
    private static final int MESSAGE_INDEX = 1;

    private String userName;
    private String message;

    private CommandLineController() {
        super();
    }

    public static CommandLineController parse(String userTypedMessage) {
        CommandLineController commandLineController = new CommandLineController();

        String[] splitUserTypedMessage = userTypedMessage.split(POST_MESSAGE_SEPARATOR);
        commandLineController.userName = splitUserTypedMessage[USERNAME_INDEX].trim();
        commandLineController.message = splitUserTypedMessage[MESSAGE_INDEX].trim();

        return commandLineController;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}