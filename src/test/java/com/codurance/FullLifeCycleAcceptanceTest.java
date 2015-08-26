package com.codurance;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.FollowsList;
import com.codurance.domain.Keyboard;
import com.codurance.domain.Screen;
import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.JustLikeTwitterEngine;
import com.codurance.userinterface.IOConsole;
import org.approvaltests.Approvals;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.codurance.helper.FileIOHelper.convertListToStringWithLinefeed;
import static com.codurance.helper.FileIOHelper.getFileToReadFrom;
import static com.codurance.helper.FileIOHelper.getFileToWriteTo;
import static com.codurance.helper.FileIOHelper.getNumberOfCommandsIn;
import static com.codurance.helper.FileIOHelper.getTheContentOf;
import static com.codurance.helper.FileIOHelper.loadDatesFrom;
import static com.codurance.helper.ImplHelper.EXTRA_LINEFEED_NEEDED;
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
        FollowsList followsList = new FollowsList();

        JustLikeTwitterEngine justLikeTwitterEngine = new JustLikeTwitterEngine(
                centralSystemClockMock,
                messageStore,
                followsList
        );

        IOConsole ioConsole = new IOConsole(
                new Keyboard(getFileToReadFrom(getClass(), REPLAY_INPUT_FILE)),
                new Screen(getFileToWriteTo(ACTUAL_OUTPUT_FILE)),
                EXTRA_LINEFEED_NEEDED);

        justLikeTwitter = new JustLikeTwitter(ioConsole, justLikeTwitterEngine);
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