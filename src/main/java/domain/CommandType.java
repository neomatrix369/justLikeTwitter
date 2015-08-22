package domain;

import java.util.Arrays;

public enum CommandType {
    POST_MESSAGE ("(\\w+) -> (.*)"        , new String[]{"User", "MessageText"}),
    READ_POST    ("(\\w+)"                , new String[]{"User"}),
    FOLLOWS_USER ("(\\w+) follows (\\w+)" , new String[]{"User", "OtherUser"}),
    DISPLAY_WALL ("(\\w+) wall"           , new String[]{"User"});

    private final String matchingPattern;
    private final String[] fieldNames;

    CommandType(String matchingPattern, String[] fieldNames) {
        this.matchingPattern = matchingPattern;
        this.fieldNames = fieldNames;
    }

    public String getMatchingPattern() {
        return matchingPattern;
    }

    public String[] getFieldNames() {
        return Arrays.copyOf(fieldNames, fieldNames.length);
    }
}