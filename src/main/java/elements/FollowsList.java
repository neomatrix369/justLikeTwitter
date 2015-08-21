package elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FollowsList {
    private Map<String, List<String>> store = new HashMap<>();

    public List<String> get(String userName) {
        return store.get(userName);
    }

    public void put(String userName, List<String> followsList) {
        store.put(userName, followsList);
    }
}
