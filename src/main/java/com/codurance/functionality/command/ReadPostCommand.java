package com.codurance.functionality.command;

import com.codurance.command.CommandPattern;
import com.codurance.formatters.PersonalTimeLineFormatter;
import com.codurance.helper.ImplHelper;
import com.codurance.domain.User;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;

public class ReadPostCommand extends CommandExecutorImpl {

    public ReadPostCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);

        User user = createNewUserFrom(commandTokens, ImplHelper.USER_FIELD);
        PersonalTimeLineFormatter personalTimeLineFormatter =
                new PersonalTimeLineFormatter(centralSystemClock);

        return personalTimeLineFormatter.getMessagesFor(user, messageStore);
    }
}
