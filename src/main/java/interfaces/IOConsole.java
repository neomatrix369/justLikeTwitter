package interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class IOConsole {
    public static final String COMMAND_PROMPT_INDICATOR = "> ";

    private final InputStream inputStream;
    private final OutputStream outputStream;

    public IOConsole(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public String showPrompt() throws IOException {
        printPromptIndicator(outputStream);
        return gatherWhatTheUserTypesAtThePrompt(inputStream);
    }

    private void printPromptIndicator(OutputStream outputStream) throws IOException {
        outputStream.write(COMMAND_PROMPT_INDICATOR.getBytes());
    }

    private String gatherWhatTheUserTypesAtThePrompt(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        return scanner.nextLine();
    }

    public void display(String output) throws IOException {
        outputStream.write(output.getBytes());
    }
}