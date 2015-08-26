package domain;

import java.util.HashMap;
import java.util.Map;

public class FollowsList {

    private static final Users USER_DOES_NOT_FOLLOW_ANYONE = null;

    private final Map<User, Users> userFollowsMapping = new HashMap<>();

    public Users getFollowsFor(User user) {
        Users followsList = userFollowsMapping.get(user);
        if (followsList == USER_DOES_NOT_FOLLOW_ANYONE) {
            return new Users();
        }
        return followsList;
    }

    public void addNewFollowTo(User user, User newFollow) {
        Users existingFollowsList = getFollowsFor(user);
        existingFollowsList.add(newFollow);
        userFollowsMapping.put(user, existingFollowsList);
    }
}