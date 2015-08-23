package command;

import domain.CommandLineEntry;
import domain.CommandType;
import domain.MessagePosted;
import domain.User;
import domain.UserTypedCommand;
import domain.Users;

import java.util.List;

import static helper.ImplHelper.HYPHEN_SEPARATOR;
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
        return getFormattedMessage(newFollowsList);
    }

    private Users addThisUserToFollowsList(User user) {
        Users existingFollowsList = followsList.getFollowsFor(user);
        existingFollowsList.add(user);
        return existingFollowsList;
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
        result.append(messagePosted.toUser())
                .append(HYPHEN_SEPARATOR)
                .append(getFormattedMessage(messagePosted))
                .append(System.lineSeparator());
    }
}