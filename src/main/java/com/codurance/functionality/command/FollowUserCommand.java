package com.codurance.functionality.command;

import com.codurance.command.CommandLineEntryFollowsUser;
import com.codurance.command.CommandPattern;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;
import com.codurance.helper.ImplHelper;

import static com.codurance.command.CommandLineEntryParser.createNewUserFrom;
import static com.codurance.command.Fields.FOLLOWS_USER_FIELD;
import static com.codurance.command.Fields.USER_FIELD;

public class FollowUserCommand extends CommandExecutorImpl {

    public FollowUserCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        CommandLineEntryFollowsUser commandLineEntry = prepareCommandLineEntry();
        followsList.addNewFollowTo(commandLineEntry.getUser(), commandLineEntry.getFollowsUser());

        return ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntryFollowsUser prepareCommandLineEntry() {
        CommandLineEntryFollowsUser commandLineEntry = new CommandLineEntryFollowsUser(
                createNewUserFrom(commandTokens, USER_FIELD),
                createNewUserFrom(commandTokens, FOLLOWS_USER_FIELD)
        );

        return commandLineEntry;
    }
}