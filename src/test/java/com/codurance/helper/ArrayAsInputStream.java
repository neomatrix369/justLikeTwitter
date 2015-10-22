package com.codurance.helper;

import com.codurance.command.UserTypedCommand;
import com.codurance.domain.io.UserInput;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ArrayAsInputStream implements UserInput {
    private final BufferedInputStream bufferedInputStream;
    private final Scanner scanner;

    public ArrayAsInputStream(UserTypedCommand userTypedCommand) {
        ByteArrayInputStream inputInputStreamContent = new ByteArrayInputStream(userTypedCommand.getBytes());
        bufferedInputStream = new BufferedInputStream(inputInputStreamContent);
        scanner = new Scanner(bufferedInputStream);
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
