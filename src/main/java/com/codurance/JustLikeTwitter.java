package com.codurance;

import com.codurance.clock.CentralSystemClock;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.FollowsList;
import com.codurance.domain.Keyboard;
import com.codurance.domain.Screen;
import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.JustLikeTwitterEngine;
import com.codurance.userinterface.IOConsole;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.codurance.helper.FileIOHelper.convertListToStringWithLinefeed;
import static com.codurance.helper.FileIOHelper.getPathFor;
import static com.codurance.helper.FileIOHelper.getTheContentOf;
import static com.codurance.helper.ImplHelper.APP_USAGE_FILEPATH;
import static com.codurance.helper.ImplHelper.EXTRA_LINEFEED_NOT_NEEDED;
import static com.codurance.helper.ImplHelper.FOREVER;
import static com.codurance.helper.ImplHelper.START_FROM_ONE;

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
                        centralSystemClock,
                        messageStore,
                        followsList
                );

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
        Path path = getPathFor(getClass(), APP_USAGE_FILEPATH);
        List<String> contentAsList = getTheContentOf(path.toString());
        return convertListToStringWithLinefeed(contentAsList);
    }
}