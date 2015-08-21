package helper;

import engine.commands.CommandExecutor;

import java.util.List;

public final class ImplHelper {
    public static final int THOUSAND_MILLISECONDS = 1000;
    public static final int SIXTY_SECONDS = 60;

    public static final String TIME_IN_WORDS_PATTERN = "(%d %s ago)";

    public static final String MINUTE_TOKEN = "minute";
    public static final String SECOND_TOKEN = "second";
    public static final String DEFAULT_TOKEN = "(just now)";
    public static final String SUFFIX_S = "s";

    public static final String MESSAGE_ON_TIMELINE_PATTERN = "%s %s";
    public static final String MESSAGE_PATTERN_READ_POST = "%s %s";

    public static final int USERNAME_INDEX = 0;
    public static final int MESSAGE_INDEX = 1;
    public static final int OTHER_USERNAME_INDEX = 1;

    public static final String HYPHEN_SEPARATOR = " - ";
    public static final String NOTHING_FOR_THIS_COMMAND_EXECUTION = "";

    public static final CommandExecutor NO_COMMAND_EXECUTOR_MATCHED = null;

    public static final String COMMAND_PROMPT_INDICATOR = "> ";
    public static final String NOTHING = "";

    public static final int START_FROM_ONE = 1;
    public static final int FOREVER = -1;
    public static final boolean EXTRA_LINEFEED_NOT_NEEDED = false;

    public static final List<String> FIRST_TIME = null;

    public static final String UTF_8_STRING = "UTF-8";

    private ImplHelper() {}

    public static String makePlural(long duration, String timeUnit) {
        if (duration > 1) {
            return timeUnit + SUFFIX_S;
        }
        return timeUnit;
    }
}
