package com.codurance;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.Keyboard;
import com.codurance.domain.Screen;
import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.JustLikeTwitterEngine;
import com.codurance.helper.FileIOHelper;
import com.codurance.helper.ImplHelper;
import com.codurance.helper.TestHelper;
import com.codurance.domain.FollowsList;
import org.approvaltests.Approvals;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.Before;
import org.junit.Test;
import com.codurance.userinterface.IOConsole;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
                messageStore,
                followsList,
                centralSystemClockMock);

        IOConsole ioConsole = new IOConsole(
                new Keyboard(FileIOHelper.getFileToReadFrom(getClass(), TestHelper.REPLAY_INPUT_FILE)),
                new Screen(FileIOHelper.getFileToWriteTo(TestHelper.ACTUAL_OUTPUT_FILE)),
                ImplHelper.EXTRA_LINEFEED_NEEDED);

        justLikeTwitter = new JustLikeTwitter(ioConsole, justLikeTwitterEngine);
    }

    @Test
    public void givenJustLikeTwitter_whenASeriesOfCommandsArePassedIn_thenResponsesForThemAreSeenInTheConsole() throws Exception {
        // given
        final List<Date> dateTimeForMessages = FileIOHelper.loadDatesFrom(getClass(), TestHelper.REPLAY_INPUT_FILE);
        when(centralSystemClockMock.getCurrentDateTime())
                .thenAnswer(returnsElementsOf(dateTimeForMessages));

        // when
        int numberOfCommands = FileIOHelper.getNumberOfCommandsIn(getClass(), TestHelper.REPLAY_INPUT_FILE);
        justLikeTwitter.run(numberOfCommands);

        // then
        String actualOutputFileContent = FileIOHelper.convertListToStringWithLinefeed(FileIOHelper.getTheContentOf(TestHelper.ACTUAL_OUTPUT_FILE));
        Approvals.verify(actualOutputFileContent);
    }
}