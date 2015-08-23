package interfaces;

import domain.Keyboard;
import domain.LineFeedToggle;
import domain.Screen;
import domain.command.UserTypedCommand;

import java.io.IOException;
import java.util.Scanner;

import static helper.ImplHelper.COMMAND_PROMPT_INDICATOR;
import static helper.ImplHelper.NOTHING;
import static helper.ImplHelper.NO_OUTPUT_IS_AVAILABLE;
import static helper.ImplHelper.UTF_8_STRING;

public class IOConsole {

    private final LineFeedToggle needLineFeedForEachLine;
    private final Scanner scanner;

    private final Screen screen;

    public IOConsole(Keyboard keyBoard,
                     Screen screen,
                     LineFeedToggle needLineFeedForEachLine) {
        this.screen = screen;
        this.needLineFeedForEachLine = needLineFeedForEachLine;

        scanner = new Scanner(keyBoard.toStream(), UTF_8_STRING);
    }

    public UserTypedCommand waitForUserAtThePrompt() throws IOException {
        printPromptIndicator(screen);
        return gatherWhatTheUserTypesAtThePrompt();
    }

    private void printPromptIndicator(Screen screen) throws IOException {
        String newCommandPrompt = COMMAND_PROMPT_INDICATOR;
        newCommandPrompt = insertLineFeedInFrontOf(newCommandPrompt);
        screen.write(newCommandPrompt.getBytes());
    }

    private UserTypedCommand gatherWhatTheUserTypesAtThePrompt() {
        if (scanner.hasNextLine()) {
            return new UserTypedCommand(scanner.nextLine());
        }
        return new UserTypedCommand(NOTHING);
    }

    public void display(String outputToDisplay) throws IOException {
        if (outputToDisplay != NO_OUTPUT_IS_AVAILABLE) {
            String newOutputToDisplay = outputToDisplay;
            newOutputToDisplay = insertLineFeedInFrontOf(newOutputToDisplay);
            screen.get().write(newOutputToDisplay.getBytes());
        }
    }

    private String insertLineFeedInFrontOf(String value) {
        if (needLineFeedForEachLine.toBoolean()) {
            return System.lineSeparator() + value;
        }
        return value;
    }
}