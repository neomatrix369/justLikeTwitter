package com.codurance.formatters;

import com.codurance.domain.message.MessagePosted;
import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.message.MessageDate;

class MessageFormatter {

    static final String HYPHEN_SEPARATOR = " - ";
    private static final String MESSAGE_TEXT_WHEN_POSTED_PATTERN = "%s %s%s";

    private final CentralSystemClock centralSystemClock;

    MessageFormatter(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    String getFormattedMessage(MessagePosted messagePosted) {
        ClockTimeFormatter clockTimeFormatter = new ClockTimeFormatter(centralSystemClock);

        MessageDate messageDate = messagePosted.getMessageDate();
        return String.format(
                MESSAGE_TEXT_WHEN_POSTED_PATTERN,
                messagePosted.getMessageText(),
                clockTimeFormatter.whenMessageWasPosted(messageDate.toDate()),
                System.lineSeparator());
    }
}
