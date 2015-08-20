package elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MessageStore {
    private final List<TimeLineMessage> store = new ArrayList<>();

    private final Comparator<TimeLineMessage>
            sortByDateTimeInDescendingOrder = new Comparator<TimeLineMessage>() {
        @Override
        public int compare(TimeLineMessage first, TimeLineMessage second) {
            return compareToYieldResultsInDescendingOrder(first, second);
        }

        private int compareToYieldResultsInDescendingOrder(TimeLineMessage first, TimeLineMessage second) {
            return second.getDateTime().compareTo(first.getDateTime());
        }
    };

    public List<TimeLineMessage> getMessagesFor(String userName) {
        return getMessagesFor(Arrays.asList(userName));
    }

    public void addMessage(TimeLineMessage messages) {
        store.add(messages);
    }

    public List<TimeLineMessage> getMessagesFor(List<String> users) {
        List<TimeLineMessage> listForUser = new ArrayList<>();

        for (TimeLineMessage eachMessage: store) {
            if (users.contains(eachMessage.getUserName())) {
                listForUser.add(eachMessage);
            }
        }

        Collections.sort(listForUser, sortByDateTimeInDescendingOrder);
        return listForUser;
    }
}