package com.codurance.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Users {
    private final Set<User> uniqueUsersList = new HashSet<>();

    public Users() {
        super();
    }

    public Users(User... user) {
        uniqueUsersList.addAll(Arrays.asList(user));
    }

    public Users(Users users) {
        uniqueUsersList.addAll(users.uniqueUsersList);
    }

    public boolean contains(User user) {
        return uniqueUsersList.contains(user);
    }

    public void add(User user) {
        uniqueUsersList.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Users users = (Users) o;

        return new EqualsBuilder()
                .append(uniqueUsersList, users.uniqueUsersList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uniqueUsersList)
                .toHashCode();
    }
}
