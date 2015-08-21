package engine.commands;

import clock.CentralSystemClock;
import clock.ClockTimeFormatter;
import elements.FollowsList;
import elements.MessageStore;
import elements.MessagePosted;

import java.util.Arrays;

public abstract class CommandExecutor {
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

    String getFormattedMessage(MessagePosted messagePosted) {
        ClockTimeFormatter clockTimeFormatter = new ClockTimeFormatter(centralSystemClock);

        return String.format(
                helper.ImplHelper.MESSAGE_PATTERN_READ_POST,
                messagePosted.getMessage(),
                clockTimeFormatter.whenMessageWasPosted(messagePosted.getDateTime()));
    }
}
