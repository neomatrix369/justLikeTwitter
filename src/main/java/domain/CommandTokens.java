package domain;

import java.util.HashMap;
import java.util.Map;

public class CommandTokens {
    private Map<String, String> tokens = new HashMap<>();

    public CommandTokens(String commandAsString, String tokenSeparator, String[] fieldNames) {
        String[] splitCommands = commandAsString.split(tokenSeparator);

        int index = 0;
        for (String fieldName: fieldNames) {
            tokens.put(fieldName, splitCommands[index]);
            index++;
        }
    }

    public String get(String fieldName) {
        return tokens.get(fieldName);
    }
}
