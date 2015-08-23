package domain.command;

import java.io.Serializable;
import java.util.Arrays;

public class Fields implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String[] fieldNames;

    public Fields(String... fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String[] toList() {
        return Arrays.copyOf(fieldNames, fieldNames.length);
    }

    public int getLength() {
        return fieldNames.length;
    }
}
