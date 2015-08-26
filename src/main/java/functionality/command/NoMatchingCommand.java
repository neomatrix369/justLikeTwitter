package functionality.command;

import domain.command.CommandPattern;
import domain.command.Fields;
import domain.command.UserTypedCommand;

import static helper.ImplHelper.NOTHING;

public class NoMatchingCommand extends CommandExecutorImpl {

    public NoMatchingCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);
        return NOTHING;
    }
}
