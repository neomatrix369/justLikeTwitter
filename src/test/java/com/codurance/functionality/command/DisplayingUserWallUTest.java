package com.codurance.functionality.command;

import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.JustLikeTwitterEngine;
import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.FollowsList;
import com.codurance.command.UserTypedCommand;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static com.codurance.helper.ImplHelper.convertToDateFrom;
import static com.codurance.helper.TestHelper.ALICE_POSTS_A_MESSAGE;
import static com.codurance.helper.TestHelper.BOB_POSTS_TWO_MESSAGES;
import static com.codurance.helper.TestHelper.CHARLIE_FOLLOWS_ALICE;
import static com.codurance.helper.TestHelper.CHARLIE_FOLLOWS_BOB;
import static com.codurance.helper.TestHelper.CHARLIE_POSTS_A_MESSAGE;
import static com.codurance.helper.TestHelper.CHARLIE_REQUESTS_WALL;
import static com.codurance.helper.TestHelper.SPACE_DELIMITER;
import static com.codurance.helper.TestHelper.at;
import static com.codurance.helper.TestHelper.on;
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
        MessageStore messageStore = new MessageStore();
        FollowsList followsList = new FollowsList();
        justLikeTwitterEngine = new JustLikeTwitterEngine(centralSystemClock, messageStore, followsList);
    }

    @Test
    public void givenAliceAndCharliesHavePostsAndCharlieFollowsAlice_whenCharlieRequestsToSeeTheWall_thenBothAliceAndCharliesPostsAreShownInTheTimeLine() throws ParseException {
        // Given Charlie is at the JustLikeTwitter command prompt ">"
        // And Alice's timeline contains the required posts
        // And he enters "Charlie -> I'm in New York today! Anyone wants to have a coffee?" at the prompt
        // And then he enters "Charlie follows Alice" at the prompt
        atThePrompt(ALICE_POSTS_A_MESSAGE, on("05/09/2015"), at("10:45:00"));
        atThePrompt(CHARLIE_POSTS_A_MESSAGE, on("05/09/2015"), at("10:50:00"));
        atThePrompt(CHARLIE_FOLLOWS_ALICE, on("05/09/2015"), at("10:50:02"));

        // When he types "Charlie wall" at the prompt
        String actualWall = atThePrompt(CHARLIE_REQUESTS_WALL, on("05/09/2015"), at("10:50:02"));

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
    public void givenAliceBobAndCharliesHavePostsAndCharlieFollowsAliceAndBob_whenCharlieRequestsToSeeTheWall_thenAllTheirPostsAreShownInTheTimeLine() throws ParseException {
        // Given Charlie is at the JustLikeTwitter command prompt ">"
        // And Alice 's timeline contains the required posts
        // And Bob's timeline contains the required posts
        // And he enters "Charlie -> I'm in New York today! Anyone wants to have a coffee?" at the prompt
        // And then he enters "Charlie follows Alice" at the prompt
        // And then he enters "Charlie follows Bob" at the prompt
        atThePrompt(ALICE_POSTS_A_MESSAGE, on("04/09/2015"), at("09:30:00"));
        atThePrompt(BOB_POSTS_TWO_MESSAGES[0], on("04/09/2015"), at("09:33:00"));
        atThePrompt(BOB_POSTS_TWO_MESSAGES[1], on("04/09/2015"), at("09:34:00"));
        atThePrompt(CHARLIE_POSTS_A_MESSAGE, on("04/09/2015"), at("09:35:00"));
        atThePrompt(CHARLIE_FOLLOWS_ALICE, on("04/09/2015"), at("09:35:00"));
        atThePrompt(CHARLIE_FOLLOWS_BOB, on("04/09/2015"), at("09:35:00"));

        // When he types "Charlie wall" at the prompt
        String actualWall = atThePrompt(CHARLIE_REQUESTS_WALL, on("04/09/2015"), at("09:35:15"));

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
    public void givenBobAndCharliesHavePostsAndCharlieFollowsBob_whenCharlieRequestsToSeeTheWall_thenBothBobAndCharliesPostsAreShownInTheTimeLine() throws ParseException {
        // Given Charlie is at the JustLikeTwitter command prompt ">"
        // And Bob's timeline contains the required posts
        // And he enters "Charlie -> I'm in New York today! Anyone wants to have a coffee?" at the prompt
        // And then he enters "Charlie follows Bob" at the prompt
        atThePrompt(BOB_POSTS_TWO_MESSAGES[0], on("06/09/2015"), at("09:00:00"));
        atThePrompt(BOB_POSTS_TWO_MESSAGES[1], on("06/09/2015"), at("09:01:00"));
        atThePrompt(CHARLIE_POSTS_A_MESSAGE, on("06/09/2015"), at("09:02:00"));
        atThePrompt(CHARLIE_FOLLOWS_BOB, on("06/09/2015"), at("09:02:00"));

        // When he types "Charlie wall" at the prompt
        String actualWall = atThePrompt(CHARLIE_REQUESTS_WALL, on("06/09/2015"), at("09:02:15"));

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

    private String atThePrompt(UserTypedCommand theUserTypes,
                               String onASpecificDate,
                               String atASpecificTime) throws ParseException {
        Date theSimulatedActionDate = convertToDateFrom(onASpecificDate + SPACE_DELIMITER + atASpecificTime);
        when(centralSystemClock.getCurrentDateTime()).thenReturn(theSimulatedActionDate);
        return justLikeTwitterEngine.executeCommand(theUserTypes);
    }

    private void verifyThatTheWallsMatch(String reason,
                                         String actualWall,
                                         String expectedWall) {
        assertThat(reason,
                actualWall,
                is(equalTo(expectedWall)));
    }
}