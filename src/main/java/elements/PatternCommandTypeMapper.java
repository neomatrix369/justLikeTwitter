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
    private final Map<CommandType, CommandExecutor> patternCommandMap =
            new HashMap<CommandType, CommandExecutor>() {
                {
                    put(POST_MESSAGE, new PostMessageCommand() );
                    put(READ_POST, new ReadPostCommand() );
                    put(FOLLOWS_USER, new FollowUserCommand() );
                    put(DISPLAY_WALL, new DisplayWallCommand());
                }
            };

    public static Map<CommandType, CommandExecutor> get() {
        return new PatternCommandTypeMapper().patternCommandMap;
    }
}
