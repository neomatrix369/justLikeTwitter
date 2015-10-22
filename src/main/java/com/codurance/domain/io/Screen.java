package com.codurance.domain.io;

import java.io.IOException;
import java.io.PrintStream;

public class Screen implements UserOutput {
    private final PrintStream outputStream = System.out;

    @Override
    public void display(String output) throws IOException {
        outputStream.println(output);
    }
}
