package com.codurance.userinterface;

import com.codurance.command.UserTypedCommand;
import com.codurance.domain.io.UserInput;
import com.codurance.domain.io.UserOutput;

import java.io.IOException;

import static com.codurance.functionality.command.NoCommand.NOTHING_AS_EXECUTION_RESULT;

public class IOConsole {

    private static final String COMMAND_PROMPT_INDICATOR = "> ";

    private final UserInput userInput;
    private final UserOutput userOutput;

    public IOConsole(UserInput userInput,
                     UserOutput userOutput) {
        this.userOutput = userOutput;
        this.userInput = userInput;
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
        if (userInput.hasNextLine()) {
            return new UserTypedCommand(userInput.nextLine());
        }
        return new UserTypedCommand(NOTHING_AS_EXECUTION_RESULT);
    }

    public void display(String outputToDisplay) throws IOException {
        userOutput.display(outputToDisplay);
    }

}