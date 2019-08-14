package com.twu.library;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {
    List<User> users = new ArrayList<>();
    {
        users.add(new User("Andres", "andres@gmail.com", "53345","123-1312","12345"));
        users.add(new User("Paola", "paola@gmail.com", "83245","633-5612","12345"));
        users.add(new User("Elise", "elise@gmail.com", "65245","393-8712","12345"));
        users.add(new User("Carlos", "carlos@gmail.com","423222", "123-4567","12345"));
    }

    @Override
    public User findUserByLibraryNumber(String libraryNumber) {
        return users.stream()
                .filter(user -> user.libraryNumber.equals(libraryNumber))
                .findFirst()
                .orElse(null);
    }
}
