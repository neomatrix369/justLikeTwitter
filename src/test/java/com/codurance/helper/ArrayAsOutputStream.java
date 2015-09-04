package com.codurance.helper;

import com.codurance.domain.io.UserOutput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ArrayAsOutputStream implements UserOutput {
    private final OutputStream outputStream;

    public ArrayAsOutputStream() {
        ByteArrayOutputStream outputStreamContent = new ByteArrayOutputStream();
        outputStream = new PrintStream(outputStreamContent);
    }

    @Override
    public void display(String output) throws IOException {
        outputStream.write(output.getBytes());
    }
}
