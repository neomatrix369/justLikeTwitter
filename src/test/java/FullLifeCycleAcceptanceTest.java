import clock.CentralSystemClock;
import com.github.approval.Approvals;
import domain.FollowsList;
import domain.Keyboard;
import domain.Screen;
import domain.message.MessageStore;
import functionality.JustLikeTwitterEngine;
import org.junit.Before;
import org.junit.Test;
import userinterfaces.IOConsole;
import userinterfaces.JustLikeTwitter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static helper.FileIOHelper.convertListToStringWithLinefeed;
import static helper.FileIOHelper.getFileToReadFrom;
import static helper.FileIOHelper.getFileToWriteTo;
import static helper.FileIOHelper.getNumberOfCommandsIn;
import static helper.FileIOHelper.getPathFor;
import static helper.FileIOHelper.getTheContentOf;
import static helper.FileIOHelper.loadDatesFrom;
import static helper.ImplHelper.EXTRA_LINEFEED_NEEDED;
import static helper.TestHelper.ACTUAL_OUTPUT_FILE;
import static helper.TestHelper.EXPECTED_OUTPUT_FILE;
import static helper.TestHelper.REPLAY_INPUT_FILE;
import static org.mockito.AdditionalAnswers.returnsElementsOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
                new Keyboard(getFileToReadFrom(getClass(), REPLAY_INPUT_FILE)),
                new Screen(getFileToWriteTo(ACTUAL_OUTPUT_FILE)),
                EXTRA_LINEFEED_NEEDED);

        justLikeTwitter = new JustLikeTwitter(ioConsole, justLikeTwitterEngine);
    }

    @Test
    public void givenJustLikeTwitter_whenASeriesOfCommandsArePassedIn_thenResponsesForThemAreSeenInTheConsole() throws IOException, ParseException {
        // given
        final List<Date> dateTimeForMessages = loadDatesFrom(getClass(), REPLAY_INPUT_FILE);
        when(centralSystemClockMock.getCurrentDateTime())
                .thenAnswer(returnsElementsOf(dateTimeForMessages));

        // when
        int numberOfCommands = getNumberOfCommandsIn(getClass(), REPLAY_INPUT_FILE);
        justLikeTwitter.run(numberOfCommands);

        // then
        String actualOutputFileContent = convertListToStringWithLinefeed(getTheContentOf(ACTUAL_OUTPUT_FILE));
        Approvals.verify(actualOutputFileContent, getPathFor(getClass(), EXPECTED_OUTPUT_FILE));
    }
}