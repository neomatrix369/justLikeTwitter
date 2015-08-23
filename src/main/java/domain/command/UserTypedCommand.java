package domain.command;

import domain.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;

public class UserTypedCommand {
    private final String command;

    public UserTypedCommand(String command) {
        this.command = command;
    }

    public UserTypedCommand(User user) {
        command = user.toString();
    }

    public boolean matches(String pattern) {
        return command.matches(pattern);
    }

    public CommandTokens parseUsing(CommandPattern commandPattern, Fields fields) {
        return new CommandTokens(command, commandPattern, fields);
    }

    public byte[] getBytes() {
        byte[] bytes = command.getBytes();
        return Arrays.copyOf(bytes, bytes.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserTypedCommand that = (UserTypedCommand) o;

        return new EqualsBuilder()
                .append(command, that.command)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(command)
                .toHashCode();
    }
}
