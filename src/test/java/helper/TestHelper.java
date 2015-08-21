package helper;

import clock.CentralSystemClock;
import com.github.approval.Approval;
import com.github.approval.reporters.Reporters;

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

    public static final String USER_CHARLIE = "Charlie";
    public static final String USER_ALICE = "Alice";
    public static final String USER_HARRY = "Harry";
    public static final String USER_BOB = "Bob";

    public static final String ANY_TEXT = "Some test to display on the console";

    public static final String ALICE_POSTS_A_MESSAGE = "Alice -> I love the weather today";

    public static final String HARRY_POSTS_A_MESSAGE = "Harry -> I like this idea";

    public static final String[] BOB_POSTS_TWO_MESSAGES = new String[]{
            "Bob -> Damn! We lost!",
            "Bob -> Good game though."
    };

    public static final String CHARLIE_POSTS_A_MESSAGE =
            "Charlie -> I'm in New York today! Anyone wants to have a coffee?";

    public static final String CHARLIE_FOLLOWS_ALICE = "Charlie follows Alice";
    public static final String CHARLIE_FOLLOWS_BOB = "Charlie follows Bob";
    public static final String CHARLIE_REQUESTS_WALL = "Charlie wall";

    public static final String[][] EXPECTED_FOLLOWS_LIST = {
            new String[]{USER_ALICE},
            new String[]{USER_ALICE, USER_BOB}
    };

    private TestHelper() {}

    public static Date simulateDelayUsing(Date currentDateTime,
                                   CentralSystemClock centralSystemClock,
                                   long timeInMilliSeconds) {
        Date newDateTime = new Date(currentDateTime.getTime() + timeInMilliSeconds);
        when(centralSystemClock.getCurrentDateTime()).thenReturn(newDateTime);
        return newDateTime;
    }
}