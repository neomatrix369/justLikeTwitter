package functionality.command;

import clock.CentralSystemClock;
import domain.FollowsList;
import domain.User;
import domain.command.CommandPattern;
import domain.command.CommandTokens;
import domain.command.Fields;
import domain.command.UserTypedCommand;
import domain.message.MessageStore;
import domain.message.MessageText;

import static helper.ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;

public class CommandExecutorImpl implements CommandExecutor {

    private final CommandPattern commandPattern;
    private final Fields fields;

    CentralSystemClock centralSystemClock;

    MessageStore messageStore;
    FollowsList followsList;
    CommandTokens commandTokens;

    CommandExecutorImpl(CommandPattern commandPattern, Fields fields) {
        this.commandPattern = commandPattern;
        this.fields = fields;
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        commandTokens = userTypedCommand.parseUsing(commandPattern, fields);
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

    @Override
    public Fields getFields() {
        return fields;
    }

    User createNewUserFrom(CommandTokens commandTokens, String fieldName) {
        return new User(commandTokens.getValueFor(fieldName));
    }

    MessageText createNewMessageTextFrom(CommandTokens commandTokens, String fieldName) {
        return new MessageText(commandTokens.getValueFor(fieldName));
    }
}