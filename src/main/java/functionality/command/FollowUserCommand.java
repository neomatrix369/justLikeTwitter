package functionality.command;

import domain.command.CommandLineEntry;
import domain.command.CommandType;
import domain.command.UserTypedCommand;

import static helper.ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;
import static helper.ImplHelper.OTHER_USER_FIELD;
import static helper.ImplHelper.USER_FIELD;

public class FollowUserCommand extends CommandExecutorImpl {

    public FollowUserCommand(CommandType commandType) {
        super(commandType);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        CommandLineEntry commandLineEntry = prepareCommandLineEntry();

        followsList.addNewFollowOf(commandLineEntry.getUser(), commandLineEntry.getOtherUsersName());

        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntry prepareCommandLineEntry() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUser(createNewUserFrom(commandTokens, USER_FIELD));
        commandLineEntry.setOtherUsersName(createNewUserFrom(commandTokens, OTHER_USER_FIELD));
        return commandLineEntry;
    }
}