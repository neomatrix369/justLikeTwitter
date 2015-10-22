package com.codurance.userinterface;

import com.codurance.command.UserTypedCommand;
import com.codurance.domain.io.UserInput;
import com.codurance.domain.io.UserOutput;

import java.io.IOException;
import java.util.Scanner;

import static com.codurance.functionality.command.NoCommand.NOTHING_AS_EXECUTION_RESULT;

public class IOConsole {

    private static final String COMMAND_PROMPT_INDICATOR = "> ";

    private final Scanner scanner;

    private final UserOutput userOutput;

    public IOConsole(UserInput userInput,
                     UserOutput userOutput) {
        this.userOutput = userOutput;
        scanner = userInput.getScanner();
    }

    public UserTypedCommand waitForUserAtThePrompt() throws IOException {
        printPromptIndicator(userOutput);
        return gatherWhatTheUserTypesAtThePrompt();
    }

    private void printPromptIndicator(UserOutput userOutput) throws IOException {
        String newCommandPrompt = COMMAND_PROMPT_INDICATOR;
        userOutput.display(newCommandPrompt);
    }

    private UserTypedCommand gatherWhatTheUserTypesAtThePrompt() {
        if (scanner.hasNextLine()) {
            return new UserTypedCommand(scanner.nextLine());
        }
        return new UserTypedCommand(NOTHING_AS_EXECUTION_RESULT);
    }

    public void display(String outputToDisplay) throws IOException {
        userOutput.display(outputToDisplay);
    }

}