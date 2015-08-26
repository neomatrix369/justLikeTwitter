package com.codurance.domain;

import java.util.HashMap;
import java.util.Map;

public class FollowsList {

    private static final Users USER_DOES_NOT_FOLLOW_ANYONE = null;

    private final Map<User, Users> usersFollowedMapping = new HashMap<>();

    public void addNewUserFollowed(User user, User newFollow) {
        Users existingFollowsList = getFollowsListFor(user);
        existingFollowsList.add(newFollow);
        usersFollowedMapping.put(user, existingFollowsList);
    }

    public Users getFollowsListFor(User user) {
        Users followsList = usersFollowedMapping.get(user);
        if (followsList == USER_DOES_NOT_FOLLOW_ANYONE) {
            return new Users();
        }
        return followsList;
    }
}