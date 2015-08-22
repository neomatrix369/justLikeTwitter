package command;

import clock.CentralSystemClock;
import clock.ClockTimeFormatter;
import domain.CommandTokens;
import domain.CommandType;
import domain.FollowsList;
import domain.MessagePosted;
import domain.MessageStore;
import domain.MessageText;
import domain.User;
import domain.UserTypedCommand;

public class CommandExecutorImpl implements CommandExecutor {
    CentralSystemClock centralSystemClock;

    MessageStore messageStore;
    FollowsList followsList;

    CommandType commandType;
    CommandTokens commandTokens;

    public CommandExecutorImpl(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        commandTokens = userTypedCommand.parseUsing(commandType);
        return "";
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

    User createNewUserFrom(CommandTokens commandTokens, String fieldName) {
        return new User(commandTokens.get(fieldName));
    }

    MessageText createNewMessageTextFrom(CommandTokens commandTokens, String fieldName) {
        return new MessageText(commandTokens.get(fieldName));
    }
}