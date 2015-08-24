package functionality;

import clock.CentralSystemClock;
import domain.FollowsList;
import domain.command.UserTypedCommand;
import domain.message.MessageStore;
import functionality.command.CommandExecutor;
import functionality.command.CommandExecutorSelector;

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
        CommandExecutorSelector commandExecutorSelector = new CommandExecutorSelector();

        CommandExecutor commandExecutor = commandExecutorSelector.getCommandExecutorFor(userTypedCommand);
        commandExecutor.setMessageStore(messageStore);
        commandExecutor.setCentralSystemClock(centralSystemClock);
        commandExecutor.setFollowsList(followsList);

        return commandExecutor.execute(userTypedCommand);
    }
}