package engine.commands;

import elements.CommandLineEntry;
import elements.MessagePosted;

import java.util.List;

import static helper.ImplHelper.HYPHEN_SEPARATOR;
import static helper.ImplHelper.USERNAME_INDEX;

public class DisplayWallCommand extends CommandExecutor {

    @Override
    public String execute() {
        CommandLineEntry commandLineEntry = prepareCommandLineEntry();
        return getWallFor(commandLineEntry.getUserName());
    }

    private CommandLineEntry prepareCommandLineEntry() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUserName(tokens[USERNAME_INDEX].trim());
        return commandLineEntry;
    }

    private String getWallFor(String userName) {
        List<String> newFollowsList = addThisUserToFollowsList(userName);
        return getFormattedMessage(newFollowsList);
    }

    private String getFormattedMessage(List<String> followsList) {
        StringBuilder result = new StringBuilder();

        List<MessagePosted> messagesPosted = messageStore.getMessagesFor(followsList);

        for (MessagePosted messagePosted : messagesPosted) {
            buildTimeLine(result, messagePosted);
        }

        return result.toString();
    }

    private void buildTimeLine(StringBuilder result, MessagePosted messagePosted) {
        result.append(messagePosted.getUserName())
                .append(HYPHEN_SEPARATOR)
                .append(getFormattedMessage(messagePosted))
                .append(System.lineSeparator());
    }

    private List<String> addThisUserToFollowsList(String userName) {
        List<String> followsList = getFollowsListFor(userName);
        followsList.add(userName);
        return followsList;
    }

    private List<String> getFollowsListFor(String userName) {
        return followsList.getFor(userName);
    }
}