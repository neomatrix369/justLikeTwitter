package helper;

import com.github.approval.Approval;
import com.github.approval.reporters.Reporters;
import domain.User;
import domain.command.UserTypedCommand;
import domain.Users;
import functionality.JustLikeTwitterEngine;

public final class TestHelper {
    public static final String REPLAY_INPUT_FILE = "justLikeTwitterCommandsInputFile.txt";
    public static final String EXPECTED_OUTPUT_FILE = "justLikeTwitterCommandsExpectedOutputFile.txt";
    public static final String ACTUAL_OUTPUT_FILE = "justLikeTwitterCommandsActualOutputFile.txt";

    public static final boolean EXTRA_LINEFEED_NEEDED = true;
    public static final boolean EXTRA_LINEFEED_NOT_NEEDED = false;

    public static final Approval<String> APPROVER = Approval.of(String.class)
            .withReporter(Reporters.console())
            .build();

    public static final User USER_CHARLIE = new User("Charlie");
    public static final User USER_ALICE = new User("Alice");
    public static final User USER_HARRY = new User("Harry");
    public static final User USER_BOB = new User("Bob");

    public static final String ANY_TEXT = "Some test to display on the console";
    public static final String ASSERT_REASON_FOR_FIELD_VALUE_NOT_RETURNED = "Field value of '%s' should have been returned";

    public static final String SPACE_DELIMETER = " ";

    public static final UserTypedCommand ALICE_POSTS_A_MESSAGE = new UserTypedCommand("Alice -> I love the weather today");
    public static final UserTypedCommand HARRY_POSTS_A_MESSAGE = new UserTypedCommand("Harry -> I like this idea");

    public static final UserTypedCommand[] BOB_POSTS_TWO_MESSAGES = new UserTypedCommand[]{
            new UserTypedCommand("Bob -> Damn! We lost!"),
            new UserTypedCommand("Bob -> Good game though.")
    };

    public static final UserTypedCommand CHARLIE_POSTS_A_MESSAGE =
            new UserTypedCommand("Charlie -> I'm in New York today! Anyone wants to have a coffee?");

    public static final UserTypedCommand CHARLIE_FOLLOWS_ALICE = new UserTypedCommand("Charlie follows Alice");
    public static final UserTypedCommand CHARLIE_FOLLOWS_BOB = new UserTypedCommand("Charlie follows Bob");
    public static final UserTypedCommand CHARLIE_REQUESTS_WALL = new UserTypedCommand("Charlie wall");

    public static final Users[] EXPECTED_FOLLOWS_LIST = {
            new Users(USER_ALICE),
            new Users(USER_ALICE, USER_BOB)
    };

    private TestHelper() {
    }

    public static void userTypesAtThePrompt(JustLikeTwitterEngine justLikeTwitterEngine, UserTypedCommand userTypedCommand) {
        justLikeTwitterEngine.executeCommand(userTypedCommand);
    }

    public static String at(String timePart) {
        return timePart;
    }

    public static String on(String datePart) {
        return datePart;
    }

}