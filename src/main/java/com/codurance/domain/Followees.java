package com.codurance.domain;

import java.util.HashMap;
import java.util.Map;

public class Followees {

    private static final Users USER_DOES_NOT_FOLLOW_ANYONE = null;

    private final Map<User, Users> usersFollowedMapping = new HashMap<>();

    public void addFollowee(User user, User newFollowee) {
        Users existingFollowees = getFolloweesFor(user);
        existingFollowees.add(newFollowee);
        usersFollowedMapping.put(user, existingFollowees);
    }

    public Users getFolloweesFor(User user) {
        Users followees = usersFollowedMapping.get(user);
        if (thisUserNeedsToBeAddedAsAFollowee(followees)) {
            return thisUserAsFollowee(user);
        }
        return followees;
    }

    private Users thisUserAsFollowee(User user) {
        return new Users(user);
    }

    private boolean thisUserNeedsToBeAddedAsAFollowee(Users followsList) {
        return followsList == USER_DOES_NOT_FOLLOW_ANYONE;
    }
}