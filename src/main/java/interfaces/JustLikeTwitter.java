package interfaces;

import engine.JustLikeTwitterEngine;

import java.io.IOException;

public class JustLikeTwitter {
    private static final int START_FROM_ONE = 1;

    private final JustLikeTwitterEngine justLikeTwitterEngine;
    private final ConsoleUI consoleUI;

    public JustLikeTwitter(JustLikeTwitterEngine justLikeTwitterEngine, ConsoleUI consoleUI) {
        this.justLikeTwitterEngine = justLikeTwitterEngine;
        this.consoleUI = consoleUI;
    }

    public void run(int maxTimesToRun) throws IOException {
        int runCount = START_FROM_ONE;
        do {
            String userTypedCommand = consoleUI.showPrompt();
            justLikeTwitterEngine.executeCommand(userTypedCommand);
            runCount++;
        } while (runCount <= maxTimesToRun);
    }
}