package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TypedCommand {
    private String commandAsString;

    public TypedCommand(String commandAsString) {
        this.commandAsString = commandAsString;
    }

    public TypedCommand(User userHarry) {
        commandAsString = userHarry.toString();
    }

    public boolean matches(String pattern) {
        return commandAsString.matches(pattern);
    }

    public String[] split(String tokenSeparator) {
        return commandAsString.split(tokenSeparator);
    }

    public byte[] getBytes() {
        return commandAsString.getBytes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TypedCommand that = (TypedCommand) o;

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
