package com.codurance.functionality.command;

import com.codurance.command.CommandLineEntryFollowsUser;
import com.codurance.command.CommandPattern;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;

import static com.codurance.command.Fields.FOLLOWS_USER_FIELD;
import static com.codurance.command.Fields.USER_FIELD;
import static com.codurance.command.Fields.createNewUserFrom;
import static com.codurance.helper.ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;

public class FollowsUserCommand extends CommandExecutorImpl {

    public FollowsUserCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        CommandLineEntryFollowsUser commandLineEntry = prepareCommandLineEntry();
        followsList.addNewUserFollowed(commandLineEntry.getUser(), commandLineEntry.getFollowsUser());

        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntryFollowsUser prepareCommandLineEntry() {
        return new CommandLineEntryFollowsUser(
                createNewUserFrom(commandTokens, USER_FIELD),
                createNewUserFrom(commandTokens, FOLLOWS_USER_FIELD)
        );
    }
}