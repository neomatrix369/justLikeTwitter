package functionality.command;

import clock.CentralSystemClock;
import domain.FollowsList;
import domain.command.UserTypedCommand;
import domain.message.MessageStore;
import functionality.JustLikeTwitterEngine;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static helper.ImplHelper.convertToDateFrom;
import static helper.TestHelper.ALICE_POSTS_A_MESSAGE;
import static helper.TestHelper.ALICE_READS_POSTS;
import static helper.TestHelper.BOB_POSTS_TWO_MESSAGES;
import static helper.TestHelper.BOB_READS_POSTS;
import static helper.TestHelper.HARRY_POSTS_A_MESSAGE;
import static helper.TestHelper.HARRY_READS_POSTS;
import static helper.TestHelper.SPACE_DELIMITER;
import static helper.TestHelper.at;
import static helper.TestHelper.on;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ReadingUserTimeLineUTest {

    private JustLikeTwitterEngine justLikeTwitterEngine;

    private final CentralSystemClock centralSystemClock = mock(CentralSystemClock.class);

    @Before
    public void setUp() {
        MessageStore messageStore = new MessageStore();
        FollowsList followsList = new FollowsList();
        justLikeTwitterEngine = mock(JustLikeTwitterEngine.class);
        justLikeTwitterEngine = new JustLikeTwitterEngine(messageStore, followsList, centralSystemClock);
    }

    /********************************************************************************************
     * Feature: Reading another user's timeline
     *******************************************************************************************/

    /**
     * Scenario: I can view Harry's timeline
     */
    @Test
    public void givenHarryHasAPost_whenHarryIsTypedAtThePrompt_thenHarrysTimeLineIsShown() throws ParseException {
        // Given I am at the JustLikeTwitter command prompt ">"
        // And Harry's timeline contains the required posts
        atThePrompt(HARRY_POSTS_A_MESSAGE, on("03/09/2015"), at("12:10:00"));

        // When I type "Harry" at the prompt after fifty seconds
        String actualTimeLine = atThePrompt(HARRY_READS_POSTS, on("03/09/2015"), at("12:10:50"));

        // Then I see "I like this idea (50 seconds ago)" at the prompt
        verifyThatTheTimeLinesMatch(
                "Harry's timeline with one post should have been shown",
                actualTimeLine,
                "I like this idea (50 seconds ago)" + System.lineSeparator());
    }

    /**
     * Scenario: I can view Alice's timeline
     */
    @Test
    public void givenAliceHasAPost_whenAliceIsTypedAtThePrompt_thenAlicesTimeLineIsShown() throws ParseException {
        // Given I am at the JustLikeTwitter command prompt ">"
        // And Alice's timeline contains the required posts
        atThePrompt(ALICE_POSTS_A_MESSAGE, on("02/09/2015"), at("12:10:00"));

        // When I type "Alice" at the prompt after five minutes
        String actualTimeLine = atThePrompt(ALICE_READS_POSTS, on("02/09/2015"), at("12:15:10"));

        // Then I see "I love the weather today (5 minutes ago)" at the prompt
        verifyThatTheTimeLinesMatch(
                "Alice's timeline with one post should have been shown",
                actualTimeLine,
                "I love the weather today (5 minutes ago)" + System.lineSeparator());
    }

    /**
     * Scenario: I can view Bob's timeline
     */
    @Test
    public void givenBobHasPosts_whenBobIsTypedAtThePrompt_thenBobsTimeLineIsShown() throws ParseException {
        // Given I am at the JustLikeTwitter command prompt ">"
        // And Bob's timeline contains the required posts
        atThePrompt(BOB_POSTS_TWO_MESSAGES[0], on("01/09/2015"), at("10:10:00"));
        atThePrompt(BOB_POSTS_TWO_MESSAGES[1], on("01/09/2015"), at("10:11:15"));

        // When I type "Bob" at the prompt after a minute
        String actualTimeLine = atThePrompt(BOB_READS_POSTS, on("01/09/2015"), at("10:12:25"));

        // Then I see the below messages in the console:
        // "Good game though. (1 minute ago)"
        // "Damn! We lost! (2 minutes ago)"
        verifyThatTheTimeLinesMatch(
                "Bob's timeline with two posts should have been shown",
                actualTimeLine,
                "Good game though. (1 minute ago)" + System.lineSeparator() +
                        "Damn! We lost! (2 minutes ago)" + System.lineSeparator()
        );
    }

    private String atThePrompt(UserTypedCommand userTypedCommand,
                               String onASpecificDate,
                               String atASpecificTime) throws ParseException {
        Date theSimulatedActionDate = convertToDateFrom(onASpecificDate + SPACE_DELIMITER + atASpecificTime);
        when(centralSystemClock.getCurrentDateTime()).thenReturn(theSimulatedActionDate);
        return justLikeTwitterEngine.executeCommand(userTypedCommand);
    }

    private void verifyThatTheTimeLinesMatch(String reason,
                                             String actualTimeLine,
                                             String expectedTimeline) {
        assertThat(reason,
                actualTimeLine,
                is(equalTo(expectedTimeline)));
    }
}