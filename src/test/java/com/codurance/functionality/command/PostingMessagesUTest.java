package com.codurance.functionality.command;

import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.FollowsList;
import com.codurance.domain.message.MessagePosted;
import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.JustLikeTwitterEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.codurance.helper.TestHelper.ALICE_POSTS_A_MESSAGE;
import static com.codurance.helper.TestHelper.BOB_POSTS_TWO_MESSAGES;
import static com.codurance.helper.TestHelper.USER_ALICE;
import static com.codurance.helper.TestHelper.USER_BOB;
import static com.codurance.helper.TestHelper.atThePrompt;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Feature: Adding messages to a user's message list
 */

public class PostingMessagesUTest {

    private JustLikeTwitterEngine justLikeTwitterEngine;
    private final MessageStore messageStore = new MessageStore();

    private final CentralSystemClock centralSystemClock = new CentralSystemClock();

    @Before
    public void setUp() {
        FollowsList followsList = new FollowsList();
        justLikeTwitterEngine = new JustLikeTwitterEngine(
                messageStore,
                followsList,
                centralSystemClock);
    }

    /**
     * Scenario: a user's message is added to the message list
     */
    @Test
    public void givenAlicesMessageListIsEmpty_WhenANewMessageIsPassedToTheEngine_ThenMessageIsAddedToAlicesMessageList() {
        // Given a user's message list is empty
        // And a new message "Alice -> I love the weather today" is available
        // When the message is passed to the engine for the user
        atThePrompt(justLikeTwitterEngine, ALICE_POSTS_A_MESSAGE);
        List<MessagePosted> actualMessagesToAdd = messageStore.getMessagesFor(USER_ALICE);

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
    public void givenBobsMessageListIsEmpty_WhenNewMessagesArePassedToTheEngine_ThenMessagesAreAddedToBobsMessageList() {
        // Given user's message list is empty
        // And new messages like "Bob -> Damn! We lost!" and "Bob -> Good game though." are available
        // When the messages are passed to the engine for the user
        atThePrompt(justLikeTwitterEngine, BOB_POSTS_TWO_MESSAGES[0]);
        atThePrompt(justLikeTwitterEngine, BOB_POSTS_TWO_MESSAGES[1]);
        List<MessagePosted> actualMessagesToAdd = messageStore.getMessagesFor(USER_BOB);

        // Then the messages are added to the user's message list, in the reverse order of entry
        verifyThatTheMessagesHaveBeenAdded(
                "Should have contained the expected messages in the message list for Bob",
                actualMessagesToAdd,
                expectedMessagesToAdd(
                        "Good game though.",
                        "Damn! We lost!")
        );
    }

    private List<String> expectedMessagesToAdd(String... expectedMessages) {
        return Arrays.asList(expectedMessages);
    }

    private void verifyThatTheMessagesHaveBeenAdded(String reason,
                                                    List<MessagePosted> actualMessages,
                                                    List<String> expectedMessages) {
        assertThat(
                reason,
                actualMessages.size(),
                is(equalTo(expectedMessages.size())));
    }
}