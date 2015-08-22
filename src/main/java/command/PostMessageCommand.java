package command;

import domain.CommandLineEntry;

import static helper.ImplHelper.USER_FIELD;
import static helper.ImplHelper.MESSAGE_TEXT_FIELD;
import static helper.ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;

public class PostMessageCommand extends CommandExecutor {

    @Override
    public String execute() {
        CommandLineEntry commandLineEntry = prepareCommandLineEntry();
        messageStore.addMessage(commandLineEntry.getMessagePosted());
        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntry prepareCommandLineEntry() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUser(createNewUserInstanceFrom(commandTokens, USER_FIELD));
        commandLineEntry.setMessagePosted(
                commandLineEntry.getUser(),
                createNewMessageTextInstanceFrom(commandTokens, MESSAGE_TEXT_FIELD));
        return commandLineEntry;
    }
}
