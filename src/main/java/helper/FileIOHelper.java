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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static helper.ImplHelper.COLUMN_SEPARATOR;
import static helper.ImplHelper.DATE_COL_INDEX;
import static helper.ImplHelper.MESSAGE_COL_INDEX;
import static helper.ImplHelper.convertToDateFrom;

public class FileIOHelper {

    private FileIOHelper() {
    }

    public static List<Date> loadDatesFrom(Class<?> aClass, String datesForInputFile) throws ParseException {
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

        StringBuilder inputString = new StringBuilder();
        for (String eachLine: lines) {
            String[] splitEachLine = eachLine.split(COLUMN_SEPARATOR);
            inputString
                    .append(splitEachLine[MESSAGE_COL_INDEX])
                    .append(System.lineSeparator());
        }

        return new ByteArrayInputStream(inputString.toString().getBytes());
    }

    public static FileOutputStream getFileToWriteTo(String fileName) throws FileNotFoundException {
        return new FileOutputStream(fileName);
    }
}
