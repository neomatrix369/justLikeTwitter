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
        patternCommandMap.put(POST_MESSAGE, new PostMessageCommand());
        patternCommandMap.put(READ_POST, new ReadPostCommand());
        patternCommandMap.put(FOLLOWS_USER, new FollowUserCommand());
        patternCommandMap.put(DISPLAY_WALL, new DisplayWallCommand());
    }

    public static Map<CommandType, CommandExecutor> get() {
        return new PatternCommandTypeMapper().patternCommandMap;
    }
}