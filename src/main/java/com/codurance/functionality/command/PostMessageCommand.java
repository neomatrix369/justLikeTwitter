package com.codurance.functionality.command;

import com.codurance.command.CommandPattern;
import com.codurance.command.CommandLineEntryPostMessage;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;

import static com.codurance.command.FieldsFactory.createNewUserFrom;
import static com.codurance.command.FieldsFactory.createNewMessageTextFrom;
import static com.codurance.command.Fields.MESSAGE_TEXT_FIELD;
import static com.codurance.command.Fields.USER_FIELD;
import static com.codurance.helper.ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;

public class PostMessageCommand extends CommandExecutorImpl {

    public PostMessageCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        CommandLineEntryPostMessage commandLineEntryPostMessage = prepareCommandLineEntry();
        messageStore.addMessage(commandLineEntryPostMessage.getMessagePosted());

        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntryPostMessage prepareCommandLineEntry() {

        return new CommandLineEntryPostMessage(
                centralSystemClock,
                createNewUserFrom(commandTokens, USER_FIELD),
                createNewMessageTextFrom(commandTokens, MESSAGE_TEXT_FIELD)
        );
    }
}
