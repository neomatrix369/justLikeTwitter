package com.codurance.domain.io;

import java.io.InputStream;
import java.util.Scanner;

import static com.codurance.helper.ImplHelper.STRING_ENCODING;

public class Keyboard implements UserInput {
    private final InputStream inputStream = System.in;

    @Override
    public Scanner getScanner() {
        return new Scanner(inputStream, STRING_ENCODING);
    }
}
