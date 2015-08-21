package engine.commands;

import elements.CommandLineEntry;
import engine.CommandExecutor;

public class PostMessageCommand extends CommandExecutor {

    @Override
    public String execute() {
        CommandLineEntry commandLineEntry = prepareCommandLineEntry();
        messageStore.addMessage(commandLineEntry.getTimeLineMessage());
        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntry prepareCommandLineEntry() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUserName(tokens[USERNAME_INDEX].trim());
        commandLineEntry.setTimeLineMessage(commandLineEntry.getUserName(), tokens[MESSAGE_INDEX].trim());
        return commandLineEntry;
    }
}
