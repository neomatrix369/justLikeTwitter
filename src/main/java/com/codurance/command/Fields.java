package com.codurance.command;

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
}
