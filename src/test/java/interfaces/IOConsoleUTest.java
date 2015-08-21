package interfaces;

import domain.Keyboard;
import domain.Screen;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static helper.ImplHelper.COMMAND_PROMPT_INDICATOR;
import static helper.TestHelper.ALICE_POSTS_A_MESSAGE;
import static helper.TestHelper.ANY_TEXT;
import static helper.TestHelper.EXTRA_LINEFEED_NOT_NEEDED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IOConsoleUTest {

    private IOConsole ioConsole;
    private ByteArrayOutputStream outputStreamContent;

    @Before
    public void setUp() {
        ByteArrayInputStream inputInputStreamContent = new ByteArrayInputStream(ALICE_POSTS_A_MESSAGE.getBytes());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputInputStreamContent);

        outputStreamContent = new ByteArrayOutputStream();
        PrintStream printStreamAsOutputStream = new PrintStream(outputStreamContent);

        ioConsole = new IOConsole(
                new Keyboard(bufferedInputStream),
                new Screen(printStreamAsOutputStream),
                EXTRA_LINEFEED_NOT_NEEDED);
    }

    @Test
    public void givenConsoleIsLoadedWithBufferedInputStreamAndPrintStream_whenALineOfInputIsPassedIn_thenTheSameInputIsReturned() throws IOException {
        // given
        // when
        String actualOutputString = ioConsole.waitForUserAtThePrompt();

        // then
        assertThat("The expected line of input should have been returned.",
                actualOutputString,
                is(equalTo(ALICE_POSTS_A_MESSAGE)));
    }

    @Test
    public void givenConsoleIsLoadedWithBufferedInputStreamAndPrintStream_whenConsolesDisplayIsInvoked_thenItAppearsOnANewLine() throws IOException {
        // given
        // when
        ioConsole.waitForUserAtThePrompt();
        ioConsole.display(ANY_TEXT);
        String actualOutputString = outputStreamContent.toString();

        // then
        assertThat("The text displayed on the output stream should have been returned.",
                actualOutputString,
                is(equalTo(COMMAND_PROMPT_INDICATOR + ANY_TEXT)));
    }
}