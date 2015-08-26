package com.codurance;

import com.codurance.clock.CentralSystemClock;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.Keyboard;
import com.codurance.domain.Screen;
import com.codurance.domain.message.MessageStore;
import com.codurance.functionality.JustLikeTwitterEngine;
import com.codurance.helper.FileIOHelper;
import com.codurance.helper.ImplHelper;
import com.codurance.domain.FollowsList;
import com.codurance.userinterface.IOConsole;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

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

        justLikeTwitter.run(ImplHelper.FOREVER);
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
                ImplHelper.EXTRA_LINEFEED_NOT_NEEDED);

        return new JustLikeTwitter(ioConsole, justLikeTwitterEngine);
    }

    public void run(int maxTimesToRun) throws IOException {
        showUsageText();
        runNow(maxTimesToRun);
    }

    private void runNow(int maxTimesToRun) throws IOException {
        int runCount = ImplHelper.START_FROM_ONE;
        do {
            UserTypedCommand userTypedCommand = ioConsole.waitForUserAtThePrompt();
            String result = justLikeTwitterEngine.executeCommand(userTypedCommand);
            ioConsole.display(result);
            runCount++;
        } while ((maxTimesToRun == ImplHelper.FOREVER) || (runCount <= maxTimesToRun));
    }

    private void showUsageText() throws IOException {
        String usageText = getUsageTextFromTheResources();
        ioConsole.display(usageText);
    }

    private String getUsageTextFromTheResources() throws IOException {
        Path path = FileIOHelper.getPathFor(getClass(), ImplHelper.APP_USAGE_FILEPATH);
        List<String> contentAsList = FileIOHelper.getTheContentOf(path.toString());
        return FileIOHelper.convertListToStringWithLinefeed(contentAsList);
    }
}