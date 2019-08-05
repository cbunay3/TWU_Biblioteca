package com.twu.library;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.List;

public class LibraryTest {
    @Test
    public void shouldReturnWelcomeMessage() {
        Library library = new Library();
        String expectedMessage = "Welcome to Biblioteca. Your one-stop/shop for great good titles in Bangalore!";

        String welcomeMessage = library.getWelcomeMessage();

        assertThat(welcomeMessage, is(expectedMessage));
    }


}
