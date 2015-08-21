package elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FollowsList {
    private Map<String, List<String>> store = new HashMap<>();

    public List<String> getFor(String userName) {
        return store.get(userName);
    }

    public void addNewFor(String userName, String newUsername) {
        List<String> existingFollowsList = getExistingFollowsListFor(userName);

        existingFollowsList.add(newUsername);

        store.put(userName, existingFollowsList);
    }

    private List<String> getExistingFollowsListFor(String userName) {
        List<String> existingFollowsList = store.get(userName);

        if (existingFollowsList == null) {
            existingFollowsList = new ArrayList<>();
        }
        return existingFollowsList;
    }
}
