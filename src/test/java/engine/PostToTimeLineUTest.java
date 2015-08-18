package engine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *Feature: Posting to a personal timeline
 */

public class PostToTimeLineUTest {

    private TwitterLikeEngine twitterLikeEngine;

    @Before
    public void setUp() {
        twitterLikeEngine = new TwitterLikeEngine();
    }

    /**
     * Scenario: Alice can publish a message to a personal timeline
     */
    @Test
    public void givenNoPostsExitsOnTimeLineWhenAliceEntersAMessageAtTheTwitterLikePromptThenMessageIsAddedToTimeLine() {
        // Given Alice is at the TwitterLike command prompt ">"
        // And Alice's timeline is empty
        // When Alice types "Alice -> I love the weather today" at the prompt
        List<String> actualMessagesOnTimeLine = executeCommandsAtThePrompt("Alice", "Alice -> I love the weather today");

        // Then the message is added to Alice's timeline
        verifyThatTheTimeLineHasBeenUpdated(
                "Should have contained the expected message on the timeline for Alice",
                actualMessagesOnTimeLine,
                expectedMessagesOnTimeline("I love the weather today")
        );
    }

    /**
     * Scenario: Bob can publish multiple messages to a personal timeline
     */
    @Test
    public void givenNoPostsExitsOnTimeLineWhenBobEntersMessagesAtTheTwitterLikePromptThenTheMessagesAreAddedToTimeLine() {
        // Given Bob is at the TwitterLike command prompt ">"
        // And Bob's timeline is empty
        // When Bob types "Bob -> Damn! We lost!" at the prompt
        // And then types "Bob -> Good game though."  at the prompt
        List<String> actualMessagesOnTimeLine = executeCommandsAtThePrompt(
                "Bob",
                "Bob -> Damn! We lost!",
                "Bob -> Good game though.");

        // Then the messages are added to Bob's timeline, in reverse order
        verifyThatTheTimeLineHasBeenUpdated(
                "Should have contained the expected messages on the timeline for Bob",
                actualMessagesOnTimeLine,
                expectedMessagesOnTimeline(
                        "Good game though.",
                        "Damn! We lost!")
        );
    }

    private List<String> executeCommandsAtThePrompt(String userName,
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