package domain.message;

import domain.User;
import domain.Users;

import java.util.ArrayList;
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
            return second.getMessageDate().compareTo(first.getMessageDate());
        }
    };

    public List<MessagePosted> getMessagesFor(User user) {
        return getMessagesFor(new Users(user));
    }

    public void addMessage(MessagePosted messages) {
        store.add(messages);
    }

    public List<MessagePosted> getMessagesFor(Users users) {
        List<MessagePosted> listForUser = new ArrayList<>();

        filterMessagesFor(users, listForUser);

        Collections.sort(listForUser, sortByDateTimeInDescendingOrder);

        return listForUser;
    }

    private void filterMessagesFor(Users users, List<MessagePosted> listForUser) {
        for (MessagePosted eachMessage: store) {
            if (users.contains(eachMessage.toUser())) {
                listForUser.add(eachMessage);
            }
        }
    }
}