package engine;

import clock.CentralSystemClock;
import elements.FollowsList;
import elements.MessageStore;

public class JustLikeTwitterEngine {
    private final MessageStore messageStore;
    private final FollowsList followsList;
    private final CentralSystemClock centralSystemClock;

    public JustLikeTwitterEngine(MessageStore messageStore,
                                 FollowsList followsList,
                                 CentralSystemClock centralSystemClock) {
        this.messageStore = messageStore;
        this.followsList = followsList;
        this.centralSystemClock = centralSystemClock;
    }

    public String executeCommand(String userTypedCommand) {
        CommandExecutorFactory commandExecutorFactory =
                new CommandExecutorFactory(centralSystemClock, messageStore, followsList);
        CommandExecutor command = commandExecutorFactory.getCommand(userTypedCommand);
        return command.execute();
    }
}