package engine.commands;

import elements.TimeLineMessage;
import engine.CommandExecutor;

import java.util.List;

public class ReadPostCommand extends CommandExecutor {

    @Override
    public String execute() {
        StringBuilder result = new StringBuilder();
        String userName = tokens[USERNAME_INDEX];
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
