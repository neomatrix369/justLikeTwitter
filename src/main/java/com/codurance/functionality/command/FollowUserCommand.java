package com.codurance.functionality.command;

import com.codurance.command.CommandLineEntry;
import com.codurance.command.CommandPattern;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;
import com.codurance.helper.ImplHelper;

public class FollowUserCommand extends CommandExecutorImpl {

    public FollowUserCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        CommandLineEntry commandLineEntry = prepareCommandLineEntry();
        followsList.addNewFollowTo(commandLineEntry.getUser(), commandLineEntry.getOtherUsersName());

        return ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntry prepareCommandLineEntry() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUser(createNewUserFrom(commandTokens, ImplHelper.USER_FIELD));
        commandLineEntry.setOtherUsersName(createNewUserFrom(commandTokens, ImplHelper.OTHER_USER_FIELD));

        return commandLineEntry;
    }
}