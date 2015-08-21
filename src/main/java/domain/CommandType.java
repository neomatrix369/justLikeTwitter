package domain;

public enum CommandType {
    POST_MESSAGE ("(\\w+) -> (.*)"        , " -> "     ),
    READ_POST    ("(\\w+)"                , " "        ),
    FOLLOWS_USER ("(\\w+) follows (\\w+)" , " follows "),
    DISPLAY_WALL ("(\\w+) wall"           , " wall"    );

    private final String matchingPattern;
    private final String tokenSeparator;

    CommandType(String matchingPattern, String tokenSeparator) {
        this.matchingPattern = matchingPattern;
        this.tokenSeparator = tokenSeparator;
    }

    public String getMatchingPattern() {
        return matchingPattern;
    }

    public String getTokenSeparator() {
        return tokenSeparator;
    }
}