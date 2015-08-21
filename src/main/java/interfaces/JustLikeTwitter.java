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
    private final MessageStore messageStore;

    public JustLikeTwitter(IOConsole ioConsole,
                           JustLikeTwitterEngine justLikeTwitterEngine,
                           MessageStore messageStore) {
        this.justLikeTwitterEngine = justLikeTwitterEngine;
        this.ioConsole = ioConsole;
        this.messageStore = messageStore;
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

        return new JustLikeTwitter(ioConsole, justLikeTwitterEngine, messageStore);
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
        String usageText =
        "---------------------------------------------------------------------------------------" + System.lineSeparator() +
        "Running JustLikeTwitter console app" + System.lineSeparator() +
        "Use Ctrl-C to exit the program." + System.lineSeparator() +
        "Only sunny path cases have been covered." + System.lineSeparator() +
        "---------------------------------------------------------------------------------------" + System.lineSeparator() +
        "" + System.lineSeparator() +
        "Command-line help (usage with examples):" + System.lineSeparator() +
        "" + System.lineSeparator() + 
        "  - Posting to personal timeline: <user name> -> <message>" + System.lineSeparator() + 
        "  for e.g." + System.lineSeparator() + 
        "     > Alice -> I'm having a great time" + System.lineSeparator() + 
        "" + System.lineSeparator() + 
        "  - Reading any user's timeline: <user name>" + System.lineSeparator() + 
        "  for e.g." + System.lineSeparator() + 
        "     > Alice " + System.lineSeparator() + 
        "     I'm having a great time (2 seconds ago)" + System.lineSeparator() + 
        "" + System.lineSeparator() + 
        "  - Following another user: <user name> follows <another user>" + System.lineSeparator() + 
        "  for e.g." + System.lineSeparator() + 
        "     > Alice follows Bob" + System.lineSeparator() + 
        "" + System.lineSeparator() + 
        "  - Display user's wall : <user name> wall" + System.lineSeparator() + 
        "  for e.g." + System.lineSeparator() + 
        "     > Alice wall" + System.lineSeparator() + 
        "     Bob - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)" + System.lineSeparator() + 
        "     Alice - I'm having a great time (5 minutes ago)" + System.lineSeparator() + 
        "" + System.lineSeparator();

        ioConsole.display(usageText);
    }

    public String getTimeLineFor(String userName) {
        return justLikeTwitterEngine.getTimeLineFor(userName);
    }

    public String getWallFor(String userName) {
        return justLikeTwitterEngine.getWallFor(userName);
    }
}