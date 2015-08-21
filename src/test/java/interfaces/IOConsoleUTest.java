package interfaces;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static interfaces.IOConsole.COMMAND_PROMPT_INDICATOR;

import static helper.TestHelper.COMMANDS_TYPED_BY_ALICE;
import static helper.TestHelper.ANY_TEXT;
import static helper.TestHelper.EXTRA_LINEFEED_NOT_NEEDED;

public class IOConsoleUTest {

    private IOConsole ioConsole;
    private ByteArrayOutputStream outputStreamContent;

    @Before
    public void setUp() {
        ByteArrayInputStream inputInputStreamContent = new ByteArrayInputStream(COMMANDS_TYPED_BY_ALICE[0].getBytes());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputInputStreamContent);

        outputStreamContent = new ByteArrayOutputStream();
        PrintStream printStreamAsOutputStream = new PrintStream(outputStreamContent);

        ioConsole = new IOConsole(bufferedInputStream, printStreamAsOutputStream, EXTRA_LINEFEED_NOT_NEEDED);
    }

    @Test
    public void givenConsoleIsLoadedWithBufferedInputStreamAndPrintStream_whenALineOfInputIsPassedIn_thenTheSameInputIsReturned() throws IOException {
        // given
        // when
        String actualOutputString = ioConsole.waitForUserAtThePrompt();

        // then
        assertThat("The expected line of input should have been returned.",
                actualOutputString,
                is(equalTo(COMMANDS_TYPED_BY_ALICE[0])));
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