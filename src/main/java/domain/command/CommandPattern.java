package domain.command;

import java.io.Serializable;

public class CommandPattern implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String pattern;

    public CommandPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return pattern;
    }
}
