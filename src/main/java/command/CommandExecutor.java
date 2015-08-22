package command;

import clock.CentralSystemClock;
import clock.ClockTimeFormatter;
import domain.CommandTokens;
import domain.CommandType;
import domain.FollowsList;
import domain.MessagePosted;
import domain.MessageStore;
import domain.MessageText;
import domain.UserTypedCommand;
import domain.User;

public abstract class CommandExecutor {
    CentralSystemClock centralSystemClock;

    MessageStore messageStore;
    FollowsList followsList;

    CommandType commandType;
    CommandTokens commandTokens;

    public CommandExecutor(CommandType commandType) {
        this.commandType = commandType;
    }

    public abstract String execute();

    public void setCentralSystemClock(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    public void setMessageStore(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    public void setFollowsList(FollowsList followsList) {
        this.followsList = followsList;
    }

    public void parseUserTypedCommand(UserTypedCommand userTypedCommand) {
        this.commandTokens = userTypedCommand.parseUsing(commandType);
    }

    String getFormattedMessage(MessagePosted messagePosted) {
        ClockTimeFormatter clockTimeFormatter = new ClockTimeFormatter(centralSystemClock);

        return String.format(
                helper.ImplHelper.MESSAGE_PATTERN_READ_POST,
                messagePosted.getMessageText(),
                clockTimeFormatter.whenMessageWasPosted(messagePosted.getMessageDate()));
    }

    User createNewUserFrom(CommandTokens commandTokens, String fieldName) {
        return new User(commandTokens.get(fieldName));
    }

    MessageText createNewMessageTextFrom(CommandTokens commandTokens, String fieldName) {
        return new MessageText(commandTokens.get(fieldName));
    }
}