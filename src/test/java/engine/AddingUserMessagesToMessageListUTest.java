package engine;

import processors.DateTimeStampProvider;
import domain.TimeLineMessage;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Feature: Adding messages to user's message list
 */

public class AddingUserMessagesToMessageListUTest {

    private JustLikeTwitterEngine justLikeTwitterEngine;
    private DateTimeStampProvider dateTimeStampProvider = mock(DateTimeStampProvider.class);

    @Before
    public void setUp() {
        justLikeTwitterEngine = new JustLikeTwitterEngine(dateTimeStampProvider);
    }

    /**
     * Scenario: a user's message is added to the message list
     */
    @Test
    public void givenUsersMessageListIsEmpty_WhenANewMessageIsPassedToTheEngine_ThenMessageIsAddedToTheUsersMessageList() {
        // Given a user's message list is empty
        // And a new message "Alice -> I love the weather today" is available
        // When the message is passed to the engine for the user
        List<TimeLineMessage> actualMessagesToAdd = processMessagesReceivedFor("Alice", "Alice -> I love the weather today");

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
        List<TimeLineMessage> actualMessagesToAdd = processMessagesReceivedFor(
                "Bob",
                "Bob -> Damn! We lost!",
                "Bob -> Good game though.");

        // Then the messages are added to the user's message list, in the reverse order of entry
        verifyThatTheMessagesHaveBeenAdded(
                "Should have contained the expected messages in the message list for Bob",
                actualMessagesToAdd,
                expectedMessagesToAdd(
                        "Good game though.",
                        "Damn! We lost!")
        );
    }

    private List<TimeLineMessage> processMessagesReceivedFor(String userName,
                                                    String... userTypedMessages) {
        for (String eachUserTypedMessage: userTypedMessages) {
            justLikeTwitterEngine.executeCommand(eachUserTypedMessage);
        }

        return justLikeTwitterEngine.getMessagesFor(userName);
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