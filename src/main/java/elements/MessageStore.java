package elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MessageStore {
    private final List<MessagePosted> store = new ArrayList<>();

    private final Comparator<MessagePosted>
            sortByDateTimeInDescendingOrder = new Comparator<MessagePosted>() {
        @Override
        public int compare(MessagePosted first, MessagePosted second) {
            return compareToYieldResultsInDescendingOrder(first, second);
        }

        private int compareToYieldResultsInDescendingOrder(MessagePosted first, MessagePosted second) {
            return second.getDateTime().compareTo(first.getDateTime());
        }
    };

    public List<MessagePosted> getMessagesFor(String userName) {
        return getMessagesFor(Arrays.asList(userName));
    }

    public void addMessage(MessagePosted messages) {
        store.add(messages);
    }

    public List<MessagePosted> getMessagesFor(List<String> users) {
        List<MessagePosted> listForUser = new ArrayList<>();

        for (MessagePosted eachMessage: store) {
            if (users.contains(eachMessage.getUserName())) {
                listForUser.add(eachMessage);
            }
        }

        Collections.sort(listForUser, sortByDateTimeInDescendingOrder);
        return listForUser;
    }
}