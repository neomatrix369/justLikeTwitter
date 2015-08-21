package engine;

import clock.CentralSystemClock;
import elements.FollowsList;
import elements.MessageStore;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static helper.TestHelper.COMMANDS_TYPED_BY_CHARLIE;
import static helper.TestHelper.EXPECTED_FOLLOWS_LIST;
import static helper.TestHelper.USER_CHARLIE;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Feature: One user following another user, creating a follows list
 */

public class FollowingUsersUTest {

    private JustLikeTwitterEngine justLikeTwitterEngine;
    private FollowsList followsList;

    @Before
    public void setUp() {
        CentralSystemClock centralSystemClock = mock(CentralSystemClock.class);
        MessageStore messageStore = new MessageStore();
        followsList = new FollowsList();
        justLikeTwitterEngine = new JustLikeTwitterEngine(messageStore, followsList, centralSystemClock);
    }

    /**
     * Scenario: Charlie can add to Alice to his follows list
     */
    @Test
    public void givenAliceAndCharlieExist_whenCharlieFollowsAlice_thenAliceIsAddedToCharliesFollowsList() {
        //  Given Charlie is at the JustLikeTwitter command prompt ">"
        //  And Alice exists
        //  When he enters "Charlie follows Alice" at the prompt
        userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[1]);
        List<String> actualFollowsList = followsList.getFor(USER_CHARLIE);

                // Then Alice is added to Charlie's follows list
        verifyThatTheFollowsListMatch(
                "Alice should have been added to Charlie's follows list",
                actualFollowsList,
                Arrays.asList(EXPECTED_FOLLOWS_LIST[0]));
    }

    /**
     * Scenario: Charlie can add Alice and Bob to his follows list
     */
    @Test
    public void givenAliceBobAndCharlieExist_whenCharlieFollowsAliceAndBon_thenBothAreAddedToCharliesFollowsList() {
        // Given Charlie is at the JustLikeTwitter command prompt ">"
        // And Alice exists
        // And Bob exists
        // When he enters "Charlie follows Alice" at the prompt
        // And he enters "Charlie follows Bob" at the prompt
        userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[1]);
        userTypesAtThePrompt(COMMANDS_TYPED_BY_CHARLIE[2]);
        List<String> actualFollowsList = followsList.getFor(USER_CHARLIE);

        // Then Alice and Bob are added to Charlie's follows list
        verifyThatTheFollowsListMatch(
                "Alice and Bob should have been added to Charlie's follows list",
                actualFollowsList,
                Arrays.asList(EXPECTED_FOLLOWS_LIST[1])
        );
    }

    private String userTypesAtThePrompt(String userTypedCommand) {
        return justLikeTwitterEngine.executeCommand(userTypedCommand);
    }

    private void verifyThatTheFollowsListMatch(String reason,
                                               List<String> actualFollowsList,
                                               List<String> expectedFollowsList) {
        assertThat(
                reason,
                actualFollowsList,
                is(equalTo(expectedFollowsList)));
    }
}