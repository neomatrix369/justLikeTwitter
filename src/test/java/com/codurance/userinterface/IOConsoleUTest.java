package com.codurance.userinterface;

import com.codurance.command.UserTypedCommand;
import com.codurance.helper.ArrayAsInputStream;
import com.codurance.helper.ArrayAsOutputStream;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static com.codurance.helper.TestHelper.ALICE_POSTS_A_MESSAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IOConsoleUTest {

    private static final String NOTHING = "''";
    private IOConsole ioConsole;

    @Before
    public void setUp() throws IOException {
        ioConsole = new IOConsole(
                new ArrayAsInputStream(ALICE_POSTS_A_MESSAGE),
                new ArrayAsOutputStream());
    }

    @Test
    public void givenConsoleIsArrayAsInputAndOutputStreams_whenALineOfInputIsPassedIn_thenTheSameInputIsReturned() throws IOException {
        // given
        // when
        UserTypedCommand actualUserTypedCommand = ioConsole.waitForUserAtThePrompt();

        // then
        assertThat("The expected line of input should have been returned.",
                actualUserTypedCommand,
                is(equalTo(ALICE_POSTS_A_MESSAGE)));
    }

    @Test
    public void givenConsoleIsArrayAsInputAndOutputStreams_whenAnInputIsAlreadyReadAndPromptIsInvokedAgain_thenNothingIsReturned() throws IOException {
        // given
        // when
        ioConsole.waitForUserAtThePrompt();
        UserTypedCommand actualUserTypedCommand = ioConsole.waitForUserAtThePrompt();

                // then
        assertThat("The expected line of input should have been returned.",
                actualUserTypedCommand.toString(),
                is(equalTo(NOTHING)));
    }

    @Test
    public void givenConsoleIsSetup_whenDisplayIsInvokedWithText_thenConsoleDisplaysTheText() throws IOException {
        // given
        ArrayAsOutputStream arrayAsOutputStreamMock = mock(ArrayAsOutputStream.class);
        ioConsole = new IOConsole(
                new ArrayAsInputStream(ALICE_POSTS_A_MESSAGE),
                arrayAsOutputStreamMock);
        ArgumentCaptor<String> actualDisplayedText = ArgumentCaptor.forClass(String.class);

        // when
        ioConsole.display(ALICE_POSTS_A_MESSAGE.toString());

        // then
        verify(arrayAsOutputStreamMock).display(actualDisplayedText.capture());
        assertThat("Should have displayed the text",
                actualDisplayedText.getValue(),
                is(equalTo(ALICE_POSTS_A_MESSAGE.toString())));
    }
}