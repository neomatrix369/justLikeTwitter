package domain.command;

public class CommandPattern {

    private final String pattern;

    public CommandPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return pattern;
    }
}
