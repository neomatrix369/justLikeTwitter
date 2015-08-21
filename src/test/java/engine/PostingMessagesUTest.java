package engine;

import clock.CentralSystemClock;
import elements.MessageStore;
import elements.TimeLineMessage;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static helper.TestHelper.COMMANDS_TYPED_BY_ALICE;
import static helper.TestHelper.COMMANDS_TYPED_BY_BOB;
import static helper.TestHelper.USER_ALICE;
import static helper.TestHelper.USER_BOB;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Feature: Adding messages to user's message list
 */

public class PostingMessagesUTest {

    private final CentralSystemClock centralSystemClock = new CentralSystemClock();
    private JustLikeTwitterEngine justLikeTwitterEngine;
    private MessageStore messageStore = new MessageStore();

    @Before
    public void setUp() {
        justLikeTwitterEngine = new JustLikeTwitterEngine(messageStore, centralSystemClock);
    }

    /**
     * Scenario: a user's message is added to the message list
     */
    @Test
    public void givenUsersMessageListIsEmpty_WhenANewMessageIsPassedToTheEngine_ThenMessageIsAddedToTheUsersMessageList() {
        // Given a user's message list is empty
        // And a new message "Alice -> I love the weather today" is available
        // When the message is passed to the engine for the user
        userTypesAtThePrompt(COMMANDS_TYPED_BY_ALICE[0]);
        List<TimeLineMessage> actualMessagesToAdd = messageStore.getMessagesFor(USER_ALICE);

        // Then the message is added to the user's message list
        verifyThatTheMessagesHaveBeenAdded(
                "Should have contained the expected message in the message list for Alice",
                actualMessagesToAdd,
                expectedMessagesToAdd("I love the weather today")
        );
    }

    /**
     * Scenario: multiple messages from a user are added to the message list
     */
    @Test
    public void givenUsersMessageListIsEmpty_WhenNewMessagesArePassedToTheEngine_ThenMessagesAreAddedToTheUsersMessageList() {
        // Given user's message list is empty
        // And new messages like "Bob -> Damn! We lost!" and "Bob -> Good game though." are available
        // When the messages are passed to the engine for the user
        userTypesAtThePrompt(COMMANDS_TYPED_BY_BOB[0]);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_BOB[1]);
        List<TimeLineMessage> actualMessagesToAdd = messageStore.getMessagesFor(USER_BOB);

        // Then the messages are added to the user's message list, in the reverse order of entry
        verifyThatTheMessagesHaveBeenAdded(
                "Should have contained the expected messages in the message list for Bob",
                actualMessagesToAdd,
                expectedMessagesToAdd(
                        "Good game though.",
                        "Damn! We lost!")
        );
    }

    private String userTypesAtThePrompt(String userTypedCommand) {
        return justLikeTwitterEngine.executeCommand(userTypedCommand);
    }
    private List<String> expectedMessagesToAdd(String... expectedMessages) {
        return Arrays.asList(expectedMessages);
    }

    private void verifyThatTheMessagesHaveBeenAdded(String reason,
                                                    List<TimeLineMessage> actualMessages,
                                                    List<String> expectedMessages) {
        assertThat(reason, actualMessages.size(), is(equalTo(expectedMessages.size())));
    }
}