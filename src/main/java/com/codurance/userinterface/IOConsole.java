package com.codurance.userinterface;

import com.codurance.command.UserTypedCommand;
import com.codurance.domain.Keyboard;
import com.codurance.domain.LineFeedToggle;
import com.codurance.domain.Screen;

import java.io.IOException;
import java.util.Scanner;

import static com.codurance.helper.ImplHelper.COMMAND_PROMPT_INDICATOR;
import static com.codurance.helper.ImplHelper.NOTHING;

public class IOConsole {

    private final LineFeedToggle needLineFeedForEachLine;
    private final Scanner scanner;

    private final Screen screen;

    public IOConsole(Keyboard keyBoard,
                     Screen screen,
                     LineFeedToggle needLineFeedForEachLine) {
        this.screen = screen;
        this.needLineFeedForEachLine = needLineFeedForEachLine;

        scanner = keyBoard.getScanner();
    }

    public UserTypedCommand waitForUserAtThePrompt() throws IOException {
        printPromptIndicator(screen);
        return gatherWhatTheUserTypesAtThePrompt();
    }

    private void printPromptIndicator(Screen screen) throws IOException {
        String newCommandPrompt = COMMAND_PROMPT_INDICATOR;
        newCommandPrompt = insertLineFeedInFrontOf(newCommandPrompt);
        screen.display(newCommandPrompt);
    }

    private UserTypedCommand gatherWhatTheUserTypesAtThePrompt() {
        if (scanner.hasNextLine()) {
            return new UserTypedCommand(scanner.nextLine());
        }
        return new UserTypedCommand(NOTHING);
    }

    public void display(String outputToDisplay) throws IOException {
        String newOutput = outputToDisplay;
        newOutput = insertLineFeedInFrontOf(newOutput);
        screen.display(newOutput);
    }

    private String insertLineFeedInFrontOf(String value) {
        if (needLineFeedForEachLine.toBoolean()) {
            return System.lineSeparator() + value;
        }
        return value;
    }
}