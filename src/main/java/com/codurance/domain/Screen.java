package com.codurance.domain;

import java.io.IOException;
import java.io.OutputStream;

public class Screen implements UserOutput {
    private final OutputStream outputStream = System.out;

    @Override
    public void display(String output) throws IOException {
        outputStream.write(output.getBytes());
    }
}
