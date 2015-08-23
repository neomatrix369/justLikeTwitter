package command;

import clock.CentralSystemClock;
import domain.FollowsList;
import domain.MessageStore;
import domain.command.UserTypedCommand;

public interface CommandExecutor {
    String execute(UserTypedCommand userTypedCommand);

    void setMessageStore(MessageStore messageStore);

    void setCentralSystemClock(CentralSystemClock centralSystemClock);

    void setFollowsList(FollowsList followsList);
}