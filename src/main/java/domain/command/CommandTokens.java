package domain.command;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static helper.ImplHelper.START_FROM_ONE;

public class CommandTokens {
    private final Map<String, String> tokens = new HashMap<>();

    public CommandTokens(String commandAsString, CommandPattern commandPattern, Fields fields) {
        Matcher matcher = prepareMatcherWith(commandAsString, commandPattern);

        if (matcher.matches()) {
            populateTokens(fields, matcher);
        }
    }

    private Matcher prepareMatcherWith(String commandAsString, CommandPattern matchingPattern) {
        Pattern pattern = Pattern.compile(matchingPattern.toString());
        return pattern.matcher(commandAsString);
    }

    private void populateTokens(Fields fields, Matcher matcher) {
        int index = START_FROM_ONE;
        for (String fieldName : fields.toList()) {
            tokens.put(fieldName, matcher.group(index));
            index++;
        }
    }

    public String get(String fieldName) {
        return tokens.get(fieldName);
    }
}