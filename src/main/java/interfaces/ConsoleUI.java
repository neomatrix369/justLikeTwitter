package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI {
    private static final String COMMAND_PROMPT_INDICATOR = "> ";

    public String showPrompt() throws IOException {
        printPromptOnScreen();

        BufferedReader bufferRead = gatherWhatUserTypesAtThePrompt();

        return returnWhatUserHasTyped(bufferRead);
    }

    private void printPromptOnScreen() {
        System.out.print(COMMAND_PROMPT_INDICATOR);
    }

    private BufferedReader gatherWhatUserTypesAtThePrompt() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        return new BufferedReader(inputStreamReader);
    }

    private String returnWhatUserHasTyped(BufferedReader bufferRead) throws IOException {
        return bufferRead.readLine();
    }
}