package elements;

public enum CommandType {
    POST_MESSAGE (" -> "),
    FOLLOWS_USER (" follows "),
    DISPLAY_WALL(" wall");

    private final String token;

    CommandType(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}