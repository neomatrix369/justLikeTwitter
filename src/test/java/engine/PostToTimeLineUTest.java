package engine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Feature: Posting to a personal timeline
 */

public class PostToTimeLineUTest {

    private TwitterLikeEngine twitterLikeEngine;

    @Before
    public void setUp() {
        twitterLikeEngine = new TwitterLikeEngine();
    }

    /**
     * Scenario: Alice's message is added to Alice's timeline
     */
    @Test
    public void givenAlicesTimeLineIsEmpty_WhenANewMessageIsPassedToTheEngine_ThenMessageIsAddedToAlicesTimeLine() {
        // Given Alice's timeline is empty
        // And a new message "Alice -> I love the weather today" is available
        // When the message is passed to the engine for Alice
        List<String> actualMessagesOnTimeLine = processMessagesReceivedFor("Alice", "Alice -> I love the weather today");

        // Then the message is added to Alice's timeline
        verifyThatTheTimeLineHasBeenUpdated(
                "Should have contained the expected message on the timeline for Alice",
                actualMessagesOnTimeLine,
                expectedMessagesOnTimeline("I love the weather today")
        );
    }

    /**
     * Scenario: Bob's messages are added to Bob's timeline
     */
    @Test
    public void givenBobsTimeLineIsEmpty_WhenNewMessagesArePassedToTheEngine_ThenMessagesAreAddedToBobsTimeLine() {
        // Given Bob's timeline is empty
        // And new messages like "Bob -> Damn! We lost!" and "Bob -> Good game though." are available
        // When the messages are passed to the engine for Bob
        List<String> actualMessagesOnTimeLine = processMessagesReceivedFor(
                "Bob",
                "Bob -> Damn! We lost!",
                "Bob -> Good game though.");

        // Then the messages are added to Bob's timeline, in the reverse order of entry
        verifyThatTheTimeLineHasBeenUpdated(
                "Should have contained the expected messages on the timeline for Bob",
                actualMessagesOnTimeLine,
                expectedMessagesOnTimeline(
                        "Good game though.",
                        "Damn! We lost!")
        );
    }

    private List<String> processMessagesReceivedFor(String userName,
                                                    String... userTypedMessages) {
        for (String eachUserTypedMessage: userTypedMessages) {
            twitterLikeEngine.executeCommand(eachUserTypedMessage);
        }

        return twitterLikeEngine.getTimelineFor(userName);
    }

    private List<String> expectedMessagesOnTimeline(String... expectedMessages) {
        List<String> expectedTimeLine = new ArrayList<>();

        for (String eachExpectedMessage: expectedMessages) {
            expectedTimeLine.add(eachExpectedMessage);
        }

        return  expectedTimeLine;
    }
    private void verifyThatTheTimeLineHasBeenUpdated(String reason,
                                                     List<String> actualMessages,
                                                     List<String> expectedMessages) {
        assertThat(reason, actualMessages, is(equalTo(expectedMessages)));
    }
}