package engine.commands;

import elements.CommandLineEntry;
import engine.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

public class FollowUserCommand extends CommandExecutor {

    @Override
    public String execute() {
        CommandLineEntry commandLineEntry = new CommandLineEntry(centralSystemClock);
        commandLineEntry.setUserName(tokens[USERNAME_INDEX].trim());
        commandLineEntry.setOtherUsersName(tokens[OTHER_USERNAME_INDEX].trim());

        List<String> existingFollowsList = getExistingFollowsListFor(commandLineEntry.getUserName());
        combineFollowsList(existingFollowsList, commandLineEntry);

        return "";
    }

    private List<String> getExistingFollowsListFor(String userName) {
        List<String> followsListForAllUsers = followsList.get(userName);

        if (followsListForAllUsers == null) {
            followsListForAllUsers = new ArrayList<>();
        }

        return followsListForAllUsers;
    }

    private void combineFollowsList(List<String> existingFollowsList, CommandLineEntry newCommandLineEntry) {
        existingFollowsList.add(newCommandLineEntry.getOtherUsersName());
        followsList.put(newCommandLineEntry.getUserName(), existingFollowsList);
    }
}