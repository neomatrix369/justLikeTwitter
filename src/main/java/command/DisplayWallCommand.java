package command;

import domain.CommandLineEntry;
import domain.MessagePosted;
import domain.User;
import domain.Users;

import java.util.List;

import static helper.ImplHelper.HYPHEN_SEPARATOR;
import static helper.ImplHelper.NO_FOLLOWS_RETURNED;
import static helper.ImplHelper.USERNAME_INDEX;

public class DisplayWallCommand extends CommandExecutor {

    @Override
    public String execute() {
        CommandLineEntry commandLineEntry = prepareCommandLineEntry();
        return getWallFor(commandLineEntry.getUser());
    }

    private CommandLineEntry prepareCommandLineEntry() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUser(new User(tokens[USERNAME_INDEX]));
        return commandLineEntry;
    }

    private String getWallFor(User user) {
        Users newFollowsList = addThisUserToFollowsList(user);
        return getFormattedMessage(newFollowsList);
    }

    private String getFormattedMessage(Users followsList) {
        StringBuilder result = new StringBuilder();

        List<MessagePosted> messagesPosted = messageStore.getMessagesFor(followsList);

        for (MessagePosted messagePosted : messagesPosted) {
            buildTimeLine(result, messagePosted);
        }

        return result.toString();
    }

    private void buildTimeLine(StringBuilder result, MessagePosted messagePosted) {
        result.append(messagePosted.getUser())
                .append(HYPHEN_SEPARATOR)
                .append(getFormattedMessage(messagePosted))
                .append(System.lineSeparator());
    }

    private Users addThisUserToFollowsList(User user) {
        Users followsList = getFollowsListFor(user);
        followsList.add(user);
        return followsList;
    }

    private Users getFollowsListFor(User user) {
        Users list = followsList.getFollowsFor(user);
        if (list == NO_FOLLOWS_RETURNED) {
            return new Users();
        }
        return list;
    }
}