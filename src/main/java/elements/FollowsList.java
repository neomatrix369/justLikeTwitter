package elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static helper.ImplHelper.FIRST_TIME;

public class FollowsList {

    private final Map<String, List<String>> store = new HashMap<>();

    public List<String> getFollowsFor(String userName) {
        return store.get(userName);
    }

    public void addNewFollowOf(String userName, String newUsername) {
        List<String> existingFollowsList = getExistingFollowsListFor(userName);

        existingFollowsList.add(newUsername);

        store.put(userName, existingFollowsList);
    }

    private List<String> getExistingFollowsListFor(String userName) {
        List<String> existingFollowsList = store.get(userName);
        existingFollowsList = getNewListForTheFirstTime(existingFollowsList);
        return existingFollowsList;
    }

    private List<String> getNewListForTheFirstTime(List<String> existingFollowsList) {
        if (existingFollowsList == FIRST_TIME) {
            return new ArrayList<>();
        }
        return existingFollowsList;
    }
}