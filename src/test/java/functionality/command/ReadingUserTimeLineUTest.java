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
import static helper.TestHelper.HARRY_POSTS_A_MESSAGE;
import static helper.TestHelper.SPACE_DELIMETER;
import static helper.TestHelper.USER_ALICE;
import static helper.TestHelper.USER_BOB;
import static helper.TestHelper.USER_HARRY;
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
        justLikeTwitterEngine = mock(JustLikeTwitterEngine.class);
        MessageStore messageStore = new MessageStore();
        FollowsList followsList = new FollowsList();
        justLikeTwitterEngine = new JustLikeTwitterEngine(messageStore, followsList, centralSystemClock);
    }

    /********************************************************************************************
     * Feature: Reading another user's timeline
     *******************************************************************************************/

    /**
     * Scenario: I can view Harry's timeline
     */
    @Test
    public void givenHarryHasAPost_whenHarryIsTypedAtThePrompt_thenHarrysTimeLineIsShown() {
        // Given I am at the JustLikeTwitter command prompt ">"
        // And Harry's timeline contains the required posts
        userTypesAtThePrompt(HARRY_POSTS_A_MESSAGE, on("03/09/2015"), at("12:10:00"));

        // When I type "Harry" at the prompt after fifty seconds
        UserTypedCommand userTypedCommand = new UserTypedCommand(USER_HARRY);
        String actualTimeLine = userTypesAtThePrompt(userTypedCommand, on("03/09/2015"), at("12:10:50"));

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
    public void givenAliceHasAPost_whenAliceIsTypedAtThePrompt_thenAlicesTimeLineIsShown() {
        // Given I am at the JustLikeTwitter command prompt ">"
        // And Alice's timeline contains the required posts
        userTypesAtThePrompt(ALICE_POSTS_A_MESSAGE, on("02/09/2015"), at("12:10:00"));

        // When I type "Alice" at the prompt after five minutes
        UserTypedCommand userTypedCommand = new UserTypedCommand(USER_ALICE);
        String actualTimeLine = userTypesAtThePrompt(userTypedCommand, on("02/09/2015"), at("12:15:10"));

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
    public void givenBobHasPosts_whenBobIsTypedAtThePrompt_thenBobsTimeLineIsShown() {
        // Given I am at the JustLikeTwitter command prompt ">"
        // And Bob's timeline contains the required posts
        userTypesAtThePrompt(BOB_POSTS_TWO_MESSAGES[0], on("01/09/2015"), at("10:10:00"));
        userTypesAtThePrompt(BOB_POSTS_TWO_MESSAGES[1], on("01/09/2015"), at("10:11:15"));

        // When I type "Bob" at the prompt after a minute
        UserTypedCommand userTypedCommand = new UserTypedCommand(USER_BOB);
        String actualTimeLine = userTypesAtThePrompt(userTypedCommand, on("01/09/2015"), at("10:12:25"));

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

    private String userTypesAtThePrompt(UserTypedCommand userTypedCommand,
                                        String onSpecificDate,
                                        String atASpecificTime) {
        Date theSimulatedActionDate = convertToDateFrom(onSpecificDate + SPACE_DELIMETER + atASpecificTime);
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