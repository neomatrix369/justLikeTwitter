package com.codurance.functionality.command;

import com.codurance.command.CommandPattern;
import com.codurance.domain.Users;
import com.codurance.domain.User;
import com.codurance.command.CommandLineEntry;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;
import com.codurance.formatters.WallTimeLineFormatter;

import static com.codurance.helper.ImplHelper.USER_FIELD;

public class DisplayWallCommand extends CommandExecutorImpl {

    public DisplayWallCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUser(createNewUserFrom(commandTokens, USER_FIELD));

        return getWallFor(commandLineEntry.getUser());
    }

    private String getWallFor(User user) {
        Users newFollowsList = addThisUserToFollowsList(user);
        WallTimeLineFormatter wallTimeLineFormatter = new WallTimeLineFormatter(centralSystemClock);

        return wallTimeLineFormatter.getMessagesFor(newFollowsList, messageStore);
    }

    private Users addThisUserToFollowsList(User user) {
        Users existingFollowsList = followsList.getFollowsFor(user);
        existingFollowsList.add(user);

        return existingFollowsList;
    }
}