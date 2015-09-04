package com.codurance.helper;

import com.codurance.domain.UserOutput;

import java.io.IOException;
import java.io.OutputStream;

public class FileAsOutputStream implements UserOutput {
    private final OutputStream outputStream;

    public FileAsOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void display(String output) throws IOException {
        outputStream.write(output.getBytes());
    }
}