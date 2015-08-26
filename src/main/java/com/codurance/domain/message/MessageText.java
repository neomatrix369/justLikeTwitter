package com.codurance.domain.message;

public class MessageText {
    private final String message;

    public MessageText(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

