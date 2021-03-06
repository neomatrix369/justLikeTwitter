package com.codurance.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codurance.helper.ImplHelper.convertToDateFrom;

public class FileIOHelper {

    private static final String COLUMN_SEPARATOR = ",";
    private static final int DATE_COL_INDEX = 0;

    private FileIOHelper() {
    }

    public static List<Date> loadDatesFrom(Class<?> aClass, String datesForInputFile) throws ParseException, IOException {
        List<Date> result = new ArrayList<>();
        List<String> lines = getTheContentOf(getPathFor(aClass, datesForInputFile).toString());
        for (String eachLine: lines) {
            String[] eachLineSplit = eachLine.split(COLUMN_SEPARATOR);
            String eachDate = eachLineSplit[DATE_COL_INDEX];
            result.add(convertToDateFrom(eachDate));
        }
        return result;
    }

    public static String convertListToStringWithLinefeed(List<String> fileContent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String eachLine: fileContent) {
            stringBuilder
                    .append(eachLine)
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    public static List<String> getTheContentOf(String fileName) throws IOException {
        File file = new File(fileName);
        Path filePath = file.toPath();
        return Files.readAllLines(filePath, Charset.defaultCharset());
    }

    public static Path getPathFor(Class<?> aClass, String inputFileName) {
        URL fileUrl = aClass.getResource(inputFileName);
        return Paths.get(fileUrl.getPath());
    }

    public static int getNumberOfCommandsIn(Class<?> aClass, String inputFileName) throws IOException {
        Path filePath = getPathFor(aClass, inputFileName);
        List<String> lines = Files.readAllLines(filePath, Charset.defaultCharset());
        return lines.size();
    }

    public static OutputStream getFileToWriteTo(String fileName) throws FileNotFoundException {
        return new FileOutputStream(fileName);
    }
}
