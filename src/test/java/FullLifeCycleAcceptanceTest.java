import elements.MessageStore;
import engine.JustLikeTwitterEngine;
import interfaces.IOConsole;
import interfaces.JustLikeTwitter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import clock.CentralSystemClock;

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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static helper.TestHelper.EXTRA_LINEFEED_NEEDED;
import static helper.TestHelper.REPLAY_INPUT_FILE;
import static helper.TestHelper.ACTUAL_OUTPUT_FILE;
import static helper.TestHelper.APPROVER;
import static helper.TestHelper.DATES_FOR_MESSAGES_INPUT_FILE;
import static helper.TestHelper.DD_MM_YYYY_HH_MM_SS;
import static helper.TestHelper.EXPECTED_OUTPUT_FILE;

public class FullLifeCycleAcceptanceTest {

    private JustLikeTwitter justLikeTwitter;
    private CentralSystemClock centralSystemClockMock = mock(CentralSystemClock.class);
    private List<Date> dateTimeForMessages = loadDatesFrom(DATES_FOR_MESSAGES_INPUT_FILE);

    @Before
    public void setUp() throws FileNotFoundException {
        MessageStore messageStore = new MessageStore();
        JustLikeTwitterEngine justLikeTwitterEngine = new JustLikeTwitterEngine(messageStore, centralSystemClockMock);
        IOConsole ioConsole = new IOConsole(
                getFileToReadFrom(REPLAY_INPUT_FILE),
                getFileToWriteTo(ACTUAL_OUTPUT_FILE),
                EXTRA_LINEFEED_NEEDED);
        justLikeTwitter = new JustLikeTwitter(ioConsole, justLikeTwitterEngine);
    }

    @Test
    public void givenJustLikeTwitter_whenASeriesOfCommandsArePassedIn_thenATimelineIsGenerated() throws IOException {
        // given
        when(centralSystemClockMock.getCurrentDateTime())
                .thenAnswer(AdditionalAnswers.returnsElementsOf(dateTimeForMessages));

        // when
        int numberOfCommands = getNumberOfCommandsIn(REPLAY_INPUT_FILE);
        justLikeTwitter.run(numberOfCommands);

        // then
        String actualOutputFileContent = convertListToStringWithLinefeed(getTheContentOf(ACTUAL_OUTPUT_FILE));
        APPROVER.verify(actualOutputFileContent, getPathFor(EXPECTED_OUTPUT_FILE));
    }

    private List<Date> loadDatesFrom(String datesForInputFile) {
        List<Date> result = new ArrayList<>();
        try {
            List<String> listOfDates = getTheContentOf(getPathFor(datesForInputFile).toString());
            for (String eachDate: listOfDates) {
                result.add(convertToDateFrom(eachDate));
            }
        } catch (IOException e) {
            System.err.println("Error loading input file with dates: " + e.getMessage());
        }

        return result;
    }

    private Date convertToDateFrom(String dateAsString) {
        DateFormat format = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS, Locale.ENGLISH);
        try {
            return format.parse(dateAsString);
        } catch (ParseException e) {
            System.err.println("Error occurred while parsing: " + e.getMessage());
        }
        return null;
    }

    private String convertListToStringWithLinefeed(List<String> fileContent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String eachLine: fileContent) {
            stringBuilder
                    .append(eachLine)
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private List<String> getTheContentOf(String fileName) throws IOException {
        File file = new File(fileName);
        Path filePath = file.toPath();
        return Files.readAllLines(filePath, Charset.defaultCharset());
    }

    private int getNumberOfCommandsIn(String inputFileName) throws IOException {
        Path filePath = getPathFor(inputFileName);
        List<String> lines = Files.readAllLines(filePath, Charset.defaultCharset());
        return lines.size();
    }

    private Path getPathFor(String inputFileName) {
        URL fileUrl = getClass().getResource(inputFileName);
        return Paths.get(fileUrl.getPath());
    }

    private InputStream getFileToReadFrom(String fileName) throws FileNotFoundException {
        Path filePath = getPathFor(fileName);
        return new FileInputStream(filePath.toFile());
    }

    private FileOutputStream getFileToWriteTo(String fileName) throws FileNotFoundException {
        return new FileOutputStream(fileName);
    }
}