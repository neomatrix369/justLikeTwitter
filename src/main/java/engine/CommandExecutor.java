package engine;

import elements.MessageStore;
import elements.TimeLineMessage;
import processors.DateTimeCentral;
import processors.DateTimeProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class CommandExecutor {
    protected static final int USERNAME_INDEX = 0;
    protected static final int MESSAGE_INDEX = 1;
    protected static final int OTHER_USERNAME_INDEX = 1;

    protected static final String MESSAGE_PATTERN_READ_POST = "%s %s";
    protected static final String HYPHEN_SEPARATOR = " - ";
    protected static final String NOTHING_REALLY = "";

    protected String[] tokens;
    protected DateTimeCentral dateTimeCentral;
    protected MessageStore messageStore;

    protected Map<String, List<String>> followsList;

    public abstract String execute();

    public void setParsedTokens(String[] tokens) {
        this.tokens = Arrays.copyOf(tokens, tokens.length);
    }

    public void setDateTimeCentral(DateTimeCentral dateTimeCentral) {
        this.dateTimeCentral = dateTimeCentral;
    }

    public void setMessageStore(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    public void setFollowsList(Map<String, List<String>> followsList) {
        this.followsList = followsList;
    }

    public String getFormattedMessage(TimeLineMessage timeLineMessage) {
        DateTimeProcessor dateTimeProcessor = new DateTimeProcessor(dateTimeCentral);

        return String.format(
                MESSAGE_PATTERN_READ_POST,
                timeLineMessage.getMessage(),
                dateTimeProcessor.whenMessageWasPosted(timeLineMessage.getDateTime()));
    }
}
