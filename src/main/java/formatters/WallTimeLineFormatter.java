package formatters;

import clock.CentralSystemClock;
import domain.message.MessagePosted;
import domain.message.MessageStore;
import domain.Users;

import java.util.List;

public class WallTimeLineFormatter extends MessageFormatter {
    public WallTimeLineFormatter(CentralSystemClock centralSystemClock) {
        super(centralSystemClock);
    }

    public String getMessagesFor(Users followsList, MessageStore messageStore) {
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
