package com.codurance.functionality.command;

import com.codurance.command.CommandPattern;
import com.codurance.command.Fields;
import com.codurance.command.UserTypedCommand;

public class NoCommand extends CommandExecutorImpl {

    public static final String NOTHING_AS_EXECUTION_RESULT = "";

    public NoCommand(CommandPattern commandPattern, Fields fields) {
        super(commandPattern, fields);
    }

    @Override
    public String execute(UserTypedCommand userTypedCommand) {
        super.execute(userTypedCommand);
        return NOTHING_AS_EXECUTION_RESULT;
    }
}