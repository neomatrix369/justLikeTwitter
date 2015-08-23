package domain.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static domain.command.CommandType.DISPLAY_WALL;
import static domain.command.CommandType.FOLLOWS_USER;
import static domain.command.CommandType.POST_MESSAGE;
import static domain.command.CommandType.READ_POST;
import static helper.TestHelper.ASSERT_REASON_FOR_FIELD_VALUE_NOT_RETURNED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CommandTokensUTest {

    private final String command;
    private final CommandType commandType;
    private final List<String> expectedFieldValues;

    @Parameterized.Parameters(name = "{index}: (User types {0}, invoking the {1} action, giving Command Tokens: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        // Post message
                        {"Alice -> I'm having a good time",
                                POST_MESSAGE,
                                new ArrayList<String>() {{
                                    add("Alice");
                                    add("I'm having a good time");
                                }}
                        },

                        // Read message
                        {"Alice",
                                READ_POST,
                                new ArrayList<String>() {{
                                    add("Alice");
                                }}
                        },

                        // Follow
                        {"Alice follows Bob",
                                FOLLOWS_USER,
                                new ArrayList<String>() {{
                                    add("Alice");
                                    add("Bob");
                                }}
                        },

                        // Display Wall
                        {"Bob wall",
                                DISPLAY_WALL,
                                new ArrayList<String>() {{
                                    add("Bob");
                                }}
                        }
                });
    }

    public CommandTokensUTest(String command,
                              CommandType commandType,
                              List<String> expectedFieldValues) {
        this.command = command;
        this.commandType = commandType;
        this.expectedFieldValues = expectedFieldValues;
    }

    @Test
    public void givenUserTypedCommandIsAvailable_whenCommandTokenParsesIt_thenItUsesTheRespectivePattern() {
        // given
        // when
        String[] actualFieldValues = retrieveFieldValuesFromCommandTypedByUser(command);

        // then
        verifyIfFieldsReturnedAreAsExpected(actualFieldValues);
    }

    private void verifyIfFieldsReturnedAreAsExpected(String[] actualFieldValues) {
        int index = 0;
        for (String expectedFieldValue : expectedFieldValues) {
            assertThat(String.format(ASSERT_REASON_FOR_FIELD_VALUE_NOT_RETURNED, expectedFieldValue),
                    actualFieldValues[index],
                    is(equalTo(expectedFieldValue)));
            index++;
        }
    }

    private String[] retrieveFieldValuesFromCommandTypedByUser(String commandString) {
        Fields fields = commandType.getCommandExecutor().getFields();
        CommandPattern matchingPattern = commandType.getMatchingPattern();
        CommandTokens commandTokens = new CommandTokens(commandString, matchingPattern, fields);

        return getActualFieldValues(fields, commandTokens);
    }

    private String[] getActualFieldValues(Fields fields, CommandTokens commandTokens) {
        String[] actualFieldValues = new String[fields.getLength()];
        int index = 0;
        for (String field : fields.toList()) {
            actualFieldValues[index] = commandTokens.getValueFor(field);
            index++;
        }
        return actualFieldValues;
    }
}