package com.codurance.command;

import com.codurance.domain.User;
import com.codurance.domain.message.MessageText;

import java.util.Arrays;

public class Fields {

    public static final String USER_FIELD = "User";
    public static final String MESSAGE_TEXT_FIELD = "MessageText";
    public static final String FOLLOWS_USER_FIELD = "FollowsUser";

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

    public static User createNewUserFrom(CommandTokens commandTokens, String fieldName) {
        return new User(commandTokens.getValueFor(fieldName));
    }

    public static MessageText createNewMessageTextFrom(CommandTokens commandTokens, String fieldName) {
        return new MessageText(commandTokens.getValueFor(fieldName));
    }
}
