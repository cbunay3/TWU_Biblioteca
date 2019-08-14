package com.twu.library;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InMemoryBookRepositoryTest {

    private PrintStream mockPrintStream;

    @Before
    public void setUp() {
        mockPrintStream = mock(PrintStream.class);
    }

    @Test
    public void findBookSuccessful() {
        String title = "Clean Code";
        BookRepository bookRepository = new InMemoryBookRepository(mockPrintStream);

        Book book = bookRepository.findAvailableBook(title);

        assertThat(book.title, is("Clean Code"));
    }

    @Test
    public void findBookUnsuccesful() {
        String title = "Pinocchio";
        BookRepository bookRepository = new InMemoryBookRepository(mockPrintStream);

        Book book = bookRepository.findAvailableBook(title);

        assertThat(book,is(nullValue()));
    }

    @Test
    public void shouldNotPrintBorrowedBooksInfo() throws IOException {
        Book expectedBook = new Book("Head First Java", "Kathy Sierra", 2003);
        BookRepository bookRepository = new InMemoryBookRepository(mockPrintStream);

        bookRepository.checkoutBookByTitle("Clean Code");
        bookRepository.checkoutBookByTitle("The Agile Samurai");
        bookRepository.listBooks();

        verify(mockPrintStream).print(expectedBook.getInfo());
    }

    @Test
    public void shouldPrintSuccessMessageOnCheckoutOfABook() throws IOException {
        BookRepository bookRepository = new InMemoryBookRepository(mockPrintStream);

        bookRepository.checkoutBookByTitle("Clean Code");

        verify(mockPrintStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageOnCheckoutAInvalidOfABook() throws IOException {
        BookRepository bookRepository = new InMemoryBookRepository(mockPrintStream);

        bookRepository.checkoutBookByTitle("Pinocchio");

        verify(mockPrintStream).println("Sorry, that book is not available");
    }


    @Test
    public void shouldNotPrintBorrowedMoviesInfo() throws IOException {
        Book expectedBook = new Book("Head First Java", "Kathy Sierra", 2003);
        BookRepository bookRepository = new InMemoryBookRepository(mockPrintStream);

        bookRepository.checkoutBookByTitle("Clean Code");
        bookRepository.checkoutBookByTitle("The Agile Samurai");
        bookRepository.listBooks();

        verify(mockPrintStream).print(expectedBook.getInfo());
    }

}