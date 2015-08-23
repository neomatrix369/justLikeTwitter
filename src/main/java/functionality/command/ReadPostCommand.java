package functionality.command;

import domain.User;
import domain.command.CommandPattern;
import domain.command.Fields;
import domain.command.UserTypedCommand;
import formatters.PersonalTimeLineFormatter;

import static helper.ImplHelper.USER_FIELD;

public class ReadPostCommand extends CommandExecutorImpl {

    public ReadPostCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
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
