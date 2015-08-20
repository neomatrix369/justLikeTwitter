package engine.commands;

import elements.CommandLineEntry;
import engine.CommandExecutor;

public class PostMessageCommand extends CommandExecutor {

    @Override
    public String getResults() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(dateTimeCentral);

        commandLineEntry.setUserName(tokens[USERNAME_INDEX].trim());
        commandLineEntry.setTimeLineMessage(commandLineEntry.getUserName(), tokens[MESSAGE_INDEX].trim());

        messageStore.addMessage(commandLineEntry.getTimeLineMessage());

        return NOTHING_REALLY;
    }
}
