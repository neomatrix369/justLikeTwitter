package helper;

import clock.CentralSystemClock;
import com.github.approval.Approval;
import com.github.approval.reporters.Reporters;

import java.util.Date;

import static org.mockito.Mockito.when;

public class TestHelper {
    public static final String REPLAY_INPUT_FILE = "justLikeTwitterCommandsInputFile.txt";
    public static final String DATES_FOR_MESSAGES_INPUT_FILE = "justLikeTwitterCommandsInputFileMessageDateTimes.txt";
    public static final String EXPECTED_OUTPUT_FILE = "justLikeTwitterCommandsExpectedOutputFile.txt";
    public static final String ACTUAL_OUTPUT_FILE = "justLikeTwitterCommandsActualOutputFile.txt";

    public static final boolean EXTRA_LINEFEED_NEEDED = true;
    public static final boolean EXTRA_LINEFEED_NOT_NEEDED = false;

    public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy hh:mm:ss";

    public static final Approval<String> APPROVER = Approval.of(String.class)
            .withReporter(Reporters.console())
            .build();

    public static final long ZERO_MINUTES = 0;
    public static final long THOUSAND_MILLISECONDS = 1000;
    public static final long AFTER_ONE_MINUTE = 60 * THOUSAND_MILLISECONDS;
    public static final long AFTER_FIVE_MINUTES = 5 * AFTER_ONE_MINUTE;
    public static final long ONE_SECOND = THOUSAND_MILLISECONDS;
    public static final long AFTER_TWO_SECONDS = 2 * ONE_SECOND;
    public static final long AFTER_FIFTEEN_SECONDS = 15 * ONE_SECOND;
    public static final long AFTER_FIFTY_SECONDS = 50 * ONE_SECOND;
    public static final long AFTER_THREE_MINUTES = 3 * AFTER_ONE_MINUTE;

    public static final String USER_CHARLIE = "Charlie";
    public static final String USER_ALICE = "Alice";
    public static final String USER_HARRY = "Harry";
    public static final String USER_BOB = "Bob";

    public static final String ANY_TEXT = "Some test to display on the console";

    public static final String[] COMMANDS_TYPED_BY_ALICE = new String[]{
            "Alice -> I love the weather today",
    };

    public static final String[] COMMAND_TYPED_BY_HARRY = new String[] {
            "Harry -> I like this idea"
    };

    public static final String[] COMMANDS_TYPED_BY_BOB = new String[]{
            "Bob -> Damn! We lost!",
            "Bob -> Good game though."
    };

    public static final String[] COMMANDS_TYPED_BY_CHARLIE = new String[]{
            "Charlie -> I'm in New York today! Anyone wants to have a coffee?",
            "Charlie follows Alice",
            "Charlie follows Bob",
            "Charlie wall"
    };

    public static final String[][] EXPECTED_FOLLOWS_LIST = {
            new String[]{USER_ALICE},
            new String[]{USER_ALICE, USER_BOB}
    };

    public static Date simulateDelayUsing(Date currentDateTime,
                                   CentralSystemClock centralSystemClock,
                                   long timeInMilliSeconds) {
        Date newDateTime = new Date(currentDateTime.getTime() + timeInMilliSeconds);
        when(centralSystemClock.getCurrentDateTime()).thenReturn(newDateTime);
        return newDateTime;
    }
}