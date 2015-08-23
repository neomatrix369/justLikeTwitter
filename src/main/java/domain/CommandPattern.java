package domain;

public class CommandPattern {
    private final String pattern;

    public CommandPattern(String pattern) {
        this.pattern = pattern;
    }

    public String toString() {
        return pattern;
    }
}
