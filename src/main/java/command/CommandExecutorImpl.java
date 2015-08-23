package command;

import clock.CentralSystemClock;
import clock.ClockTimeFormatter;
import domain.CommandTokens;
import domain.CommandType;
import domain.FollowsList;
import domain.MessageDate;
import domain.MessagePosted;
import domain.MessageStore;
import domain.MessageText;
import domain.User;
import domain.UserTypedCommand;

import static helper.ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;

public class CommandExecutorImpl implements CommandExecutor {
    CentralSystemClock centralSystemClock;

    MessageStore messageStore;
    FollowsList followsList;
    CommandTokens commandTokens;

    private final CommandType commandType;

    CommandExecutorImpl(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        commandTokens = userTypedCommand.parseUsing(commandType);
        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    @Override
    public void setCentralSystemClock(CentralSystemClock centralSystemClock) {
        this.centralSystemClock = centralSystemClock;
    }

    @Override
    public void setMessageStore(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    @Override
    public void setFollowsList(FollowsList followsList) {
        this.followsList = followsList;
    }

    String getFormattedMessage(MessagePosted messagePosted) {
        ClockTimeFormatter clockTimeFormatter = new ClockTimeFormatter(centralSystemClock);

        MessageDate messageDate = messagePosted.getMessageDate();
        return String.format(
                helper.ImplHelper.MESSAGE_PATTERN_READ_POST,
                messagePosted.getMessageText(),
                clockTimeFormatter.whenMessageWasPosted(messageDate.toDate()));
    }

    User createNewUserFrom(CommandTokens commandTokens, String fieldName) {
        return new User(commandTokens.get(fieldName));
    }

    MessageText createNewMessageTextFrom(CommandTokens commandTokens, String fieldName) {
        return new MessageText(commandTokens.get(fieldName));
    }
}