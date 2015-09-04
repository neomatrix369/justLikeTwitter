package com.codurance.helper;

import com.codurance.domain.io.UserInput;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static com.codurance.helper.FileIOHelper.getPathFor;
import static com.codurance.helper.FileIOHelper.getTheContentOf;
import static com.codurance.helper.ImplHelper.COLUMN_SEPARATOR;
import static com.codurance.helper.ImplHelper.MESSAGE_COL_INDEX;

public class FileAsInputStream implements UserInput {
    private final InputStream inputStream;

    public FileAsInputStream(Class<?> aClass, String inputFileName) throws IOException {
        this.inputStream = getFileToReadFrom(aClass, inputFileName);
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
    public Scanner getScanner() {
        return new Scanner(inputStream);
    }
}
