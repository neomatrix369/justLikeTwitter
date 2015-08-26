package com.codurance.functionality.command;

import com.codurance.command.CommandPattern;
import com.codurance.helper.ImplHelper;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;

public class NoMatchingCommand extends CommandExecutorImpl {

    public NoMatchingCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);
        return ImplHelper.NOTHING;
    }
}
