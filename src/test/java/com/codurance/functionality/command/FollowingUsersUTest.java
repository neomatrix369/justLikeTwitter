package com.codurance.functionality.command;

import com.codurance.domain.Users;
import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.JustLikeTwitterEngine;
import com.codurance.clock.CentralSystemClock;
import com.codurance.domain.FollowsList;
import org.junit.Before;
import org.junit.Test;

import static com.codurance.helper.TestHelper.CHARLIE_FOLLOWS_ALICE;
import static com.codurance.helper.TestHelper.CHARLIE_FOLLOWS_BOB;
import static com.codurance.helper.TestHelper.EXPECTED_FOLLOWS_LIST;
import static com.codurance.helper.TestHelper.USER_CHARLIE;
import static com.codurance.helper.TestHelper.atThePrompt;
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
        justLikeTwitterEngine = new JustLikeTwitterEngine(centralSystemClock, messageStore, followsList);
    }

    /**
     * Scenario: Charlie can add to Alice to his follows list
     */
    @Test
    public void givenAliceAndCharlieExist_whenCharlieFollowsAlice_thenAliceIsAddedToCharliesFollowsList() {
        //  Given Charlie is at the JustLikeTwitter command prompt ">"
        //  And Alice exists
        //  When he enters "Charlie follows Alice" at the prompt
        atThePrompt(justLikeTwitterEngine, CHARLIE_FOLLOWS_ALICE);
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
        atThePrompt(justLikeTwitterEngine, CHARLIE_FOLLOWS_ALICE);
        atThePrompt(justLikeTwitterEngine, CHARLIE_FOLLOWS_BOB);
        Users actualFollowsList = followsList.getFollowsFor(USER_CHARLIE);

        // Then Alice and Bob are added to Charlie's follows list
        verifyThatTheFollowsListMatch(
                "Alice and Bob should have been added to Charlie's follows list",
                actualFollowsList,
                new Users(EXPECTED_FOLLOWS_LIST[1])
        );
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