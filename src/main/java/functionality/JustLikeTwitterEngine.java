package functionality;

import functionality.command.CommandExecutor;
import functionality.command.CommandExecutorFactory;
import clock.CentralSystemClock;
import domain.FollowsList;
import domain.message.MessageStore;
import domain.command.UserTypedCommand;

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

    public String executeCommand(UserTypedCommand userTypedCommand) {
        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory();
        CommandExecutor commandExecutor = commandExecutorFactory.getCommandUsing(userTypedCommand);

        commandExecutor.setMessageStore(messageStore);
        commandExecutor.setCentralSystemClock(centralSystemClock);
        commandExecutor.setFollowsList(followsList);

        return commandExecutor.execute(userTypedCommand);
    }
}