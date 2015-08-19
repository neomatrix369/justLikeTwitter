package interfaces;

import engine.JustLikeTwitterEngine;

import java.io.IOException;

public class JustLikeTwitter {
    private static final int START_FROM_ONE = 1;

    private final JustLikeTwitterEngine justLikeTwitterEngine;
    private final IOConsole IOConsole;

    public JustLikeTwitter(JustLikeTwitterEngine justLikeTwitterEngine, IOConsole IOConsole) {
        this.justLikeTwitterEngine = justLikeTwitterEngine;
        this.IOConsole = IOConsole;
    }

    public void run(int maxTimesToRun) throws IOException {
        int runCount = START_FROM_ONE;
        do {
            String userTypedCommand = IOConsole.showPrompt();
            justLikeTwitterEngine.executeCommand(userTypedCommand);
            runCount++;
        } while (runCount <= maxTimesToRun);
    }
}