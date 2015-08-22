package helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static helper.ImplHelper.COLUMN_SEPARATOR;
import static helper.ImplHelper.DATE_COL_INDEX;
import static helper.ImplHelper.DD_MM_YYYY_HH_MM_SS;
import static helper.ImplHelper.MESSAGE_COL_INDEX;

public class FileIOHelper {

    private FileIOHelper() {
    }

    public static List<Date> loadDatesFrom(Class<?> aClass, String datesForInputFile) {
        List<Date> result = new ArrayList<>();
        try {
            List<String> lines = getTheContentOf(getPathFor(aClass, datesForInputFile).toString());
            for (String eachLine: lines) {
                String[] eachLineSplit = eachLine.split(COLUMN_SEPARATOR);
                String eachDate = eachLineSplit[DATE_COL_INDEX];
                result.add(convertToDateFrom(eachDate));
            }
        } catch (IOException e) {
            System.err.println("Error loading input file with dates: " + e.getMessage());
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

    public static InputStream getFileToReadFrom(Class<?> aClass, String fileName) throws IOException {
        List<String> lines = getTheContentOf(getPathFor(aClass, fileName).toString());

        String inputString = "";
        for (String eachLine: lines) {
            String[] splitEachLine = eachLine.split(COLUMN_SEPARATOR);
            inputString += splitEachLine[MESSAGE_COL_INDEX] + System.lineSeparator();
        }

        return new ByteArrayInputStream(inputString.getBytes());
    }

    public static FileOutputStream getFileToWriteTo(String fileName) throws FileNotFoundException {
        return new FileOutputStream(fileName);
    }

    private static Date convertToDateFrom(String dateAsString) {
        DateFormat format = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS, Locale.ENGLISH);
        try {
            return format.parse(dateAsString);
        } catch (ParseException e) {
            System.err.println("Error occurred while parsing a date: " + e.getMessage());
            System.err.println("Error cause by: " + dateAsString);
        }
        return null;
    }
}
