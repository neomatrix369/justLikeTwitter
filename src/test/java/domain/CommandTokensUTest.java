package domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static domain.CommandType.DISPLAY_WALL;
import static domain.CommandType.FOLLOWS_USER;
import static domain.CommandType.POST_MESSAGE;
import static domain.CommandType.READ_POST;
import static helper.TestHelper.ASSERT_REASON_FOR_FIELD_VALUE_NOT_RETURNED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CommandTokensUTest {

    private final String commandAsString;
    private final CommandType commandType;
    private final List<String> expectedFieldValues;

    @Parameterized.Parameters(name = "{index}: (User types {0}, invoking the {1} action, giving Command Tokens: {2}")
    public static Collection<?> data() {
        return Arrays.asList(
                new Object[][]{
                        // Post message
                        {"Alice -> I'm having a good time",
                                POST_MESSAGE,
                                new ArrayList() {{
                                    add("Alice");
                                    add("I'm having a good time");
                                }}
                        },

                        // Read message
                        {"Alice",
                                READ_POST,
                                new ArrayList() {{
                                    add("Alice");
                                }}
                        },

                        // Follow
                        {"Alice follows Bob",
                                FOLLOWS_USER,
                                new ArrayList() {{
                                    add("Alice");
                                    add("Bob");
                                }}
                        },

                        // Display Wall
                        {"Bob wall",
                                DISPLAY_WALL,
                                new ArrayList() {{
                                    add("Bob");
                                }}
                        }
                });
    }

    public CommandTokensUTest(String commandAsString,
                              CommandType commandType,
                              List<String> expectedFieldValues) {
        this.commandAsString = commandAsString;
        this.commandType = commandType;
        this.expectedFieldValues = expectedFieldValues;
    }

    @Test
    public void givenUserTypedCommandIsAvailable_whenCommandTokenParsesIt_thenItUsesTheRespectivePattern() {
        // given
        String commandString = commandAsString;

        // when
        CommandTokens commandTokens = new CommandTokens(commandString, commandType);
        String[] actualFieldValues = new String[commandType.getFieldNames().length];
        int index = 0;
        for (String fieldName : commandType.getFieldNames()) {
            actualFieldValues[index] = commandTokens.get(fieldName);
            index++;
        }

        // then
        index = 0;
        for (String expectedFieldValue : expectedFieldValues) {
            assertThat(String.format(ASSERT_REASON_FOR_FIELD_VALUE_NOT_RETURNED, expectedFieldValue),
                    actualFieldValues[index],
                    is(equalTo(expectedFieldValue)));
            index++;
        }
    }
}