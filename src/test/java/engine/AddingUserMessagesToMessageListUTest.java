package engine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Feature: Adding messages to user's message list
 */

public class AddingUserMessagesToMessageListUTest {

    private TwitterLikeEngine twitterLikeEngine;

    @Before
    public void setUp() {
        twitterLikeEngine = new TwitterLikeEngine();
    }

    /**
     * Scenario: a user's message is added to the message list
     */
    @Test
    public void givenUsersMessageListIsEmpty_WhenANewMessageIsPassedToTheEngine_ThenMessageIsAddedToTheUsersMessageList() {
        // Given a user's message list is empty
        // And a new message "Alice -> I love the weather today" is available
        // When the message is passed to the engine for the user
        List<String> actualMessagesToAdd = processMessagesReceivedFor("Alice", "Alice -> I love the weather today");

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
        List<String> actualMessagesToAdd = processMessagesReceivedFor(
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

    private List<String> processMessagesReceivedFor(String userName,
                                                    String... userTypedMessages) {
        for (String eachUserTypedMessage: userTypedMessages) {
            twitterLikeEngine.executeCommand(eachUserTypedMessage);
        }

        return twitterLikeEngine.getMessagesFor(userName);
    }

    private List<String> expectedMessagesToAdd(String... expectedMessages) {
        List<String> expectedMessagesList = new ArrayList<>();

        for (String eachExpectedMessage: expectedMessages) {
            expectedMessagesList.add(eachExpectedMessage);
        }

        return  expectedMessagesList;
    }
    private void verifyThatTheMessagesHaveBeenAdded(String reason,
                                                    List<String> actualMessages,
                                                    List<String> expectedMessages) {
        assertThat(reason, actualMessages, is(equalTo(expectedMessages)));
    }
}