import engine.JustLikeTwitterEngine;
import interfaces.IOConsole;
import interfaces.JustLikeTwitter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostingMessagesUTest {

    private static final int ONCE_ONLY = 1;
    private static final int TWICE = 2;

    private static final String COMMAND_TYPED_BY_ALICE = "Alice -> I love the weather today";
    private static final String[] COMMANDS_TYPED_BY_BOB = new String[] {
            "Bob -> Damn! We lost!",
            "Bob -> Good game though."
    };

    private JustLikeTwitterEngine justLikeTwitterEngine;
    private IOConsole ioConsole;

    private JustLikeTwitter justLikeTwitter;

    @Before
    public void setUp() {
        justLikeTwitterEngine = mock(JustLikeTwitterEngine.class);
        ioConsole = mock(IOConsole.class);
    }

    /********************************************************************************************
     * Feature: Posting to a personal timeline
     *******************************************************************************************/

    /**
     * Scenario: Alice can publish a message to a personal timeline
     */
    @Test
    public void givenNoPostsExitsOnTimeLineWhenAliceEntersAMessageAtTheJustLikeTwitterPromptThenMessageIsAddedToTimeLine() throws IOException {
        // Given Alice is at the JustLikeTwitter command prompt ">"
        // And Alice's timeline is empty
        setupJustLikeTwitterPromptUsing(COMMAND_TYPED_BY_ALICE);

        // When Alice types "Alice -> I love the weather today" at the prompt
        justLikeTwitter.run(ONCE_ONLY);

        // Then action is taken to add the message to Alice's timeline
        verifyThatActionIsTakenToRecordTheMessage(justLikeTwitterEngine, COMMAND_TYPED_BY_ALICE);
    }

    /**
     * Scenario: Bob can publish messages to a personal timeline
     */
    @Test
    public void givenNoPostsExitsOnTimeLineWhenBobEntersMessagesAtTheJustLikeTwitterPromptThenTheMessagesAreAddedToTimeLine() throws IOException {
        // Given Bob is at the JustLikeTwitter command prompt ">"
        // And Bob's timeline is empty
        setupJustLikeTwitterPromptUsing(COMMANDS_TYPED_BY_BOB);

        // When Bob types "Bob -> Damn! We lost!" at the prompt
        // And then types "Bob -> Good game though."  at the prompt
        justLikeTwitter.run(TWICE);

        // Then action is taken to add the messages to Bob's timeline
        verifyThatActionIsTakenToRecordTheMessage(justLikeTwitterEngine, COMMANDS_TYPED_BY_BOB);
    }

    private void verifyThatActionIsTakenToRecordTheMessage(
            JustLikeTwitterEngine justLikeTwitterEngine,
            String... userTypedCommands) {
        for (String userTypedCommand: userTypedCommands) {
            verify(justLikeTwitterEngine).executeCommand(eq(userTypedCommand));
        }
    }

    private void setupJustLikeTwitterPromptUsing(String... userTypedCommands) throws IOException {
        List<String> userTypedCommandsList = Arrays.asList(userTypedCommands);

        for (String userTypedCommand: userTypedCommands) {
            when(ioConsole.showPrompt())
                    .thenAnswer(
                            AdditionalAnswers.returnsElementsOf(userTypedCommandsList));
        }

        justLikeTwitter = new JustLikeTwitter(justLikeTwitterEngine, ioConsole);
    }
}