import engine.JustLikeTwitterEngine;
import interfaces.ConsoleUI;
import interfaces.JustLikeTwitter;
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

    private JustLikeTwitterEngine justLikeTwitterEngine;
    private ConsoleUI consoleUI;

    private JustLikeTwitter justLikeTwitter;

    @Before
    public void setUp() {
        justLikeTwitterEngine = mock(JustLikeTwitterEngine.class);
        consoleUI = mock(ConsoleUI.class);
    }

    @After
    public void tearDown() {
        reset(justLikeTwitterEngine);
    }

    /**
     * Scenario: Alice can publish a message to a personal timeline
     */
    @Test
    public void givenNoPostsExitsOnTimeLineWhenAliceEntersAMessageAtTheJustLikeTwitterPromptThenMessageIsAddedToTimeLine() throws IOException {
        // Given Alice is at the JustLikeTwitter command prompt ">"
        // And Alice's timeline is empty
        setupJustLikeTwitterPromptUsing(commandTypedByAlice);

        // When Alice types "Alice -> I love the weather today" at the prompt
        justLikeTwitter.run(ONCE_ONLY);

        // Then action is taken to add the message to Alice's timeline
        verifyThatActionIsTakenToRecordTheMessage(justLikeTwitterEngine, commandTypedByAlice);
    }

    /**
     * Scenario: Bob can publish messages to a personal timeline
     */
    @Test
    public void givenNoPostsExitsOnTimeLineWhenBobEntersMessagesAtTheJustLikeTwitterPromptThenTheMessagesAreAddedToTimeLine() throws IOException {
        // Given Bob is at the JustLikeTwitter command prompt ">"
        // And Bob's timeline is empty
        setupJustLikeTwitterPromptUsing(commandsTypedByBob);

        // When Bob types "Bob -> Damn! We lost!" at the prompt
        // And then types "Bob -> Good game though."  at the prompt
        justLikeTwitter.run(TWICE);

        // Then action is taken to add the messages to Bob's timeline
        verifyThatActionIsTakenToRecordTheMessage(
                justLikeTwitterEngine,
                commandsTypedByBob);
    }

    private void setupJustLikeTwitterPromptUsing(String... userTypedCommands) throws IOException {
        List<String> userTypedCommandsList = Arrays.asList(userTypedCommands);

        for (String userTypedCommand: userTypedCommands) {
            when(consoleUI.showPrompt())
                    .thenAnswer(
                            AdditionalAnswers.returnsElementsOf(userTypedCommandsList));
        }

        justLikeTwitter = new JustLikeTwitter(justLikeTwitterEngine, consoleUI);
    }

    private void verifyThatActionIsTakenToRecordTheMessage(
            JustLikeTwitterEngine justLikeTwitterEngine,
            String... userTypedCommands) {
        for (String userTypedCommand: userTypedCommands) {
            verify(justLikeTwitterEngine).executeCommand(eq(userTypedCommand));
        }
    }
}