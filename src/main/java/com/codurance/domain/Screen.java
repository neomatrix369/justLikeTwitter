package com.codurance.domain;

import java.io.IOException;
import java.io.OutputStream;

public class Screen implements UserOutput {
    private final OutputStream outputStream;

    public Screen(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void display(String output) throws IOException {
        outputStream.write(output.getBytes());
    }
}
