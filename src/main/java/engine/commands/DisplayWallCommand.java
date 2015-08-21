package engine.commands;

import elements.CommandLineEntry;
import elements.TimeLineMessage;
import engine.CommandExecutor;

import java.util.List;

public class DisplayWallCommand extends CommandExecutor {

    @Override
    public String execute() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(dateTimeCentral);
        commandLineEntry.setUserName(tokens[USERNAME_INDEX].trim());
        return getWallFor(commandLineEntry.getUserName());
    }

    private String getWallFor(String userName) {
        List<String> newFollowsList = addThisUserToFollowsList(userName);
        return getFormattedMessagesForWallUsing(newFollowsList);
    }

    private String getFormattedMessagesForWallUsing(List<String> followsList) {
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
        return followsList.get(userName);
    }
}
