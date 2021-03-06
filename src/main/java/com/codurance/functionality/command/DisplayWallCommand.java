package com.codurance.functionality.command;

import com.codurance.command.CommandPattern;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.User;
import com.codurance.domain.Users;
import com.codurance.formatters.WallTimeLineFormatter;

import static com.codurance.command.Fields.createNewUserFrom;
import static com.codurance.command.Fields.USER_FIELD;

public class DisplayWallCommand extends CommandExecutorImpl {

    public DisplayWallCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        User user = createNewUserFrom(commandTokens, USER_FIELD);

        return getWallFor(user);
    }

    private String getWallFor(User user) {
        Users users = followees.getFolloweesFor(user);

        WallTimeLineFormatter wallTimeLineFormatter = new WallTimeLineFormatter(centralSystemClock);

        return wallTimeLineFormatter.getMessagesFor(users, messageStore);
    }

}