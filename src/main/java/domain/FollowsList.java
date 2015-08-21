package domain;

import java.util.HashMap;
import java.util.Map;

import static helper.ImplHelper.NO_FOLLOWS_RETURNED;

public class FollowsList {

    private final Map<User, Users> store = new HashMap<>();

    public Users getFollowsFor(User user) {
        return store.get(user);
    }

    public void addNewFollowOf(User user, User newUser) {
        Users existingFollowsList = getExistingFollowsListFor(user);

        existingFollowsList.add(newUser);

        store.put(user, existingFollowsList);
    }

    private Users getExistingFollowsListFor(User user) {
        Users existingFollowsList = store.get(user);
        existingFollowsList = getNewListForTheFirstTime(existingFollowsList);
        return existingFollowsList;
    }

    private Users getNewListForTheFirstTime(Users existingFollowsList) {
        if (existingFollowsList == NO_FOLLOWS_RETURNED) {
            return new Users();
        }
        return existingFollowsList;
    }
}