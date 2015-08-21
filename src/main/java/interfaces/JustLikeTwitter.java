package interfaces;

import elements.MessageStore;
import engine.JustLikeTwitterEngine;
import clock.CentralSystemClock;

import java.io.IOException;

public class JustLikeTwitter {
    private static final int START_FROM_ONE = 1;
    private static final int FOREVER = -1;
    private static final boolean EXTRA_LINEFEED_NOT_NEEDED = false;

    private final JustLikeTwitterEngine justLikeTwitterEngine;
    private final IOConsole ioConsole;

    public JustLikeTwitter(IOConsole ioConsole,
                           JustLikeTwitterEngine justLikeTwitterEngine) {
        this.justLikeTwitterEngine = justLikeTwitterEngine;
        this.ioConsole = ioConsole;
    }

    public static void main(String[] args) throws IOException {
        JustLikeTwitter justLikeTwitter = setupJustLikeTwitter();

        justLikeTwitter.run(FOREVER);
    }

    private static JustLikeTwitter setupJustLikeTwitter() {
        CentralSystemClock centralSystemClock = new CentralSystemClock();
        MessageStore messageStore = new MessageStore();
        JustLikeTwitterEngine justLikeTwitterEngine = new JustLikeTwitterEngine(messageStore, centralSystemClock);

        IOConsole ioConsole = new IOConsole(System.in, System.out, EXTRA_LINEFEED_NOT_NEEDED);

        return new JustLikeTwitter(ioConsole, justLikeTwitterEngine);
    }

    public void run(int maxTimesToRun) throws IOException {
        int runCount = START_FROM_ONE;

        showUsageText();
        
        do {
            String userTypedCommand = ioConsole.waitForUserAtThePrompt();
            String result = justLikeTwitterEngine.executeCommand(userTypedCommand);
            ioConsole.display(result);
            runCount++;
        } while ((maxTimesToRun == FOREVER) || (runCount <= maxTimesToRun));
    }

    private void showUsageText() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String usageText = stringBuilder
                .append("---------------------------------------------------------------------------------------").append(System.lineSeparator())
                .append("Running JustLikeTwitter console app").append(System.lineSeparator())
                .append("Use Ctrl-C to exit the program.").append(System.lineSeparator())
                .append("Only sunny path cases have been covered.").append(System.lineSeparator())
                .append("---------------------------------------------------------------------------------------").append(System.lineSeparator())
                .append("").append(System.lineSeparator())
                .append("Command-line help (usage with examples):").append(System.lineSeparator())
                .append("").append(System.lineSeparator())
                .append("  - Posting to personal timeline: <user name> -> <message>").append(System.lineSeparator())
                .append("  for e.g.").append(System.lineSeparator())
                .append("     > Alice -> I'm having a great time").append(System.lineSeparator())
                .append("").append(System.lineSeparator())
                .append("  - Reading any user's timeline: <user name>").append(System.lineSeparator())
                .append("  for e.g.").append(System.lineSeparator())
                .append("     > Alice ").append(System.lineSeparator())
                .append("     I'm having a great time (2 seconds ago)").append(System.lineSeparator())
                .append("").append(System.lineSeparator())
                .append("  - Following another user: <user name> follows <another user>").append(System.lineSeparator())
                .append("  for e.g.").append(System.lineSeparator())
                .append("     > Alice follows Bob").append(System.lineSeparator())
                .append("").append(System.lineSeparator())
                .append("  - Display user's wall : <user name> wall").append(System.lineSeparator())
                .append("  for e.g.").append(System.lineSeparator())
                .append("     > Alice wall").append(System.lineSeparator())
                .append("     Bob - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)").append(System.lineSeparator())
                .append("     Alice - I'm having a great time (5 minutes ago)").append(System.lineSeparator())
                .append("").append(System.lineSeparator())
                .toString();

        ioConsole.display(usageText);
    }
}