package engine.commands;

import elements.TimeLineMessage;
import engine.CommandExecutor;

import java.util.List;

public class ReadPostCommand extends CommandExecutor {
    @Override
    public String getResults() {
        StringBuilder result = new StringBuilder();
        String userName = tokens[USERNAME_INDEX];
        List<TimeLineMessage> usersMessages = messageStore.getMessagesFor(userName);

        for (TimeLineMessage usersMessage: usersMessages) {
            result.append(usersMessage.toString())
                  .append(System.lineSeparator());
        }

        return result.toString();
    }
}
