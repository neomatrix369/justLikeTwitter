package domain;

import java.util.HashMap;
import java.util.Map;

import static helper.ImplHelper.USER_DOES_NOT_FOLLOW_ANYONE;

public class FollowsList {

    private final Map<User, Users> list = new HashMap<>();

    public Users getFollowsFor(User user) {
        return list.get(user);
    }

    public void addNewFollowOf(User user, User newUser) {
        Users existingFollowsList = getExistingFollowsListFor(user);

        existingFollowsList.add(newUser);

        list.put(user, existingFollowsList);
    }

    private Users getExistingFollowsListFor(User user) {
        Users existingFollowsList = list.get(user);
        existingFollowsList = getNewListForTheFirstTime(existingFollowsList);
        return existingFollowsList;
    }

    private Users getNewListForTheFirstTime(Users existingFollowsList) {
        if (existingFollowsList == USER_DOES_NOT_FOLLOW_ANYONE) {
            return new Users();
        }
        return existingFollowsList;
    }
}