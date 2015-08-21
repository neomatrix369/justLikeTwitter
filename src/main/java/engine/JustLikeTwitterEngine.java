package engine;

import clock.CentralSystemClock;
import command.CommandExecutorFactory;
import domain.FollowsList;
import domain.MessageStore;
import command.CommandExecutor;
import domain.TypedCommand;

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

    public String executeCommand(TypedCommand userTypedCommand) {
        CommandExecutorFactory commandExecutorFactory =
                new CommandExecutorFactory(centralSystemClock, messageStore, followsList);
        CommandExecutor command = commandExecutorFactory.getCommand(userTypedCommand);
        return command.execute();
    }
}