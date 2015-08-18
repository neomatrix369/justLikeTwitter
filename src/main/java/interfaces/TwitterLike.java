package interfaces;

import engine.TwitterLikeEngine;

import java.io.IOException;

public class TwitterLike {
    private static final int START_FROM_ONE = 1;

    private final TwitterLikeEngine twitterLikeEngine;
    private final ConsoleUI consoleUI;

    public TwitterLike(TwitterLikeEngine twitterLikeEngine, ConsoleUI consoleUI) {
        this.twitterLikeEngine = twitterLikeEngine;
        this.consoleUI = consoleUI;
    }

    public void run(int maxTimesToRun) throws IOException {
        int runCount = START_FROM_ONE;
        do {
            String userTypedCommand = consoleUI.showPrompt();
            twitterLikeEngine.executeCommand(userTypedCommand);
            runCount++;
        } while (runCount <= maxTimesToRun);
    }
}