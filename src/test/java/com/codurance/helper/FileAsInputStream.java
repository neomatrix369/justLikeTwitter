package com.codurance.helper;

import com.codurance.domain.io.UserInput;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static com.codurance.helper.FileIOHelper.getPathFor;
import static com.codurance.helper.FileIOHelper.getTheContentOf;

public class FileAsInputStream implements UserInput {

    private static final String COLUMN_SEPARATOR = ",";
    private static final int MESSAGE_COL_INDEX = 1;

    private final InputStream inputStream;
    private final Scanner scanner;

    public FileAsInputStream(Class<?> aClass, String inputFileName) throws IOException {
        this.inputStream = getFileToReadFrom(aClass, inputFileName);
        scanner = new Scanner(inputStream);
    }

    private InputStream getFileToReadFrom(Class<?> aClass, String fileName) throws IOException {
        List<String> lines = getTheContentOf(getPathFor(aClass, fileName).toString());

        StringBuilder inputString = new StringBuilder();
        for (String eachLine: lines) {
            String[] splitEachLine = eachLine.split(COLUMN_SEPARATOR);
            inputString
                    .append(splitEachLine[MESSAGE_COL_INDEX])
                    .append(System.lineSeparator());
        }

        return new ByteArrayInputStream(inputString.toString().getBytes());
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