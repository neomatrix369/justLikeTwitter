package formatters;

import clock.CentralSystemClock;
import domain.message.MessagePosted;
import domain.message.MessageStore;
import domain.User;

import java.util.List;

public class PersonalTimeLineFormatter extends MessageFormatter {

    public PersonalTimeLineFormatter(CentralSystemClock centralSystemClock) {
        super(centralSystemClock);
    }

    public String getMessagesFor(User user, MessageStore messageStore) {
        StringBuilder result = new StringBuilder();

        List<MessagePosted> messagesPosted = messageStore.getMessagesFor(user);

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