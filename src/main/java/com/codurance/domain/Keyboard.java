package com.codurance.domain;

import java.io.InputStream;
import java.util.Scanner;

import static com.codurance.helper.ImplHelper.STRING_ENCODING;

public class Keyboard {
    private final InputStream inputStream;

    public Keyboard(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Scanner getScanner() {
        return new Scanner(inputStream, STRING_ENCODING);
    }
}
