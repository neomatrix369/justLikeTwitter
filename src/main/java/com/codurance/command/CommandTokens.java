package com.codurance.command;

import com.codurance.helper.ImplHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandTokens {
    private final Map<String, String> tokens = new HashMap<>();

    public CommandTokens(String command, CommandPattern commandPattern, Fields fields) {
        Matcher matcher = prepareMatcherWith(command, commandPattern);

        if (matcher.matches()) {
            populateTokens(fields, matcher);
        }
    }

    private Matcher prepareMatcherWith(String command, CommandPattern matchingPattern) {
        Pattern pattern = Pattern.compile(matchingPattern.toString());
        return pattern.matcher(command);
    }

    private void populateTokens(Fields fields, Matcher matcher) {
        int index = ImplHelper.START_FROM_ONE;
        for (String fieldName : fields.toList()) {
            tokens.put(fieldName, matcher.group(index));
            index++;
        }
    }

    public String getValueFor(String fieldName) {
        return tokens.get(fieldName);
    }
}