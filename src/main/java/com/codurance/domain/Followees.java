package com.codurance.domain;

import java.util.HashMap;
import java.util.Map;

public class Followees {

    private final Map<User, Users> usersToFollowees = new HashMap<>();

    public void addFollowee(User user, User newFollowee) {
        Users existingFollowees = getFolloweesFor(user);
        existingFollowees.add(newFollowee);
        usersToFollowees.put(user, existingFollowees);
    }

    public Users getFolloweesFor(User user) {
        return usersToFollowees.getOrDefault(user, thisUserAsFollowee(user));
    }

    private Users thisUserAsFollowee(User user) {
        return new Users(user);
    }
}