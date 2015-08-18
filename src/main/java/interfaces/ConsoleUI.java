package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI {
    private static final String COMMAND_PROMPT_INDICATOR = "> ";

    public String showPrompt() throws IOException {
        System.out.print(COMMAND_PROMPT_INDICATOR);

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        return bufferRead.readLine();
    }
}
