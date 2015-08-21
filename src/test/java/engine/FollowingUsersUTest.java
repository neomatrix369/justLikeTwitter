package engine;

import clock.CentralSystemClock;
import domain.FollowsList;
import domain.MessageStore;
import domain.Users;
import org.junit.Before;
import org.junit.Test;

import static helper.TestHelper.CHARLIE_FOLLOWS_ALICE;
import static helper.TestHelper.CHARLIE_FOLLOWS_BOB;
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
        userTypesAtThePrompt(CHARLIE_FOLLOWS_ALICE);
        Users actualFollowsList = followsList.getFollowsFor(USER_CHARLIE);

                // Then Alice is added to Charlie's follows list
        verifyThatTheFollowsListMatch(
                "Alice should have been added to Charlie's follows list",
                actualFollowsList,
                new Users(EXPECTED_FOLLOWS_LIST[0]));
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
        userTypesAtThePrompt(CHARLIE_FOLLOWS_ALICE);
        userTypesAtThePrompt(CHARLIE_FOLLOWS_BOB);
        Users actualFollowsList = followsList.getFollowsFor(USER_CHARLIE);

        // Then Alice and Bob are added to Charlie's follows list
        verifyThatTheFollowsListMatch(
                "Alice and Bob should have been added to Charlie's follows list",
                actualFollowsList,
                new Users(EXPECTED_FOLLOWS_LIST[1])
        );
    }

    private void userTypesAtThePrompt(String userTypedCommand) {
        justLikeTwitterEngine.executeCommand(userTypedCommand);
    }

    private void verifyThatTheFollowsListMatch(String reason,
                                               Users actualFollowsList,
                                               Users expectedFollowsList) {
        assertThat(
                reason,
                actualFollowsList,
                is(equalTo(expectedFollowsList)));
    }
}