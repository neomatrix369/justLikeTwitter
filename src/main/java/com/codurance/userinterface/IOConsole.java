package com.codurance.userinterface;

import com.codurance.command.UserTypedCommand;
import com.codurance.domain.LineFeedToggle;
import com.codurance.domain.UserInput;
import com.codurance.domain.UserOutput;

import java.io.IOException;
import java.util.Scanner;

import static com.codurance.helper.ImplHelper.COMMAND_PROMPT_INDICATOR;
import static com.codurance.helper.ImplHelper.NOTHING;

public class IOConsole {

    private final LineFeedToggle needLineFeedForEachLine;
    private final Scanner scanner;

    private final UserOutput userOutput;

    public IOConsole(UserInput userInput,
                     UserOutput userOutput,
                     LineFeedToggle needLineFeedForEachLine) {
        this.userOutput = userOutput;
        this.needLineFeedForEachLine = needLineFeedForEachLine;

        scanner = userInput.getScanner();
    }

    public UserTypedCommand waitForUserAtThePrompt() throws IOException {
        printPromptIndicator(userOutput);
        return gatherWhatTheUserTypesAtThePrompt();
    }

    private void printPromptIndicator(UserOutput userOutput) throws IOException {
        String newCommandPrompt = COMMAND_PROMPT_INDICATOR;
        newCommandPrompt = insertLineFeedInFrontOf(newCommandPrompt);
        userOutput.display(newCommandPrompt);
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
        userOutput.display(newOutput);
    }

    private String insertLineFeedInFrontOf(String value) {
        if (needLineFeedForEachLine.toBoolean()) {
            return System.lineSeparator() + value;
        }
        return value;
    }
}