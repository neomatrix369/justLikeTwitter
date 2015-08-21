package command;

import domain.CommandLineEntry;
import domain.User;

import static helper.ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;
import static helper.ImplHelper.OTHER_USERNAME_INDEX;
import static helper.ImplHelper.USERNAME_INDEX;

public class FollowUserCommand extends CommandExecutor {

    @Override
    public String execute() {
        CommandLineEntry commandLineEntry = prepareCommandLineEntry();

        followsList.addNewFollowOf(commandLineEntry.getUser(), commandLineEntry.getOtherUsersName());

        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntry prepareCommandLineEntry() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUser(new User(tokens[USERNAME_INDEX]));
        commandLineEntry.setOtherUsersName(new User(tokens[OTHER_USERNAME_INDEX]));
        return commandLineEntry;
    }
}