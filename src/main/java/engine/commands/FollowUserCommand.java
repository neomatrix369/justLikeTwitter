package engine.commands;

import elements.CommandLineEntry;

public class FollowUserCommand extends CommandExecutor {

    @Override
    public String execute() {
        CommandLineEntry commandLineEntry = prepareCommandLineEntry();

        followsList.addNewFor(commandLineEntry.getUserName(), commandLineEntry.getOtherUsersName());

        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntry prepareCommandLineEntry() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUserName(tokens[USERNAME_INDEX].trim());
        commandLineEntry.setOtherUsersName(tokens[OTHER_USERNAME_INDEX].trim());
        return commandLineEntry;
    }

}