package com.twu.library;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static com.twu.library.LibraryOption.*;
import static org.mockito.Mockito.*;

public class LibraryMenuTest {
    private LibraryMenu libraryMenu;
    private PrintStream mockPrintStream;
    private BufferedReader mockBufferedReader;


    @Before
    public void setUp() {
        mockPrintStream = mock(PrintStream.class);
        mockBufferedReader = mock(BufferedReader.class);
    }

    public List<Book> createBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Head First Java", "Kathy Sierra", 2003));
        books.add(new Book("The Agile Samurai", "Jonathan Rasmusson", 2010));
        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        return books;
    }

    public String expectedBooksInfo() {
        StringBuilder booksInfo = new StringBuilder();
        createBooks().forEach(book -> booksInfo.append(book.getInfo()));
        return booksInfo.toString();
    }

    @Test
    public void shouldPrintBooksInfoWhenUserListBooks() throws IOException {
        libraryMenu = new LibraryMenu(mockPrintStream, mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn(LIST_BOOKS.code(), QUIT.code());

        libraryMenu.show();

        verify(mockPrintStream).print(expectedBooksInfo());
    }

    @Test
    public void shouldPrintSuccessMessageWhenUserCheckoutABook() throws IOException {
        libraryMenu = new LibraryMenu(mockPrintStream, mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn(CHECKOUT_BOOK.code(),"Clean Code", QUIT.code());

        libraryMenu.show();

        verify(mockPrintStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageWhenUserTryToCheckoutAInvalidBook() throws IOException {
        libraryMenu = new LibraryMenu(mockPrintStream, mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn(CHECKOUT_BOOK.code(),"Pinocchio",QUIT.code());

        libraryMenu.show();

        verify(mockPrintStream).println("Sorry, that book is not available");
    }

    @Test
    public void shouldPrintSuccessMessageWhenUserReturnABook() throws IOException {
        libraryMenu = new LibraryMenu(mockPrintStream, mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn(CHECKOUT_BOOK.code(),"Clean Code",RETURN_BOOK.code(),"Clean Code",QUIT.code());

        libraryMenu.show();

        verify(mockPrintStream).println("Thank you for returning the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageWhenUserTryToReturnAInvalidBook() throws IOException {
        libraryMenu = new LibraryMenu(mockPrintStream, mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn(RETURN_BOOK.code(),"Pinocchio",QUIT.code());

        libraryMenu.show();

        verify(mockPrintStream).println("This is not a valid book to return");
    }
}