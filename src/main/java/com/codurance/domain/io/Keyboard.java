package com.codurance.domain.io;

import java.util.Scanner;

public class Keyboard implements UserInput {

    private static final String STRING_ENCODING = "UTF-8";

    private final Scanner scanner = new Scanner(System.in, STRING_ENCODING);

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
