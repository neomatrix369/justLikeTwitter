package com.codurance;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.Followees;
import com.codurance.domain.io.UserInput;
import com.codurance.domain.io.UserOutput;
import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.JustLikeTwitterEngine;
import com.codurance.helper.FileAsInputStream;
import com.codurance.helper.FileAsOutputStream;
import com.codurance.userinterface.IOConsole;
import org.approvaltests.Approvals;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import static com.codurance.helper.FileIOHelper.convertListToStringWithLinefeed;
import static com.codurance.helper.FileIOHelper.getFileToWriteTo;
import static com.codurance.helper.FileIOHelper.getNumberOfCommandsIn;
import static com.codurance.helper.FileIOHelper.getTheContentOf;
import static com.codurance.helper.FileIOHelper.loadDatesFrom;
import static com.codurance.helper.TestHelper.ACTUAL_OUTPUT_FILE;
import static com.codurance.helper.TestHelper.REPLAY_INPUT_FILE;
import static org.mockito.AdditionalAnswers.returnsElementsOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UseReporter(DiffReporter.class)
public class FullLifeCycleAcceptanceTest {

    private JustLikeTwitter justLikeTwitter;
    private final CentralSystemClock centralSystemClockMock = mock(CentralSystemClock.class);

    @Before
    public void setUp() throws IOException {
        MessageStore messageStore = new MessageStore();
        Followees followees = new Followees();

        JustLikeTwitterEngine justLikeTwitterEngine = new JustLikeTwitterEngine(
                centralSystemClockMock,
                messageStore,
                followees
        );

        IOConsole ioConsole = new IOConsole(
                getUserInput(REPLAY_INPUT_FILE),
                getUserOutput(ACTUAL_OUTPUT_FILE));

        justLikeTwitter = new JustLikeTwitter(ioConsole, justLikeTwitterEngine);
    }

    private UserInput getUserInput(String inputFileName) throws IOException {
        return new FileAsInputStream(getClass(), inputFileName);
    }

    private UserOutput getUserOutput(String outputFileName) throws FileNotFoundException {
        OutputStream fileAsOutputStream = getFileToWriteTo(outputFileName);
        return new FileAsOutputStream(fileAsOutputStream);
    }

    @Test
    public void givenJustLikeTwitter_whenASeriesOfCommandsArePassedIn_thenResponsesForThemAreSeenInTheConsole() throws Exception {
        // given
        final List<Date> dateTimeForMessages = loadDatesFrom(getClass(), REPLAY_INPUT_FILE);
        when(centralSystemClockMock.getCurrentDateTime()).thenAnswer(returnsElementsOf(dateTimeForMessages));

        // when
        int numberOfCommands = getNumberOfCommandsIn(getClass(), REPLAY_INPUT_FILE);
        justLikeTwitter.run(numberOfCommands);

        // then
        String actualOutputFileContent = convertListToStringWithLinefeed(getTheContentOf(ACTUAL_OUTPUT_FILE));
        Approvals.verify(actualOutputFileContent);
    }
}