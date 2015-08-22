package domain;

import command.CommandExecutor;
import command.DisplayWallCommand;
import command.FollowUserCommand;
import command.PostMessageCommand;
import command.ReadPostCommand;

import java.util.HashMap;
import java.util.Map;

import static domain.CommandType.DISPLAY_WALL;
import static domain.CommandType.FOLLOWS_USER;
import static domain.CommandType.POST_MESSAGE;
import static domain.CommandType.READ_POST;

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