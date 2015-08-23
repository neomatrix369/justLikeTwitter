package functionality.command;

import clock.CentralSystemClock;
import domain.FollowsList;
import domain.command.Fields;
import domain.command.UserTypedCommand;
import domain.message.MessageStore;

public interface CommandExecutor {
    String execute(UserTypedCommand userTypedCommand);

    void setMessageStore(MessageStore messageStore);

    void setCentralSystemClock(CentralSystemClock centralSystemClock);

    void setFollowsList(FollowsList followsList);

    Fields getFields();
}