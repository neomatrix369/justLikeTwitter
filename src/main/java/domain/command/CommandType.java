package domain.command;

import functionality.command.CommandExecutor;
import functionality.command.DisplayWallCommand;
import functionality.command.FollowUserCommand;
import functionality.command.PostMessageCommand;
import functionality.command.ReadPostCommand;

public enum CommandType {

    POST_MESSAGE(Constants.POST_COMMAND_PATTERN,
            new PostMessageCommand(Constants.POST_COMMAND_PATTERN, new Fields("User", "MessageText"))),

    READ_POST   (Constants.READ_POST_COMMAND_PATTERN,
            new ReadPostCommand(Constants.READ_POST_COMMAND_PATTERN, new Fields("User"))),

    FOLLOWS_USER(Constants.FOLLOW_USER_COMMAND_PATTERN,
            new FollowUserCommand(Constants.FOLLOW_USER_COMMAND_PATTERN, new Fields("User", "OtherUser"))),

    DISPLAY_WALL(Constants.DISPLAY_WALL_COMMAND_PATTERN,
            new DisplayWallCommand(Constants.DISPLAY_WALL_COMMAND_PATTERN, new Fields("User")));

    private final CommandPattern matchingPattern;
    private final CommandExecutor commandExecutor;

    CommandType(CommandPattern matchingPattern, CommandExecutor commandExecutor) {
        this.matchingPattern = matchingPattern;
        this.commandExecutor = commandExecutor;
    }

    public CommandPattern getMatchingPattern() {
        return matchingPattern;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    private static class Constants {
        static final CommandPattern POST_COMMAND_PATTERN = new CommandPattern("(\\w+) -> (.*)");
        static final CommandPattern READ_POST_COMMAND_PATTERN = new CommandPattern("(\\w+)");
        static final CommandPattern FOLLOW_USER_COMMAND_PATTERN = new CommandPattern("(\\w+) follows (\\w+)");
        static final CommandPattern DISPLAY_WALL_COMMAND_PATTERN = new CommandPattern("(\\w+) wall");

        private Constants() {
        }
    }
}