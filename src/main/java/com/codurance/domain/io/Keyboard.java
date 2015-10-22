package com.codurance.domain.io;

import java.io.InputStream;
import java.util.Scanner;

public class Keyboard implements UserInput {

    private static final String STRING_ENCODING = "UTF-8";

    private final InputStream inputStream = System.in;

    @Override
    public Scanner getScanner() {
        return new Scanner(inputStream, STRING_ENCODING);
    }
}
