package formatters;

import clock.CentralSystemClock;
import domain.message.MessageDate;
import domain.message.MessagePosted;

class MessageFormatter {

    static final String HYPHEN_SEPARATOR = " - ";
    private static final String MESSAGE_TEXT_WHEN_POSTED_PATTERN = "%s %s";

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
                clockTimeFormatter.whenMessageWasPosted(messageDate.toDate()));
    }
}
