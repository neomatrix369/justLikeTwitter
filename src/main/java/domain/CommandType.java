package domain;

public enum CommandType {
    POST_MESSAGE(new CommandPattern("(\\w+) -> (.*)")        , new Fields("User", "MessageText")),
    READ_POST   (new CommandPattern("(\\w+)")                , new Fields("User")),
    FOLLOWS_USER(new CommandPattern("(\\w+) follows (\\w+)") , new Fields("User", "OtherUser")),
    DISPLAY_WALL(new CommandPattern("(\\w+) wall")           , new Fields("User"));

    private final CommandPattern matchingPattern;
    private final Fields fields;

    CommandType(CommandPattern matchingPattern, Fields fields) {
        this.matchingPattern = matchingPattern;
        this.fields = fields;
    }

    public CommandPattern getMatchingPattern() {
        return matchingPattern;
    }

    public Fields getFields() {
        return fields;
    }
}