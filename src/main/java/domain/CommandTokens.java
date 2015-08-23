package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static helper.ImplHelper.START_FROM_ONE;

public class CommandTokens {
    private final Map<String, String> tokens = new HashMap<>();

    public CommandTokens(String commandAsString, CommandType commandType) {
        String[] fieldNames = commandType.getFieldNames();
        String matchingPattern = commandType.getMatchingPattern();
        mapFieldToValueIn(commandAsString, matchingPattern, fieldNames);
    }

    private void mapFieldToValueIn(String commandAsString, String matchingPattern, String[] fieldNames) {
        Matcher matcher = prepareMatcherWith(commandAsString, matchingPattern);

        if (matcher.matches()) {
            populateTokens(fieldNames, matcher);
        }
    }

    private void populateTokens(String[] fieldNames, Matcher matcher) {
        int index = START_FROM_ONE;
        for (String fieldName : fieldNames) {
            tokens.put(fieldName, matcher.group(index));
            index++;
        }
    }

    private Matcher prepareMatcherWith(String commandAsString, String matchingPattern) {
        Pattern pattern = Pattern.compile(matchingPattern);
        return pattern.matcher(commandAsString);
    }

    public String get(String fieldName) {
        return tokens.get(fieldName);
    }
}
