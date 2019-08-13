package com.twu.library;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


import static com.twu.library.MenuOption.*;
import static org.mockito.Mockito.*;

public class LibraryServiceTest {
    private Library library;
    private LibraryService libraryService;
    private PrintStream mockPrintStream;
    private BufferedReader mockBufferedReader;


    @Before
    public void setUp() {
        mockPrintStream = mock(PrintStream.class);
        mockBufferedReader = mock(BufferedReader.class);
        library = new Library(books(), mockPrintStream);
    }

    public List<Book> books() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Head First Java", "Kathy Sierra", 2003));
        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        return books;
    }

    public String expectedBooksInfo() {
        StringBuilder booksInfo = new StringBuilder();
        books().forEach(book -> booksInfo.append(book.getInfo()));
        return booksInfo.toString();
    }

    @Test
    public void shouldPrintBooksInfoWhenUserListBooks() {
        libraryService = new LibraryService(library, mockBufferedReader);

        libraryService.showOption(LIST_BOOKS.code());

        verify(mockPrintStream).print(expectedBooksInfo());
    }

    @Test
    public void shouldPrintSuccessMessageWhenUserCheckoutABook() throws IOException {
        libraryService = new LibraryService(library, mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("Clean Code");

        libraryService.showOption(CHECKOUT_A_BOOK.code());

        verify(mockPrintStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageWhenUserTryToCheckoutAInvalidBook() throws IOException {
        libraryService = new LibraryService(library, mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("Pinocchio");

        libraryService.showOption(CHECKOUT_A_BOOK.code());

        verify(mockPrintStream).println("Sorry, that book is not available");
    }

    @Test
    public void shouldPrintSuccessMessageWhenUserReturnABook() throws IOException {
        libraryService = new LibraryService(library, mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("Clean Code");

        libraryService.showOption(CHECKOUT_A_BOOK.code());
        libraryService.showOption(RETURN_A_BOOK.code());

        verify(mockPrintStream).println("Thank you for returning the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageWhenUserTryToReturnAInvalidBook() throws IOException {
        libraryService = new LibraryService(library, mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("Pinocchio");

        libraryService.showOption(RETURN_A_BOOK.code());

        verify(mockPrintStream).println("This is not a valid book to return");
    }
}