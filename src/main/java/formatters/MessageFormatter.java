package formatters;

import clock.CentralSystemClock;
import domain.MessageDate;
import domain.MessagePosted;

public class MessageFormatter {

    private static final String MESSAGE_TEXT_WHEN_POSTED_PATTERN = "%s %s";
    protected static final String HYPHEN_SEPARATOR = " - ";

    private CentralSystemClock centralSystemClock;

    public MessageFormatter(CentralSystemClock centralSystemClock) {
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
