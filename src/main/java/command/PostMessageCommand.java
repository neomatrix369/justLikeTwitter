package command;

import domain.CommandLineEntry;
import domain.User;

import static helper.ImplHelper.MESSAGE_INDEX;
import static helper.ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;
import static helper.ImplHelper.USERNAME_INDEX;

public class PostMessageCommand extends CommandExecutor {

    @Override
    public String execute() {
        CommandLineEntry commandLineEntry = prepareCommandLineEntry();
        messageStore.addMessage(commandLineEntry.getMessagePosted());
        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntry prepareCommandLineEntry() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUser(new User(tokens[USERNAME_INDEX]));
        commandLineEntry.setMessagePosted(commandLineEntry.getUser(), tokens[MESSAGE_INDEX].trim());
        return commandLineEntry;
    }
}
