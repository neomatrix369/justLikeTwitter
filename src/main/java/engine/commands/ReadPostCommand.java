package engine.commands;

import elements.TimeLineMessage;

import java.util.List;

public class ReadPostCommand extends CommandExecutor {

    @Override
    public String execute() {
        String userName = tokens[USERNAME_INDEX];
        return getFormattedMessageFor(userName);
    }

    private String getFormattedMessageFor(String userName) {
        StringBuilder result = new StringBuilder();

        List<TimeLineMessage> timeLineMessages = messageStore.getMessagesFor(userName);

        for (TimeLineMessage timeLineMessage: timeLineMessages) {
            buildTimeLine(result, timeLineMessage);
        }

        return result.toString();
    }

    private void buildTimeLine(StringBuilder result, TimeLineMessage timeLineMessage) {
        result.append(getFormattedMessage(timeLineMessage))
              .append(System.lineSeparator());
    }
}
