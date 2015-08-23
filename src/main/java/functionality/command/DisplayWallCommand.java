package functionality.command;

import domain.command.CommandLineEntry;
import domain.command.CommandType;
import domain.User;
import domain.command.UserTypedCommand;
import domain.Users;
import formatters.WallTimeLineFormatter;

import static helper.ImplHelper.USER_FIELD;

public class DisplayWallCommand extends CommandExecutorImpl {

    public DisplayWallCommand(CommandType commandType) {
        super(commandType);
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