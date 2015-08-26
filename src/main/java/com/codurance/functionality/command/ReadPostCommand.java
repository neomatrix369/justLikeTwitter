package com.codurance.functionality.command;

import com.codurance.command.CommandPattern;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;
import com.codurance.domain.User;
import com.codurance.formatters.PersonalTimeLineFormatter;

import static com.codurance.command.CommandLineEntryParser.createNewUserFrom;
import static com.codurance.command.Fields.USER_FIELD;

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
