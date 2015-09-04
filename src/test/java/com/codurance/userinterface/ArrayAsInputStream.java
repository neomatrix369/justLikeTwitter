package com.codurance.userinterface;

import com.codurance.command.UserTypedCommand;
import com.codurance.domain.UserInput;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ArrayAsInputStream implements UserInput {
    private final BufferedInputStream bufferedInputStream;

    public ArrayAsInputStream(UserTypedCommand userTypedCommand) {
        ByteArrayInputStream inputInputStreamContent = new ByteArrayInputStream(userTypedCommand.getBytes());
        bufferedInputStream = new BufferedInputStream(inputInputStreamContent);
    }

    @Override
    public Scanner getScanner() {
        return new Scanner(bufferedInputStream);
    }
}
