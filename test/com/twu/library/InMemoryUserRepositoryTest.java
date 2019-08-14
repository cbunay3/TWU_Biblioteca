package com.twu.library;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class InMemoryUserRepositoryTest {
    @Test
    public void findUserSuccessful() {
        String libraryNumber = "123-4567";
        UserRepository userRepository = new InMemoryUserRepository();

        User user = userRepository.findUserByLibraryNumber(libraryNumber);

        assertThat(user.libraryNumber, is("123-4567"));
    }

    @Test
    public void findUserUnsuccesful() {
        String libraryNumber = "222-0444";
        UserRepository userRepository = new InMemoryUserRepository();

        User user = userRepository.findUserByLibraryNumber(libraryNumber);

        assertThat(user,is(nullValue()));
    }

}