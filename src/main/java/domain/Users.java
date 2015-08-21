package domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Users {
    private List<User> usersList = new ArrayList<>();

    public Users() {
        super();
    }

    public Users(User... user) {
        usersList.addAll(Arrays.asList(user));
    }

    public Users(Users users) {
        usersList.addAll(users.usersList);
    }

    public boolean contains(User user) {
        return usersList.contains(user);
    }

    public void add(User user) {
        usersList.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        return new EqualsBuilder()
                .append(usersList, users.usersList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(usersList)
                .toHashCode();
    }
}
