package com.codurance.functionality.command;

import com.codurance.command.CommandPattern;
import com.codurance.command.CommandLineEntry;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;

import static com.codurance.helper.ImplHelper.MESSAGE_TEXT_FIELD;
import static com.codurance.helper.ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;
import static com.codurance.helper.ImplHelper.USER_FIELD;

public class PostMessageCommand extends CommandExecutorImpl {

    public PostMessageCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        CommandLineEntry commandLineEntry = prepareCommandLineEntry();
        messageStore.addMessage(commandLineEntry.getMessagePosted());

        return NOTHING_FOR_THIS_COMMAND_EXECUTION;
    }

    private CommandLineEntry prepareCommandLineEntry() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUser(createNewUserFrom(commandTokens, USER_FIELD));
        commandLineEntry.setMessagePosted(
                commandLineEntry.getUser(),
                createNewMessageTextFrom(commandTokens, MESSAGE_TEXT_FIELD));

        return commandLineEntry;
    }
}
