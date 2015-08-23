package domain;

import java.util.HashMap;
import java.util.Map;

public class FollowsList {

    private static final Users USER_DOES_NOT_FOLLOW_ANYONE = null;

    private final Map<User, Users> list = new HashMap<>();

    public Users getFollowsFor(User user) {
        Users userFollowsList = list.get(user);
        if (userFollowsList == USER_DOES_NOT_FOLLOW_ANYONE) {
            return new Users();
        }
        return userFollowsList;
    }

    public void addNewFollowOf(User user, User newUser) {
        Users existingFollowsList = getFollowsFor(user);
        existingFollowsList.add(newUser);
        list.put(user, existingFollowsList);
    }
}