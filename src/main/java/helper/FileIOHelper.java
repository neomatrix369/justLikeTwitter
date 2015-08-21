package helper;

import java.io.File;
import java.io.FileInputStream;
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

import static helper.ImplHelper.DD_MM_YYYY_HH_MM_SS;

public class FileIOHelper {
    public static List<Date> loadDatesFrom(Class<? extends Object> aClass, String datesForInputFile) {
        List<Date> result = new ArrayList<>();
        try {
            List<String> listOfDates = getTheContentOf(getPathFor(aClass, datesForInputFile).toString());
            for (String eachDate: listOfDates) {
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

    public static Path getPathFor(Class<? extends Object> aClass, String inputFileName) {
        URL fileUrl = aClass.getResource(inputFileName);
        return Paths.get(fileUrl.getPath());
    }

    public static int getNumberOfCommandsIn(Class<? extends Object> aClass, String inputFileName) throws IOException {
        Path filePath = getPathFor(aClass, inputFileName);
        List<String> lines = Files.readAllLines(filePath, Charset.defaultCharset());
        return lines.size();
    }

    public static InputStream getFileToReadFrom(Class<? extends Object> aClass, String fileName) throws FileNotFoundException {
        Path filePath = getPathFor(aClass, fileName);
        return new FileInputStream(filePath.toFile());
    }

    public static FileOutputStream getFileToWriteTo(String fileName) throws FileNotFoundException {
        return new FileOutputStream(fileName);
    }

    private static Date convertToDateFrom(String dateAsString) {
        DateFormat format = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS, Locale.ENGLISH);
        try {
            return format.parse(dateAsString);
        } catch (ParseException e) {
            System.err.println("Error occurred while parsing: " + e.getMessage());
        }
        return null;
    }
}
