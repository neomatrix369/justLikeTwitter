package interfaces;

import engine.JustLikeTwitterEngine;
import processors.DateTimeStampProvider;

import java.io.IOException;

public class JustLikeTwitter {
    private static final int START_FROM_ONE = 1;
    private static final int FOREVER = -1;

    private final JustLikeTwitterEngine justLikeTwitterEngine;
    private final IOConsole ioConsole;

    public JustLikeTwitter(JustLikeTwitterEngine justLikeTwitterEngine, IOConsole ioConsole) {
        this.justLikeTwitterEngine = justLikeTwitterEngine;
        this.ioConsole = ioConsole;
    }

    public static void main(String[] args) throws IOException {
        JustLikeTwitter justLikeTwitter = setupJustLikeTwitter();

        justLikeTwitter.run(FOREVER);
    }

    private static JustLikeTwitter setupJustLikeTwitter() {
        DateTimeStampProvider dateTimeStampProvider = new DateTimeStampProvider();
        JustLikeTwitterEngine justLikeTwitterEngine = new JustLikeTwitterEngine(dateTimeStampProvider);
        IOConsole ioConsole = new IOConsole(System.in, System.out);

        return new JustLikeTwitter(justLikeTwitterEngine, ioConsole);
    }

    public void run(int maxTimesToRun) throws IOException {
        int runCount = START_FROM_ONE;
        do {
            String userTypedCommand = ioConsole.showPrompt();

            String result = justLikeTwitterEngine.executeCommand(userTypedCommand);
            ioConsole.display(result);
            runCount++;
        } while ((maxTimesToRun == FOREVER) || (runCount <= maxTimesToRun));
    }

    public String showTimeLineFor(String userName) {
        return justLikeTwitterEngine.getTimeLineFor(userName);
    }
}