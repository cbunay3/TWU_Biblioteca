package com.twu.library;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class LibraryTest {

    private Library library;
    private PrintStream mockPrintStream;

    @Before
    public void setUp() {
        mockPrintStream = mock(PrintStream.class);
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

    public String expectedBookInfo() {
        return books().get(0).getInfo();
    }

    @Test
    public void shouldPrintWelcomeMessage() {
        library = new Library(new ArrayList<>(), mockPrintStream);

        library.showWelcomeMessage();

        verify(mockPrintStream).print("Welcome to Biblioteca. Your one-stop/shop for great good titles in Bangalore!");
    }

    @Test
    public void shouldPrintNothingWhenThereAreNoBooks() {
        library = new Library(new ArrayList<>(), mockPrintStream);

        library.listBooks();

        verify(mockPrintStream).print("");
    }

    @Test
    public void shouldPrintBothBookInfoWhenThereAreTwoBooks() throws IOException {
        library = new Library(books(), mockPrintStream);

        library.listBooks();

        verify(mockPrintStream).print(expectedBooksInfo());
    }

    @Test
    public void shouldNotPrintBorrowedBookInfo() throws IOException {
        library = new Library(books(), mockPrintStream);

        library.checkoutBook("Clean Code");
        library.listBooks();

        verify(mockPrintStream).print(expectedBookInfo());
    }

    @Test
    public void shouldPrintSuccessMessageOnCheckoutOfABook() throws IOException {
        library = new Library(books(), mockPrintStream);

        library.checkoutBook("Clean Code");

        verify(mockPrintStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageOnCheckoutAInvalidOfABook() throws IOException {
        library = new Library(books(), mockPrintStream);

        library.checkoutBook("Pinocchio");

        verify(mockPrintStream).println("Sorry, that book is not available");
    }

    @Test
    public void shouldPrintReturnedBook() throws IOException {
        library = new Library(books(), mockPrintStream);

        library.checkoutBook("Clean Code");
        library.returnBook("Clean Code");
        library.listBooks();

        verify(mockPrintStream).print(expectedBooksInfo());
    }

    @Test
    public void shouldPrintSuccessMessageOnReturnABook() {
        library = new Library(books(), mockPrintStream);

        library.checkoutBook("Clean Code");
        library.returnBook("Clean Code");

        verify(mockPrintStream).println("Thank you for returning the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageOnReturnAInvalidBook() {
        library = new Library(books(), mockPrintStream);

        library.returnBook("Pinocchio");

        verify(mockPrintStream).println("This is not a valid book to return");
    }
}
