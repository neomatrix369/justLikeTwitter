package engine.commands;

import elements.CommandLineEntry;
import elements.TimeLineMessage;

import java.util.List;

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

        List<TimeLineMessage> timeLineMessages = messageStore.getMessagesFor(followsList);

        for (TimeLineMessage timeLineMessage: timeLineMessages) {
            buildTimeLine(result, timeLineMessage);
        }

        return result.toString();
    }

    private void buildTimeLine(StringBuilder result, TimeLineMessage timeLineMessage) {
        result.append(timeLineMessage.getUserName())
                .append(HYPHEN_SEPARATOR)
                .append(getFormattedMessage(timeLineMessage))
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