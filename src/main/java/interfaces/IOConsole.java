package interfaces;

import domain.Keyboard;
import domain.Screen;

import java.io.IOException;
import java.util.Scanner;

import static helper.ImplHelper.COMMAND_PROMPT_INDICATOR;
import static helper.ImplHelper.NOTHING;
import static helper.ImplHelper.UTF_8_STRING;

public class IOConsole {

    private final boolean needLineFeedForEachLine;
    private final Scanner scanner;

    private final Screen screen;

    public IOConsole(Keyboard keyBoard,
                     Screen screen,
                     boolean needLineFeedForEachLine) {
        this.screen = screen;
        this.needLineFeedForEachLine = needLineFeedForEachLine;

        scanner = new Scanner(keyBoard.get(), UTF_8_STRING);
    }

    public String waitForUserAtThePrompt() throws IOException {
        printPromptIndicator(screen);
        return gatherWhatTheUserTypesAtThePrompt();
    }

    private void printPromptIndicator(Screen screen) throws IOException {
        String newCommandPrompt = COMMAND_PROMPT_INDICATOR;
        newCommandPrompt = insertLineFeedInFrontOf(newCommandPrompt);
        screen.write(newCommandPrompt.getBytes());
    }

    private String gatherWhatTheUserTypesAtThePrompt() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return NOTHING;
    }

    public void display(String outputToDisplay) throws IOException {
        if (outputToDisplay != null) {
            String newOutputToDisplay = outputToDisplay;
            newOutputToDisplay = insertLineFeedInFrontOf(newOutputToDisplay);
            screen.get().write(newOutputToDisplay.getBytes());
        }
    }

    private String insertLineFeedInFrontOf(String value) {
        if (needLineFeedForEachLine) {
            return System.lineSeparator() + value;
        }
        return value;
    }
}