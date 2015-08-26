package com.codurance.command;

import com.codurance.functionality.command.CommandExecutor;
import com.codurance.functionality.command.DisplayWallCommand;
import com.codurance.functionality.command.FollowsUserCommand;
import com.codurance.functionality.command.PostMessageCommand;
import com.codurance.functionality.command.ReadPostCommand;

import static com.codurance.command.Fields.FOLLOWS_USER_FIELD;
import static com.codurance.command.Fields.MESSAGE_TEXT_FIELD;
import static com.codurance.command.Fields.USER_FIELD;

public enum CommandType {

    POST_MESSAGE(Constants.POST_COMMAND_PATTERN,
            new PostMessageCommand(Constants.POST_COMMAND_PATTERN, new Fields(USER_FIELD, MESSAGE_TEXT_FIELD))),

    READ_POST   (Constants.READ_POST_COMMAND_PATTERN,
            new ReadPostCommand(Constants.READ_POST_COMMAND_PATTERN, new Fields(USER_FIELD))),

    FOLLOWS_USER(Constants.FOLLOW_USER_COMMAND_PATTERN,
            new FollowsUserCommand(Constants.FOLLOW_USER_COMMAND_PATTERN, new Fields(USER_FIELD, FOLLOWS_USER_FIELD))),

    DISPLAY_WALL(Constants.DISPLAY_WALL_COMMAND_PATTERN,
            new DisplayWallCommand(Constants.DISPLAY_WALL_COMMAND_PATTERN, new Fields(USER_FIELD)));

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