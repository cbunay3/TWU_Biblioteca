package com.twu.library;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

public class LibraryTest {

    private List<Book> books;
    private PrintStream printStream;
    private Library library;

    @Before
    public void setUp() {
        books = new ArrayList<>();
        printStream = mock(PrintStream.class);
        library = new Library(books, printStream);
    }

    @Test
    public void shouldPrintWelcomeMessage() {
        String expectedWelcomeMessage = "Welcome to Biblioteca. Your one-stop/shop for great good titles in Bangalore!";

        library.viewWelcomeMessage();

        verify(printStream).println(expectedWelcomeMessage);
    }

    @Test
    public void shouldPrintNothingWhenThereAreNoBooks() {
        library.listBooks();

        verify(printStream).println("");
    }

    @Test
    public void shouldPrintBookInfoWhenThereIsOneBook() {
        books.add(new Book("Head First Java","Kathy Sierra",2003));

        library.listBooks();

        verify(printStream).println("Head First Java,  Kathy Sierra,  2003\n");
    }

    @Test
    public void shouldPrintBothBookInfoWhenThereAreTwoBooks() throws IOException {
        books.add(new Book("Head First Java","Kathy Sierra",2003));
        books.add(new Book("Clean Code", "Robert C. Martin", 2008));

        library.listBooks();

        verify(printStream).println("Head First Java,  Kathy Sierra,  2003\nClean Code,  Robert C. Martin,  2008\n");
    }
}
