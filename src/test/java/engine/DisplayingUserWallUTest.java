package engine;

import clock.CentralSystemClock;
import elements.MessageStore;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Feature: Displaying a user's wall
 */

public class DisplayingUserWallUTest {

    private static final long ZERO_MINUTES = 0;
    private static final long THOUSAND_MILLISECONDS = 1000;
    private static final long AFTER_ONE_MINUTE = 60 * THOUSAND_MILLISECONDS;
    private static final long AFTER_FIVE_MINUTES = 5 * AFTER_ONE_MINUTE;
    private static final long ONE_SECOND = THOUSAND_MILLISECONDS;
    private static final long AFTER_TWO_SECONDS = 2 * ONE_SECOND;
    private static final long AFTER_FIFTEEN_SECONDS = 15 * ONE_SECOND;
    private static final long AFTER_THREE_MINUTES = 3 * AFTER_ONE_MINUTE;

    private Date currentDateTime;
    private JustLikeTwitterEngine justLikeTwitterEngine;

    private final CentralSystemClock centralSystemClock = mock(CentralSystemClock.class);

    private static final String[] COMMANDS_TYPED_BY_ALICE = new String[]{
            "Alice -> I love the weather today",
    };

    private static final String[] COMMANDS_TYPED_BY_BOB = new String[]{
            "Bob -> Damn! We lost!",
            "Bob -> Good game though."
    };

    private static final String[] COMMANDS_TYPED_BY_CHARLIE = new String[]{
            "Charlie -> I'm in New York today! Anyone wants to have a coffee?",
            "Charlie follows Alice",
            "Charlie follows Bob",
            "Charlie wall"
    };

    /**
     * Scenario: Charlie can subscribe to Alice’s timeline, and views an aggregated list of all subscriptions
     */

    @Before
    public void setUp() {
        currentDateTime = new Date();
        justLikeTwitterEngine = mock(JustLikeTwitterEngine.class);
        MessageStore messageStore = new MessageStore();
        justLikeTwitterEngine = new JustLikeTwitterEngine(messageStore, centralSystemClock);
    }

    @Test
    public void givenAliceAndCharliesHavePostsAndCharlieFollowsAlice_whenCharlieRequestsToSeeTheWall_thenBothAliceAndCharliesPostsAreShownInTheTimeLine() throws IOException {
        // Given Charlie is at the JustLikeTwitter command prompt ">"
        // And Alice's timeline contains the required posts
        // And he enters "Charlie -> I'm in New York today! Anyone wants to have a coffee?" at the prompt
        // And then he enters "Charlie follows Alice" at the prompt
        userTypesAtThePrompt(COMMANDS_TYPED_BY_ALICE[0], ZERO_MINUTES);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[0], AFTER_FIVE_MINUTES);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[1], AFTER_TWO_SECONDS);

        // When he types "Charlie wall" at the prompt
        String actualWall = userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[3], ZERO_MINUTES);

        // Then he sees the below in the console
        // "Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)
        // Alice - I love the weather today (5 minutes ago)"
        verifyThatTheWallsMatch(
                "Should have displayed a wall with Charlie's and Alice's time lines on it",
                actualWall,
                "Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)" + System.lineSeparator() +
                "Alice - I love the weather today (5 minutes ago)" + System.lineSeparator());
    }

    @Test
    public void givenAliceBobAndCharliesHavePostsAndCharlieFollowsAliceAndBob_whenCharlieRequestsToSeeTheWall_thenAllTheirPostsAreShownInTheTimeLine() throws IOException {
        // Given Charlie is at the JustLikeTwitter command prompt ">"
        // And Alice 's timeline contains the required posts
        // And Bob's timeline contains the required posts
        // And he enters "Charlie -> I'm in New York today! Anyone wants to have a coffee?" at the prompt
        // And then he enters "Charlie follows Alice" at the prompt
        // And then he enters "Charlie follows Bob" at the prompt
        userTypesAtThePrompt(COMMANDS_TYPED_BY_ALICE[0], ZERO_MINUTES);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_BOB[0], AFTER_THREE_MINUTES);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_BOB[1], AFTER_ONE_MINUTE);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[0], AFTER_ONE_MINUTE);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[1], ZERO_MINUTES);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[2], ZERO_MINUTES);

        // When he types "Charlie wall" at the prompt
        String actualWall = userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[3], AFTER_FIFTEEN_SECONDS);

        // Then he sees the below in the console
        // "Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
        // Bob - Good game though. (1 minute ago)
        // Bob - Damn! We lost! (2 minutes ago)
        // Alice - I love the weather today (5 minutes ago)"
        verifyThatTheWallsMatch(
                "Should have displayed a wall with Charlie's, Alice's and Bob's time lines on it",
                actualWall,
                "Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)" + System.lineSeparator() +
                "Bob - Good game though. (1 minute ago)" + System.lineSeparator() +
                "Bob - Damn! We lost! (2 minutes ago)" + System.lineSeparator() +
                "Alice - I love the weather today (5 minutes ago)" + System.lineSeparator());
    }

    @Test
    public void givenBobAndCharliesHavePostsAndCharlieFollowsBob_whenCharlieRequestsToSeeTheWall_thenBothBobAndCharliesPostsAreShownInTheTimeLine() throws IOException {
        // Given Charlie is at the JustLikeTwitter command prompt ">"
        // And Bob's timeline contains the required posts
        // And he enters "Charlie -> I'm in New York today! Anyone wants to have a coffee?" at the prompt
        // And then he enters "Charlie follows Bob" at the prompt
        userTypesAtThePrompt(COMMANDS_TYPED_BY_BOB[0], ZERO_MINUTES);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_BOB[1], AFTER_ONE_MINUTE);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[0], AFTER_ONE_MINUTE);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[2], ZERO_MINUTES);

        // When he types "Charlie wall" at the prompt
        String actualWall = userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[3], AFTER_FIFTEEN_SECONDS);

        // Then he sees the below in the console
        // "Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)
        // Bob - Good game though. (1 minute ago)
        // Bob - Damn! We lost! (2 minutes ago)"
        verifyThatTheWallsMatch(
                "Should have displayed a wall with Charlie's and Bob's time lines on it",
                actualWall,
                "Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)" + System.lineSeparator() +
                        "Bob - Good game though. (1 minute ago)" + System.lineSeparator() +
                        "Bob - Damn! We lost! (2 minutes ago)" + System.lineSeparator());
    }

    private String userTypesAtThePrompt(String userTypedCommand,
                                      long delayInMilliseconds) throws IOException {
        currentDateTime = simulateDelayUsing(currentDateTime, delayInMilliseconds);
        return justLikeTwitterEngine.executeCommand(userTypedCommand);
    }

    private Date simulateDelayUsing(Date currentDateTime, long timeInMilliSeconds) {
        Date newDateTime = new Date(currentDateTime.getTime() + timeInMilliSeconds);
        when(centralSystemClock.getCurrentDateTime()).thenReturn(newDateTime);
        return newDateTime;
    }

    private void verifyThatTheWallsMatch(String reason,
                                         String actualWall,
                                         String expectedWall) {
        assertThat(reason,
                actualWall,
                is(equalTo(expectedWall)));
    }
}