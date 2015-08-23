package interfaces;

import clock.CentralSystemClock;
import domain.FollowsList;
import domain.Keyboard;
import domain.message.MessageStore;
import domain.Screen;
import domain.command.UserTypedCommand;
import functionality.JustLikeTwitterEngine;
import helper.FileIOHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static helper.ImplHelper.APP_USAGE_FILEPATH;
import static helper.ImplHelper.EXTRA_LINEFEED_NOT_NEEDED;
import static helper.ImplHelper.FOREVER;
import static helper.ImplHelper.START_FROM_ONE;

public class JustLikeTwitter {
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
        FollowsList followsList = new FollowsList();
        MessageStore messageStore = new MessageStore();

        JustLikeTwitterEngine justLikeTwitterEngine =
                new JustLikeTwitterEngine(
                        messageStore,
                        followsList,
                        centralSystemClock);

        IOConsole ioConsole = new IOConsole(
                new Keyboard(System.in),
                new Screen(System.out),
                EXTRA_LINEFEED_NOT_NEEDED);

        return new JustLikeTwitter(ioConsole, justLikeTwitterEngine);
    }

    public void run(int maxTimesToRun) throws IOException {
        showUsageText();
        runNow(maxTimesToRun);
    }

    private void runNow(int maxTimesToRun) throws IOException {
        int runCount = START_FROM_ONE;
        do {
            UserTypedCommand userTypedCommand = ioConsole.waitForUserAtThePrompt();
            String result = justLikeTwitterEngine.executeCommand(userTypedCommand);
            ioConsole.display(result);
            runCount++;
        } while ((maxTimesToRun == FOREVER) || (runCount <= maxTimesToRun));
    }

    private void showUsageText() throws IOException {
        String usageText = getUsageTextFromTheResources();
        ioConsole.display(usageText);
    }

    private String getUsageTextFromTheResources() throws IOException {
        Path path = FileIOHelper.getPathFor(getClass(), APP_USAGE_FILEPATH);
        List<String> contentAsList = FileIOHelper.getTheContentOf(path.toString());
        return FileIOHelper.convertListToStringWithLinefeed(contentAsList);
    }
}