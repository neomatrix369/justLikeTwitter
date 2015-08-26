package com.codurance.command;

import com.codurance.domain.User;

public class CommandLineEntryFollowsUser {
    private final User user;
    private final User followsUser;

    public CommandLineEntryFollowsUser(User user, User followsUser) {
        this.user = user;
        this.followsUser = followsUser;
    }

    public User getFollowsUser() {
        return followsUser;
    }

    public User getUser() {
        return user;
    }
}
