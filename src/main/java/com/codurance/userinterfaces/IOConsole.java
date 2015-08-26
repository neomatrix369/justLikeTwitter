package com.codurance.userinterfaces;

import com.codurance.command.UserTypedCommand;
import com.codurance.domain.Keyboard;
import com.codurance.domain.LineFeedToggle;
import com.codurance.domain.Screen;
import com.codurance.helper.ImplHelper;

import java.io.IOException;
import java.util.Scanner;

public class IOConsole {

    private final LineFeedToggle needLineFeedForEachLine;
    private final Scanner scanner;

    private final Screen screen;

    public IOConsole(Keyboard keyBoard,
                     Screen screen,
                     LineFeedToggle needLineFeedForEachLine) {
        this.screen = screen;
        this.needLineFeedForEachLine = needLineFeedForEachLine;

        scanner = new Scanner(keyBoard.toStream(), ImplHelper.STRING_ENCODING);
    }

    public UserTypedCommand waitForUserAtThePrompt() throws IOException {
        printPromptIndicator(screen);
        return gatherWhatTheUserTypesAtThePrompt();
    }

    private void printPromptIndicator(Screen screen) throws IOException {
        String newCommandPrompt = ImplHelper.COMMAND_PROMPT_INDICATOR;
        newCommandPrompt = insertLineFeedInFrontOf(newCommandPrompt);
        screen.write(newCommandPrompt.getBytes());
    }

    private UserTypedCommand gatherWhatTheUserTypesAtThePrompt() {
        if (scanner.hasNextLine()) {
            return new UserTypedCommand(scanner.nextLine());
        }
        return new UserTypedCommand(ImplHelper.NOTHING);
    }

    public void display(String outputToDisplay) throws IOException {
        String newOutputToDisplay = outputToDisplay;
        newOutputToDisplay = insertLineFeedInFrontOf(newOutputToDisplay);
        screen.get().write(newOutputToDisplay.getBytes());
    }

    private String insertLineFeedInFrontOf(String value) {
        if (needLineFeedForEachLine.toBoolean()) {
            return System.lineSeparator() + value;
        }
        return value;
    }
}