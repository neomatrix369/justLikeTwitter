import engine.TwitterLikeEngine;
import interfaces.ConsoleUI;
import interfaces.TwitterLike;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Feature: Posting to a personal timeline
 */

public class FullLifeCycleAcceptanceTest {

    private static final int ONCE_ONLY = 1;
    private static final int TWICE = 2;

    private String commandTypedByAlice = "Alice -> I love the weather today";
    private String[] commandsTypedByBob = new String[] {
            "Bob -> Damn! We lost!",
            "Bob -> Good game though."
    };

    private TwitterLikeEngine twitterLikeEngine;
    private ConsoleUI consoleUI;

    private TwitterLike twitterLike;

    @Before
    public void setUp() {
        twitterLikeEngine = mock(TwitterLikeEngine.class);
        consoleUI = mock(ConsoleUI.class);
    }

    @After
    public void tearDown() {
        reset(twitterLikeEngine);
    }

    /**
     * Scenario: Alice can publish a message to a personal timeline
     */
    @Test
    public void givenNoPostsExitsOnTimeLineWhenAliceEntersAMessageAtTheTwitterLikePromptThenMessageIsAddedToTimeLine() throws IOException {
        // Given Alice is at the TwitterLike command prompt ">"
        // And Alice's timeline is empty
        setupTwitterLikeUsing(commandTypedByAlice);

        // When Alice types "Alice -> I love the weather today" at the prompt
        twitterLike.run(ONCE_ONLY);

        // Then action is taken to add the message to Alice's timeline
        verifyThatActionIsTakenToRecordTheMessage(twitterLikeEngine, commandTypedByAlice);
    }

    /**
     * Scenario: Bob can publish messages to a personal timeline
     */
    @Test
    public void givenNoPostsExitsOnTimeLineWhenBobEntersMessagesAtTheTwitterLikePromptThenTheMessagesAreAddedToTimeLine() throws IOException {
        // Given Bob is at the TwitterLike command prompt ">"
        // And Bob's timeline is empty
        setupTwitterLikeUsing(commandsTypedByBob);

        // When Bob types "Bob -> Damn! We lost!" at the prompt
        // And then types "Bob -> Good game though."  at the prompt
        twitterLike.run(TWICE);

        // Then action is taken to add the messages to Bob's timeline
        verifyThatActionIsTakenToRecordTheMessage(
                twitterLikeEngine,
                commandsTypedByBob);
    }

    private void setupTwitterLikeUsing(String... userTypedCommands) throws IOException {
        List<String> userTypedCommandsList = Arrays.asList(userTypedCommands);
        for (String userTypedCommand: userTypedCommands) {
            when(consoleUI.showPrompt())
                    .thenAnswer(
                            AdditionalAnswers.returnsElementsOf(userTypedCommandsList));
        }

        twitterLike = new TwitterLike(twitterLikeEngine, consoleUI);
    }

    private void verifyThatActionIsTakenToRecordTheMessage(
            TwitterLikeEngine twitterLikeEngine,
            String... userTypedCommands) {
        for (String userTypedCommand: userTypedCommands) {
            verify(twitterLikeEngine).executeCommand(eq(userTypedCommand));
        }
    }
}