package command;

import domain.MessagePosted;

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

        List<MessagePosted> messagesPosted = messageStore.getMessagesFor(userName);

        for (MessagePosted messagePosted : messagesPosted) {
            buildTimeLine(result, messagePosted);
        }

        return result.toString();
    }

    private void buildTimeLine(StringBuilder result, MessagePosted messagePosted) {
        result.append(getFormattedMessage(messagePosted))
              .append(System.lineSeparator());
    }
}
