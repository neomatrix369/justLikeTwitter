package com.codurance.command;

import com.codurance.domain.User;
import com.codurance.domain.message.MessageText;

public class CommandLineEntryParser {

    public static User createNewUserFrom(CommandTokens commandTokens, String fieldName) {
        return new User(commandTokens.getValueFor(fieldName));
    }

    public static MessageText createNewMessageTextFrom(CommandTokens commandTokens, String fieldName) {
        return new MessageText(commandTokens.getValueFor(fieldName));
    }
}
