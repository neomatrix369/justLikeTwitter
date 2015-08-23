package functionality.command;

import domain.command.CommandType;
import domain.User;
import domain.command.UserTypedCommand;
import formatters.PersonalTimeLineFormatter;

import static helper.ImplHelper.USER_FIELD;

public class ReadPostCommand extends CommandExecutorImpl {

    public ReadPostCommand(CommandType commandType) {
        super(commandType);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        User user = createNewUserFrom(commandTokens, USER_FIELD);
        PersonalTimeLineFormatter personalTimeLineFormatter =
                new PersonalTimeLineFormatter(centralSystemClock);
        return personalTimeLineFormatter.getMessagesFor(user, messageStore);
    }
}
