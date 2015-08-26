package com.codurance.formatters;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.message.MessagePosted;
import com.codurance.domain.message.MessageStore;
import com.codurance.domain.User;

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
        result.append(getFormattedMessage(messagePosted));
    }
}