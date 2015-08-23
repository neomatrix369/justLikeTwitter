package domain.command;

import functionality.command.CommandExecutor;
import functionality.command.DisplayWallCommand;
import functionality.command.FollowUserCommand;
import functionality.command.PostMessageCommand;
import functionality.command.ReadPostCommand;

import java.util.HashMap;
import java.util.Map;

import static domain.command.CommandType.DISPLAY_WALL;
import static domain.command.CommandType.FOLLOWS_USER;
import static domain.command.CommandType.POST_MESSAGE;
import static domain.command.CommandType.READ_POST;

public final class PatternCommandTypeMapper {
    private final Map<CommandType, CommandExecutor> patternCommandMap;

    private PatternCommandTypeMapper() {
        patternCommandMap = new HashMap<>();
        patternCommandMap.put(POST_MESSAGE, new PostMessageCommand(POST_MESSAGE));
        patternCommandMap.put(READ_POST, new ReadPostCommand(READ_POST));
        patternCommandMap.put(FOLLOWS_USER, new FollowUserCommand(FOLLOWS_USER));
        patternCommandMap.put(DISPLAY_WALL, new DisplayWallCommand(DISPLAY_WALL));
    }

    public static Map<CommandType, CommandExecutor> get() {
        return new PatternCommandTypeMapper().patternCommandMap;
    }
}