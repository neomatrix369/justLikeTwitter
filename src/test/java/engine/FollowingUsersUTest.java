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

    private static final String USER_CHARLIE = "Charlie";

    private static final String[] COMMANDS_BY_CHARLIE = new String [] {
            "Charlie follows Alice",
            "Charlie follows Bob"
    };

    private static final String[][] EXPECTED_FOLLOWS_LIST = {
            new String[]{"Alice"},
            new String[]{"Alice", "Bob"}
    };


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
        justLikeTwitterEngine.executeCommand(COMMANDS_BY_CHARLIE[0]);
        List<String> actualFollowsList = justLikeTwitterEngine.getFollowsListFor(USER_CHARLIE);

        // Then Alice is added to Charlie's follows list
        List<String> expectedFollowsList = Arrays.asList(EXPECTED_FOLLOWS_LIST[0]);
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
        justLikeTwitterEngine.executeCommand(COMMANDS_BY_CHARLIE[0]);
        justLikeTwitterEngine.executeCommand(COMMANDS_BY_CHARLIE[1]);
        List<String> actualFollowsList = justLikeTwitterEngine.getFollowsListFor(USER_CHARLIE);

        // Then Alice and Bob are added to Charlie's follows list
        List<String> expectedFollowsList = Arrays.asList(EXPECTED_FOLLOWS_LIST[1]);
        assertThat("Alice and Bob should have been added to Charlie's follows list",
                actualFollowsList,
                is(equalTo(expectedFollowsList)));
    }
}