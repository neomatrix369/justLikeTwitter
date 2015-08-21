package engine.commands;

import elements.MessagePosted;

import java.util.List;

import static helper.ImplHelper.USERNAME_INDEX;

public class ReadPostCommand extends CommandExecutor {

    @Override
    public String execute() {
        String userName = tokens[USERNAME_INDEX];
        return getFormattedMessageFor(userName);
    }

    private String getFormattedMessageFor(String userName) {
        StringBuilder result = new StringBuilder();

        List<MessagePosted> messagePosteds = messageStore.getMessagesFor(userName);

        for (MessagePosted messagePosted : messagePosteds) {
            buildTimeLine(result, messagePosted);
        }

        return result.toString();
    }

    private void buildTimeLine(StringBuilder result, MessagePosted messagePosted) {
        result.append(getFormattedMessage(messagePosted))
              .append(System.lineSeparator());
    }
}
