package command;

import clock.CentralSystemClock;
import clock.ClockTimeFormatter;
import domain.CommandTokens;
import domain.FollowsList;
import domain.MessagePosted;
import domain.MessageStore;
import domain.MessageText;
import domain.User;

public abstract class CommandExecutor {
    CommandTokens commandTokens;
    CentralSystemClock centralSystemClock;
    MessageStore messageStore;
    FollowsList followsList;

    public abstract String execute();

    public void setCommandTokens(CommandTokens commandTokens) {
        this.commandTokens = commandTokens;
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
                messagePosted.getMessageText(),
                clockTimeFormatter.whenMessageWasPosted(messagePosted.getMessageDate()));
    }

    User createNewUserInstanceFrom(CommandTokens commandTokens, String fieldName) {
        return new User(commandTokens.get(fieldName));
    }

    MessageText createNewMessageTextInstanceFrom(CommandTokens commandTokens, String fieldName) {
        return new MessageText(commandTokens.get(fieldName));
    }
}
