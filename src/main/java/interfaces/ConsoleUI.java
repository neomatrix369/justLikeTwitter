package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ConsoleUI {
    private static final String COMMAND_PROMPT_INDICATOR = "> ";

    public String showPrompt() throws IOException {
        printPromptIndicator(System.out);

        BufferedReader bufferRead = gatherWhatTheUserTypesAtThePrompt(System.in);

        return whateverTheUserHasTyped(bufferRead);
    }

    private void printPromptIndicator(PrintStream outputStream) {
        outputStream.print(COMMAND_PROMPT_INDICATOR);
    }

    private BufferedReader gatherWhatTheUserTypesAtThePrompt(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

    private String whateverTheUserHasTyped(BufferedReader bufferRead) throws IOException {
        return bufferRead.readLine();
    }
}