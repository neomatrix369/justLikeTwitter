package interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class IOConsole {
    public static String COMMAND_PROMPT_INDICATOR = "> ";

    private static final String NOTHING = "";

    private final InputStream inputStream;
    private final OutputStream outputStream;
    private boolean needLineFeedForEachLine;
    private Scanner scanner;

    public IOConsole(InputStream inputStream,
                     OutputStream outputStream,
                     boolean needLineFeedForEachLine) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.needLineFeedForEachLine = needLineFeedForEachLine;

        scanner = new Scanner(inputStream, "UTF-8");
    }

    public String waitForUserAtThePrompt() throws IOException {
        printPromptIndicator(outputStream);
        return gatherWhatTheUserTypesAtThePrompt(inputStream);
    }

    private void printPromptIndicator(OutputStream outputStream) throws IOException {
        String newCommandPrompt = COMMAND_PROMPT_INDICATOR;
        newCommandPrompt = insertLineFeedInFrontOf(newCommandPrompt);
        outputStream.write(newCommandPrompt.getBytes());
    }

    private String gatherWhatTheUserTypesAtThePrompt(InputStream inputStream) {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return NOTHING;
    }

    public void display(String outputToDisplay) throws IOException {
        if (outputToDisplay != null) {
            String newOutputToDisplay = outputToDisplay;
            newOutputToDisplay = insertLineFeedInFrontOf(newOutputToDisplay);
            outputStream.write(newOutputToDisplay.getBytes());
        }
    }

    private String insertLineFeedInFrontOf(String value) {
        if (needLineFeedForEachLine) {
            value = System.lineSeparator() + value;
        }
        return value;
    }
}