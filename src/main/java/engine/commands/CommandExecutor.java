package engine.commands;

import clock.CentralSystemClock;
import clock.ClockTimeFormatter;
import elements.FollowsList;
import elements.MessageStore;
import elements.TimeLineMessage;

import java.util.Arrays;

public abstract class CommandExecutor {
    static final int USERNAME_INDEX = 0;
    static final int MESSAGE_INDEX = 1;
    static final int OTHER_USERNAME_INDEX = 1;

    static final String HYPHEN_SEPARATOR = " - ";
    static final String NOTHING_FOR_THIS_COMMAND_EXECUTION = "";

    private static final String MESSAGE_PATTERN_READ_POST = "%s %s";

    String[] tokens;
    CentralSystemClock centralSystemClock;
    MessageStore messageStore;
    FollowsList followsList;

    public abstract String execute();

    public void setParsedTokens(String[] tokens) {
        this.tokens = Arrays.copyOf(tokens, tokens.length);
    }

    public void setCentralSystemClock(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public void setMessageStore(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    public void setFollowsList(FollowsList followsList) {
        this.followsList = followsList;
    }

    String getFormattedMessage(TimeLineMessage timeLineMessage) {
        ClockTimeFormatter clockTimeFormatter = new ClockTimeFormatter(centralSystemClock);

        return String.format(
                MESSAGE_PATTERN_READ_POST,
                timeLineMessage.getMessage(),
                clockTimeFormatter.whenMessageWasPosted(timeLineMessage.getDateTime()));
    }
}