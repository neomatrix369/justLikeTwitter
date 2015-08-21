package elements;

import engine.commands.CommandExecutor;
import engine.commands.DisplayWallCommand;
import engine.commands.FollowUserCommand;
import engine.commands.PostMessageCommand;
import engine.commands.ReadPostCommand;

import java.util.HashMap;
import java.util.Map;

import static elements.CommandType.DISPLAY_WALL;
import static elements.CommandType.FOLLOWS_USER;
import static elements.CommandType.POST_MESSAGE;
import static elements.CommandType.READ_POST;

public final class PatternCommandTypeMapper {
    private Map<CommandType, CommandExecutor> patternCommandMap;

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