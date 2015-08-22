package domain;

public enum CommandType {
    POST_MESSAGE ("(\\w+) -> (.*)"        , " -> "     , new String[]{"User", "MessageText"}),
    READ_POST    ("(\\w+)"                , " "        , new String[]{"User"}),
    FOLLOWS_USER ("(\\w+) follows (\\w+)" , " follows ", new String[]{"User", "OtherUser"}),
    DISPLAY_WALL ("(\\w+) wall"           , " wall"    , new String[]{"User"});

    private final String matchingPattern;
    private final String tokenSeparator;
    private final String[] fieldNames;

    CommandType(String matchingPattern, String tokenSeparator, String[] fieldNames) {
        this.matchingPattern = matchingPattern;
        this.tokenSeparator = tokenSeparator;
        this.fieldNames = fieldNames;
    }

    public String getMatchingPattern() {
        return matchingPattern;
    }

    public String getTokenSeparator() {
        return tokenSeparator;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }
}