package functionality.command;

import domain.User;
import domain.Users;
import domain.command.CommandLineEntry;
import domain.command.CommandPattern;
import domain.command.Fields;
import domain.command.UserTypedCommand;
import formatters.WallTimeLineFormatter;

import static helper.ImplHelper.USER_FIELD;

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