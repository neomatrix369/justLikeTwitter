package engine;

import org.junit.Before;
import org.junit.Test;
import processors.DateTimeCentral;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Feature: One user following another user, creating a follows list
 */

public class FollowingUsersUTest {

    private JustLikeTwitterEngine justLikeTwitterEngine;

    @Before
    public void setUp() {
        DateTimeCentral dateTimeCentral = mock(DateTimeCentral.class);
        justLikeTwitterEngine = new JustLikeTwitterEngine(dateTimeCentral);
    }

    /**
     * Scenario: Charlie can add to Alice to his follows list
     */
    @Test
    public void givenAliceAndCharlieExist_whenCharlieFollowsAlice_thenAliceIsAddedToCharliesFollowsList() {
        //  Given Charlie is at the JustLikeTwitter command prompt ">"
        //  And Alice exists
        //  When he enters "Charlie follows Alice" at the prompt
        justLikeTwitterEngine.executeCommand("Charlie follows Alice");
        List<String> actualFollowsList = justLikeTwitterEngine.getFollowsListFor("Charlie");

        // Then Alice is added to Charlie's follows list
        List<String> expectedFollowsList = Arrays.asList(new String[]{ "Alice" });
        assertThat("Alice should have been added to Charlie's follows list",
                actualFollowsList,
                is(equalTo(expectedFollowsList)));
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
        justLikeTwitterEngine.executeCommand("Charlie follows Alice");
        justLikeTwitterEngine.executeCommand("Charlie follows Bob");
        List<String> actualFollowsList = justLikeTwitterEngine.getFollowsListFor("Charlie");

        // Then Alice and Bob are added to Charlie's follows list
        List<String> expectedFollowsList = Arrays.asList(new String[]{ "Alice", "Bob" });
        assertThat("Alice and Bob should have been added to Charlie's follows list",
                actualFollowsList,
                is(equalTo(expectedFollowsList)));
    }
}