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


    public List<Book> createBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Head First Java", "Kathy Sierra", 2003));
        books.add(new Book("The Agile Samurai", "Jonathan Rasmusson", 2010));
        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        return books;
    }

    public List<Movie> createMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("The Lion King", 2019, "Jon Favreau", "9"));
        movies.add(new Movie("Jumper", 2008, "Doug Liman", "5"));
        movies.add(new Movie("The Avengers", 2012, "Joss Whedon", "9"));
        return movies;
    }

    public String expectedBooksInfo() {
        StringBuilder booksInfo = new StringBuilder();
        createBooks().forEach(book -> booksInfo.append(book.getInfo()));
        return booksInfo.toString();
    }

    public String expectedMoviesInfo() {
        StringBuilder moviesInfo = new StringBuilder();
        createMovies().forEach(movie -> moviesInfo.append(movie.getInfo()));
        return moviesInfo.toString();
    }

    public String expectedBookInfo() {
        return createBooks().remove(0).getInfo();
    }

    public String expectedMovieInfo() {
        return createMovies().remove(0).getInfo();
    }

    @Test
    public void shouldPrintWelcomeMessage() {
        library = new Library(mockPrintStream);

        library.showWelcomeMessage();

        verify(mockPrintStream).print("Welcome to Biblioteca. Your one-stop/shop for great good titles in Bangalore!");
    }

    @Test
    public void shouldPrintNothingWhenThereAreNoBooks() {
        library = new Library(mockPrintStream);
        library.setBooks(new ArrayList<>());

        library.listBooks();

        verify(mockPrintStream).print("");
    }

    @Test
    public void shouldPrintBooksInfoWhenThereAreMoreThanOneBooks() throws IOException {
        library = new Library(mockPrintStream);

        library.listBooks();

        verify(mockPrintStream).print(expectedBooksInfo());
    }

    @Test
    public void shouldNotPrintBorrowedBooksInfo() throws IOException {
        library = new Library(mockPrintStream);

        library.checkoutBook("Clean Code");
        library.checkoutBook("The Agile Samurai");

        library.listBooks();

        verify(mockPrintStream).print(expectedBookInfo());
    }

    @Test
    public void shouldPrintSuccessMessageOnCheckoutOfABook() throws IOException {
        library = new Library(mockPrintStream);

        library.checkoutBook("Clean Code");

        verify(mockPrintStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageOnCheckoutAInvalidOfABook() throws IOException {
        library = new Library(mockPrintStream);

        library.checkoutBook("Pinocchio");

        verify(mockPrintStream).println("Sorry, that book is not available");
    }

    @Test
    public void shouldPrintReturnedBook() throws IOException {
        library = new Library(mockPrintStream);

        library.checkoutBook("Clean Code");
        library.returnBook("Clean Code");
        library.listBooks();

        verify(mockPrintStream).print(expectedBooksInfo());
    }

    @Test
    public void shouldPrintSuccessMessageOnReturnABook() {
        library = new Library(mockPrintStream);

        library.checkoutBook("Clean Code");
        library.returnBook("Clean Code");

        verify(mockPrintStream).println("Thank you for returning the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageOnReturnAInvalidBook() {
        library = new Library(mockPrintStream);

        library.returnBook("Pinocchio");

        verify(mockPrintStream).println("This is not a valid book to return");
    }

    @Test
    public void shouldPrintAvailableBooks() {
        library = new Library(mockPrintStream);

        library.listMovies();

        verify(mockPrintStream).print(expectedMoviesInfo());
    }

    @Test
    public void shouldNotPrintNothingWhenThereIsNoMovies() {
        library = new Library(mockPrintStream);
        library.setMovies(new ArrayList<>());

        library.listMovies();

        verify(mockPrintStream).print("");
    }


}
