package engine;

import clock.CentralSystemClock;
import domain.FollowsList;
import domain.MessageStore;
import domain.UserTypedCommand;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static helper.TestHelper.AFTER_FIFTY_SECONDS;
import static helper.TestHelper.AFTER_FIVE_MINUTES;
import static helper.TestHelper.AFTER_ONE_MINUTE;
import static helper.TestHelper.ALICE_POSTS_A_MESSAGE;
import static helper.TestHelper.BOB_POSTS_TWO_MESSAGES;
import static helper.TestHelper.HARRY_POSTS_A_MESSAGE;
import static helper.TestHelper.USER_ALICE;
import static helper.TestHelper.USER_BOB;
import static helper.TestHelper.USER_HARRY;
import static helper.TestHelper.ZERO_MINUTES;
import static helper.TestHelper.simulateDelayUsing;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ReadingUserTimeLineUTest {

    private JustLikeTwitterEngine justLikeTwitterEngine;
    private Date currentDateTime;

    private final CentralSystemClock centralSystemClock = mock(CentralSystemClock.class);

    @Before
    public void setUp() {
        currentDateTime = new Date();
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
        userTypesAtThePrompt(HARRY_POSTS_A_MESSAGE, ZERO_MINUTES);

        // When I type "Harry" at the prompt after fifty seconds
        UserTypedCommand userTypedCommand = new UserTypedCommand(USER_HARRY);
        String actualTimeLine = userTypesAtThePrompt(userTypedCommand, AFTER_FIFTY_SECONDS);

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
        userTypesAtThePrompt(ALICE_POSTS_A_MESSAGE, ZERO_MINUTES);

        // When I type "Alice" at the prompt after five minutes
        UserTypedCommand userTypedCommand = new UserTypedCommand(USER_ALICE);
        String actualTimeLine = userTypesAtThePrompt(userTypedCommand, AFTER_FIVE_MINUTES);

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
        userTypesAtThePrompt(BOB_POSTS_TWO_MESSAGES[0], ZERO_MINUTES);
        userTypesAtThePrompt(BOB_POSTS_TWO_MESSAGES[1], AFTER_ONE_MINUTE);

        // When I type "Bob" at the prompt after a minute
        UserTypedCommand userTypedCommand = new UserTypedCommand(USER_BOB);
        String actualTimeLine = userTypesAtThePrompt(userTypedCommand, AFTER_ONE_MINUTE);

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
                                        long delayInMilliseconds) {
        currentDateTime = simulateDelayUsing(
                currentDateTime,
                centralSystemClock,
                delayInMilliseconds);
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