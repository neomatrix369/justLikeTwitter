package interfaces;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IOConsoleUTest {

    private static final String INPUT_FROM_ALICE = "Alice -> I love the weather today";

    @Test
    public void givenAnInput_whenConsoleIsLoadedUsingBufferedInputStreamAndPrintStream_thenTheSameInputIsVisible() throws IOException {
        // given
        PrintStream printStreamAsOutputStream = new PrintStream(new ByteArrayOutputStream());

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(INPUT_FROM_ALICE.getBytes());
        BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream);

        IOConsole IOConsole = new IOConsole(bufferedInputStream, printStreamAsOutputStream);

        // when
        String actualOutputString = IOConsole.showPrompt();

        // then
        assertThat("The expected input string should have been returned.",
                actualOutputString,
                is(equalTo(INPUT_FROM_ALICE)));
    }
}
