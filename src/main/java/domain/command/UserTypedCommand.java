package domain.command;

import domain.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;

public class UserTypedCommand {
    private final String commandAsString;

    public UserTypedCommand(String commandAsString) {
        this.commandAsString = commandAsString;
    }

    public UserTypedCommand(User user) {
        commandAsString = user.toString();
    }

    public boolean matches(String pattern) {
        return commandAsString.matches(pattern);
    }

    public CommandTokens parseUsing(CommandPattern commandPattern, Fields fields) {
        return new CommandTokens(commandAsString, commandPattern, fields);
    }

    public byte[] getBytes() {
        byte[] bytes = commandAsString.getBytes();
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
                .append(commandAsString, that.commandAsString)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(commandAsString)
                .toHashCode();
    }
}
