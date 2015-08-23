package functionality.command;

import domain.command.CommandLineEntry;
import domain.command.CommandPattern;
import domain.command.Fields;
import domain.command.UserTypedCommand;

import static helper.ImplHelper.MESSAGE_TEXT_FIELD;
import static helper.ImplHelper.NOTHING_FOR_THIS_COMMAND_EXECUTION;
import static helper.ImplHelper.USER_FIELD;

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
