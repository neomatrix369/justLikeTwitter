package com.codurance.functionality.command;

import com.codurance.clock.CentralSystemClock;
import com.codurance.command.CommandPattern;
import com.codurance.command.CommandType;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.Followees;
import com.codurance.domain.message.MessageStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.codurance.helper.TestHelper.ALICE_POSTS_A_MESSAGE;
import static com.codurance.helper.TestHelper.ALICE_READS_POSTS;
import static com.codurance.helper.TestHelper.CHARLIE_FOLLOWS_ALICE;
import static com.codurance.helper.TestHelper.CHARLIE_REQUESTS_WALL;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CommandExecutorSelectorUTest {
    private static final String INVALID_COMMAND = "Invalid command";
    private static final CommandExecutor NO_COMMAND_EXECUTOR_MATCHED =
            new NoMatchingCommand(new CommandPattern(INVALID_COMMAND), new Fields());

    private final UserTypedCommand userTypedCommand;
    private final CommandExecutor expectedCommandExecutor;

    private CommandExecutorSelector commandExecutorSelector;

    @Parameterized.Parameters(name = "{index}: (User types {0}, invoking an action of type {1}.")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        { ALICE_POSTS_A_MESSAGE, CommandType.POST_MESSAGE.getCommandExecutor() },
                        { ALICE_READS_POSTS, CommandType.READ_POST.getCommandExecutor() },
                        { CHARLIE_FOLLOWS_ALICE, CommandType.FOLLOWS_USER.getCommandExecutor() },
                        { CHARLIE_REQUESTS_WALL, CommandType.DISPLAY_WALL.getCommandExecutor() },
                        { new UserTypedCommand(INVALID_COMMAND), NO_COMMAND_EXECUTOR_MATCHED }
                }
        );
    }

    @Before
    public void setUp() {
        commandExecutorSelector = new CommandExecutorSelector(
                new CentralSystemClock(),
                new MessageStore(),
                new Followees());
    }

    public CommandExecutorSelectorUTest(UserTypedCommand userTypedCommand,
                                        CommandExecutor expectedCommandExecutor) {
        this.userTypedCommand = userTypedCommand;
        this.expectedCommandExecutor = expectedCommandExecutor;
    }

    @Test
    public void givenAUserTypedCommand_whenCommandExecutorSelectorIsInvokedWithIt_thenTheRespectiveCommandExecutorIsReturned() {
        // given
        // when
        CommandExecutor actualCommandExecutor = commandExecutorSelector.getCommandExecutorFor(userTypedCommand);

        // then
        assertThat("Should have returned the appropriate command executor.",
                actualCommandExecutor,
                instanceOf(expectedCommandExecutor.getClass())
        );
    }
}