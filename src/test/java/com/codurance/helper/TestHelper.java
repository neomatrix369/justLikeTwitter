package com.codurance.helper;

import com.codurance.domain.Users;
import com.codurance.functionality.JustLikeTwitterEngine;
import com.codurance.domain.User;
import com.codurance.command.UserTypedCommand;

public final class TestHelper {
    public static final String REPLAY_INPUT_FILE = "../../justLikeTwitterCommandsInputFile.txt";
    public static final String ACTUAL_OUTPUT_FILE = "../../justLikeTwitterCommandsActualOutputFile.txt";

    public static final User USER_CHARLIE = new User("Charlie");
    public static final User USER_ALICE = new User("Alice");
    public static final User USER_BOB = new User("Bob");
    private static final User USER_HARRY = new User("Harry");

    public static final String ANY_TEXT = "Some test to display on the console";
    public static final String ASSERT_REASON_FOR_FIELD_VALUE_NOT_RETURNED = "Field value of '%s' should have been returned";

    public static final String SPACE_DELIMITER = " ";

    public static final UserTypedCommand ALICE_POSTS_A_MESSAGE = new UserTypedCommand("Alice -> I love the weather today");
    public static final UserTypedCommand HARRY_POSTS_A_MESSAGE = new UserTypedCommand("Harry -> I like this idea");

    public static final UserTypedCommand[] BOB_POSTS_TWO_MESSAGES = new UserTypedCommand[]{
            new UserTypedCommand("Bob -> Damn! We lost!"),
            new UserTypedCommand("Bob -> Good game though.")
    };

    public static final UserTypedCommand CHARLIE_POSTS_A_MESSAGE =
            new UserTypedCommand("Charlie -> I'm in New York today! Anyone wants to have a coffee?");

    public static final UserTypedCommand HARRY_READS_POSTS = new UserTypedCommand(USER_HARRY);
    public static final UserTypedCommand ALICE_READS_POSTS = new UserTypedCommand(USER_ALICE);
    public static final UserTypedCommand BOB_READS_POSTS = new UserTypedCommand(USER_BOB);

    public static final UserTypedCommand CHARLIE_FOLLOWS_ALICE = new UserTypedCommand("Charlie follows Alice");
    public static final UserTypedCommand CHARLIE_FOLLOWS_BOB = new UserTypedCommand("Charlie follows Bob");
    public static final UserTypedCommand CHARLIE_REQUESTS_WALL = new UserTypedCommand("Charlie wall");

    public static final Users[] EXPECTED_FOLLOWS_LIST = {
            new Users(USER_CHARLIE, USER_ALICE),
            new Users(USER_CHARLIE, USER_ALICE, USER_BOB)
    };

    private TestHelper() {
    }

    public static void atThePrompt(JustLikeTwitterEngine justLikeTwitterEngine,
                                   UserTypedCommand theUserHasTyped) {
        justLikeTwitterEngine.executeCommand(theUserHasTyped);
    }

    public static String at(String timePart) {
        return timePart;
    }

    public static String on(String datePart) {
        return datePart;
    }
}