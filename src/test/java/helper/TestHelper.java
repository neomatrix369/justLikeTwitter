package helper;

import clock.CentralSystemClock;
import com.github.approval.Approval;
import com.github.approval.reporters.Reporters;
import domain.UserTypedCommand;
import domain.User;
import domain.Users;

import java.util.Date;

import static org.mockito.Mockito.when;

public final class TestHelper {
    public static final String REPLAY_INPUT_FILE = "justLikeTwitterCommandsInputFile.txt";
    public static final String EXPECTED_OUTPUT_FILE = "justLikeTwitterCommandsExpectedOutputFile.txt";
    public static final String ACTUAL_OUTPUT_FILE = "justLikeTwitterCommandsActualOutputFile.txt";

    public static final boolean EXTRA_LINEFEED_NEEDED = true;
    public static final boolean EXTRA_LINEFEED_NOT_NEEDED = false;

    public static final Approval<String> APPROVER = Approval.of(String.class)
            .withReporter(Reporters.console())
            .build();

    private static final long THOUSAND_MILLISECONDS = 1000;
    private static final long ONE_SECOND = THOUSAND_MILLISECONDS;

    public static final long ZERO_MINUTES = 0;
    public static final long AFTER_ONE_MINUTE = 60 * THOUSAND_MILLISECONDS;
    public static final long AFTER_FIVE_MINUTES = 5 * AFTER_ONE_MINUTE;

    public static final long AFTER_TWO_SECONDS = 2 * ONE_SECOND;
    public static final long AFTER_FIFTEEN_SECONDS = 15 * ONE_SECOND;
    public static final long AFTER_FIFTY_SECONDS = 50 * ONE_SECOND;
    public static final long AFTER_THREE_MINUTES = 3 * AFTER_ONE_MINUTE;

    public static final User USER_CHARLIE = new User("Charlie");
    public static final User USER_ALICE = new User("Alice");
    public static final User USER_HARRY = new User("Harry");
    public static final User USER_BOB = new User("Bob");

    public static final String ANY_TEXT = "Some test to display on the console";

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

    public static Date simulateDelayUsing(Date currentDateTime,
                                   CentralSystemClock centralSystemClock,
                                   long timeInMilliSeconds) {
        Date newDateTime = new Date(currentDateTime.getTime() + timeInMilliSeconds);
        when(centralSystemClock.getCurrentDateTime()).thenReturn(newDateTime);
        return newDateTime;
    }
}