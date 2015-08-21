import clock.CentralSystemClock;
import domain.FollowsList;
import domain.Keyboard;
import domain.MessageStore;
import domain.Screen;
import engine.JustLikeTwitterEngine;
import interfaces.IOConsole;
import interfaces.JustLikeTwitter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static helper.FileIOHelper.convertListToStringWithLinefeed;
import static helper.FileIOHelper.getFileToReadFrom;
import static helper.FileIOHelper.getFileToWriteTo;
import static helper.FileIOHelper.getNumberOfCommandsIn;
import static helper.FileIOHelper.getPathFor;
import static helper.FileIOHelper.getTheContentOf;
import static helper.FileIOHelper.loadDatesFrom;
import static helper.TestHelper.ACTUAL_OUTPUT_FILE;
import static helper.TestHelper.APPROVER;
import static helper.TestHelper.EXPECTED_OUTPUT_FILE;
import static helper.TestHelper.EXTRA_LINEFEED_NEEDED;
import static helper.TestHelper.REPLAY_INPUT_FILE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FullLifeCycleAcceptanceTest {

    private JustLikeTwitter justLikeTwitter;
    private final CentralSystemClock centralSystemClockMock = mock(CentralSystemClock.class);
    private final List<Date> dateTimeForMessages = loadDatesFrom(getClass(), REPLAY_INPUT_FILE);

    @Before
    public void setUp() throws IOException {
        MessageStore messageStore = new MessageStore();
        FollowsList followsList = new FollowsList();

        JustLikeTwitterEngine justLikeTwitterEngine = new JustLikeTwitterEngine(
                messageStore,
                followsList,
                centralSystemClockMock);

        IOConsole ioConsole = new IOConsole(
                new Keyboard(getFileToReadFrom(getClass(), REPLAY_INPUT_FILE)),
                new Screen(getFileToWriteTo(ACTUAL_OUTPUT_FILE)),
                EXTRA_LINEFEED_NEEDED);

        justLikeTwitter = new JustLikeTwitter(ioConsole, justLikeTwitterEngine);
    }

    @Test
    public void givenJustLikeTwitter_whenASeriesOfCommandsArePassedIn_thenResponsesForThemAreSeenInTheConsole() throws IOException {
        // given
        when(centralSystemClockMock.getCurrentDateTime())
                .thenAnswer(AdditionalAnswers.returnsElementsOf(dateTimeForMessages));

        // when
        int numberOfCommands = getNumberOfCommandsIn(getClass(), REPLAY_INPUT_FILE);
        justLikeTwitter.run(numberOfCommands);

        // then
        String actualOutputFileContent = convertListToStringWithLinefeed(getTheContentOf(ACTUAL_OUTPUT_FILE));
        APPROVER.verify(actualOutputFileContent, getPathFor(getClass(), EXPECTED_OUTPUT_FILE));
    }
}