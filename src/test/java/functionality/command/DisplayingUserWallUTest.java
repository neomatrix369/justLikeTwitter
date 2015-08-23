package functionality.command;

import clock.CentralSystemClock;
import domain.FollowsList;
import domain.message.MessageStore;
import domain.command.UserTypedCommand;
import functionality.JustLikeTwitterEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static helper.ImplHelper.convertToDateFrom;
import static helper.TestHelper.ALICE_POSTS_A_MESSAGE;
import static helper.TestHelper.BOB_POSTS_TWO_MESSAGES;
import static helper.TestHelper.CHARLIE_FOLLOWS_ALICE;
import static helper.TestHelper.CHARLIE_FOLLOWS_BOB;
import static helper.TestHelper.CHARLIE_POSTS_A_MESSAGE;
import static helper.TestHelper.CHARLIE_REQUESTS_WALL;
import static helper.TestHelper.SPACE_DELIMETER;
import static helper.TestHelper.at;
import static helper.TestHelper.on;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Feature: Displaying a user's wall
 */

public class DisplayingUserWallUTest {

    private JustLikeTwitterEngine justLikeTwitterEngine;

    private final CentralSystemClock centralSystemClock = mock(CentralSystemClock.class);

    /**
     * Scenario: Charlie can subscribe to Alice’s timeline, and views an aggregated list of all subscriptions
     */

    @Before
    public void setUp() {
        justLikeTwitterEngine = mock(JustLikeTwitterEngine.class);
        MessageStore messageStore = new MessageStore();
        FollowsList followsList = new FollowsList();
        justLikeTwitterEngine = new JustLikeTwitterEngine(messageStore, followsList, centralSystemClock);
    }

    @Test
    public void givenAliceAndCharliesHavePostsAndCharlieFollowsAlice_whenCharlieRequestsToSeeTheWall_thenBothAliceAndCharliesPostsAreShownInTheTimeLine() {
        // Given Charlie is at the JustLikeTwitter command prompt ">"
        // And Alice's timeline contains the required posts
        // And he enters "Charlie -> I'm in New York today! Anyone wants to have a coffee?" at the prompt
        // And then he enters "Charlie follows Alice" at the prompt
        userTypesAtThePrompt(ALICE_POSTS_A_MESSAGE, on("05/09/2015"), at("10:45:00"));
        userTypesAtThePrompt(CHARLIE_POSTS_A_MESSAGE, on("05/09/2015"), at("10:50:00"));
        userTypesAtThePrompt(CHARLIE_FOLLOWS_ALICE, on("05/09/2015"), at("10:50:02"));

        // When he types "Charlie wall" at the prompt
        String actualWall = userTypesAtThePrompt(CHARLIE_REQUESTS_WALL, on("05/09/2015"), at("10:50:02"));

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
    public void givenAliceBobAndCharliesHavePostsAndCharlieFollowsAliceAndBob_whenCharlieRequestsToSeeTheWall_thenAllTheirPostsAreShownInTheTimeLine() {
        // Given Charlie is at the JustLikeTwitter command prompt ">"
        // And Alice 's timeline contains the required posts
        // And Bob's timeline contains the required posts
        // And he enters "Charlie -> I'm in New York today! Anyone wants to have a coffee?" at the prompt
        // And then he enters "Charlie follows Alice" at the prompt
        // And then he enters "Charlie follows Bob" at the prompt
        userTypesAtThePrompt(ALICE_POSTS_A_MESSAGE, on("04/09/2015"), at("09:30:00"));
        userTypesAtThePrompt(BOB_POSTS_TWO_MESSAGES[0], on("04/09/2015"), at("09:33:00"));
        userTypesAtThePrompt(BOB_POSTS_TWO_MESSAGES[1], on("04/09/2015"), at("09:34:00"));
        userTypesAtThePrompt(CHARLIE_POSTS_A_MESSAGE, on("04/09/2015"), at("09:35:00"));
        userTypesAtThePrompt(CHARLIE_FOLLOWS_ALICE, on("04/09/2015"), at("09:35:00"));
        userTypesAtThePrompt(CHARLIE_FOLLOWS_BOB, on("04/09/2015"), at("09:35:00"));

        // When he types "Charlie wall" at the prompt
        String actualWall = userTypesAtThePrompt(CHARLIE_REQUESTS_WALL, on("04/09/2015"), at("09:35:15"));

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
    public void givenBobAndCharliesHavePostsAndCharlieFollowsBob_whenCharlieRequestsToSeeTheWall_thenBothBobAndCharliesPostsAreShownInTheTimeLine() {
        // Given Charlie is at the JustLikeTwitter command prompt ">"
        // And Bob's timeline contains the required posts
        // And he enters "Charlie -> I'm in New York today! Anyone wants to have a coffee?" at the prompt
        // And then he enters "Charlie follows Bob" at the prompt
        userTypesAtThePrompt(BOB_POSTS_TWO_MESSAGES[0], on("06/09/2015"), at("09:00:00"));
        userTypesAtThePrompt(BOB_POSTS_TWO_MESSAGES[1], on("06/09/2015"), at("09:01:00"));
        userTypesAtThePrompt(CHARLIE_POSTS_A_MESSAGE, on("06/09/2015"), at("09:02:00"));
        userTypesAtThePrompt(CHARLIE_FOLLOWS_BOB, on("06/09/2015"), at("09:02:00"));

        // When he types "Charlie wall" at the prompt
        String actualWall = userTypesAtThePrompt(CHARLIE_REQUESTS_WALL, on("06/09/2015"), at("09:02:15"));

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

    private String userTypesAtThePrompt(UserTypedCommand userTypedCommand,
                                        String onSpecificDate,
                                        String atASpecificTime) {
        Date theSimulatedActionDate = convertToDateFrom(onSpecificDate + SPACE_DELIMETER + atASpecificTime);
        when(centralSystemClock.getCurrentDateTime()).thenReturn(theSimulatedActionDate);
        return justLikeTwitterEngine.executeCommand(userTypedCommand);
    }

    private void verifyThatTheWallsMatch(String reason,
                                         String actualWall,
                                         String expectedWall) {
        assertThat(reason,
                actualWall,
                is(equalTo(expectedWall)));
    }
}