package engine;

import engine.JustLikeTwitterEngine;
import interfaces.IOConsole;
import interfaces.JustLikeTwitter;
import org.junit.Before;
import org.junit.Test;
import processors.DateTimeCentral;

import java.io.IOException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReadingUserTimeLineUTest {

    private static final int ONCE_ONLY = 1;

    private static final long ZERO_MINUTES = 0;
    private static final long AFTER_ONE_MINUTE = 60 * 1000;
    private static final long AFTER_FIVE_MINUTES = 5 * AFTER_ONE_MINUTE;
    private static final long ONE_SECOND = 1000;
    private static final long AFTER_FIFTY_SECONDS = 50 * ONE_SECOND;

    private static final String COMMAND_TYPED_BY_HARRY = "Harry -> I like this idea";
    private static final String COMMAND_TYPED_BY_ALICE = "Alice -> I love the weather today";
    private static final String[] COMMANDS_TYPED_BY_BOB = new String[] {
            "Bob -> Damn! We lost!",
            "Bob -> Good game though."
    };

    private JustLikeTwitterEngine justLikeTwitterEngine;
    private IOConsole ioConsole;

    private JustLikeTwitter justLikeTwitter;
    private Date currentDateTime;

    private final DateTimeCentral dateTimeCentral = mock(DateTimeCentral.class);

    @Before
    public void setUp() {
        currentDateTime = new Date();
        justLikeTwitterEngine = mock(JustLikeTwitterEngine.class);
        ioConsole = mock(IOConsole.class);
    }

    /********************************************************************************************
     * Feature: Reading another user's timeline
     *******************************************************************************************/

    /**
     * Scenario: I can view Harry's timeline
     */
    @Test
    public void givenHarryHasAPost_whenHarryIsTypedAtThePrompt_thenHarrysTimeLineIsShown() throws IOException {
        // Given I am at the JustLikeTwitter command prompt ">"
        // And Harry's timeline contains the required posts
        setupJustLikeTwitterWith();
        currentDateTime =
                userTypesAtThePrompt(COMMAND_TYPED_BY_HARRY, currentDateTime, ZERO_MINUTES);

        // When I type "Harry" at the prompt after fifty seconds
        String actualTimeLine =
                getTimelineFor("Harry", currentDateTime, AFTER_FIFTY_SECONDS);

        // Then I see "I like this idea (50 seconds ago)" at the prompt
        String expectedTimeline = "I like this idea (50 seconds ago)" + System.lineSeparator();
        assertThat("Harry's timeline with one post should have been shown",
                actualTimeLine,
                is(equalTo(expectedTimeline)));
    }

    /**
     * Scenario: I can view Alice's timeline
     */
    @Test
    public void givenAliceHasAPost_whenAliceIsTypedAtThePrompt_thenAlicesTimeLineIsShown() throws IOException {
        // Given I am at the JustLikeTwitter command prompt ">"
        // And Alice's timeline contains the required posts
        setupJustLikeTwitterWith();
        currentDateTime =
                userTypesAtThePrompt(COMMAND_TYPED_BY_ALICE, currentDateTime, ZERO_MINUTES);

        // When I type "Alice" at the prompt after five minutes
        String actualTimeLine =
                getTimelineFor("Alice", currentDateTime, AFTER_FIVE_MINUTES);

        // Then I see "I love the weather today (5 minutes ago)" at the prompt
        String expectedTimeline = "I love the weather today (5 minutes ago)" + System.lineSeparator();
        assertThat("Alice's timeline with one post should have been shown",
                actualTimeLine,
                is(equalTo(expectedTimeline)));
    }

    /**
     * Scenario: I can view Bob's timeline
     */
    @Test
    public void givenBobHasPosts_whenBobIsTypedAtThePrompt_thenBobsTimeLineIsShown() throws IOException {
        // Given I am at the JustLikeTwitter command prompt ">"
        // And Bob's timeline contains the required posts
        setupJustLikeTwitterWith();
        currentDateTime = userTypesAtThePrompt(COMMANDS_TYPED_BY_BOB[0], currentDateTime, ZERO_MINUTES);
        currentDateTime = userTypesAtThePrompt(COMMANDS_TYPED_BY_BOB[1], currentDateTime, AFTER_ONE_MINUTE);

        // When I type "Bob" at the prompt after a minute
        String actualTimeLine = getTimelineFor("Bob", currentDateTime, AFTER_ONE_MINUTE);

        // Then I see the below messages in the console:
        // "Good game though. (1 minute ago)"
        // "Damn! We lost! (2 minutes ago)"
        String expectedTimeline =
                "Good game though. (1 minute ago)" + System.lineSeparator() +
                "Damn! We lost! (2 minutes ago)" + System.lineSeparator();
        assertThat("Bob's timeline with two posts should have been shown",
                actualTimeLine,
                is(equalTo(expectedTimeline)));
    }

    private String getTimelineFor(String userNameAsCommand,
                                  Date currentDateTime,
                                  long delayInMilliSeconds) throws IOException {
        userTypesAtThePrompt(userNameAsCommand, currentDateTime, delayInMilliSeconds);
        return justLikeTwitter.showTimeLineFor(userNameAsCommand);
    }

    private Date simulateDelayUsing(Date currentDateTime, long timeInMilliSeconds) {
        Date newDateTime = new Date(currentDateTime.getTime() + timeInMilliSeconds);
        when(dateTimeCentral.getCurrentDateTime()).thenReturn(newDateTime);
        return newDateTime;
    }

    private void setupJustLikeTwitterWith() {
        justLikeTwitterEngine = new JustLikeTwitterEngine(dateTimeCentral);
        justLikeTwitter = new JustLikeTwitter(justLikeTwitterEngine, ioConsole);
    }

    private Date userTypesAtThePrompt(String userTypedCommand,
                                      Date currentDateTime,
                                      long delayInMilliseconds) throws IOException {
        currentDateTime = simulateDelayUsing(currentDateTime, delayInMilliseconds);
        when(ioConsole.showPrompt()).thenReturn(userTypedCommand);
        justLikeTwitter.run(ONCE_ONLY);

        return currentDateTime;
    }
}