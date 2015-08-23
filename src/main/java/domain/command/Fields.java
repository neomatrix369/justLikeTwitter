package domain.command;

import java.util.Arrays;

public class Fields {
    private String[] fieldNames;

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
