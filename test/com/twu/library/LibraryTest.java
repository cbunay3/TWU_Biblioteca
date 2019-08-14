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

public class LibraryTest {
    private Library library;
    private PrintStream mockPrintStream;
    private BufferedReader mockBufferedReader;
    private BookRepository bookRepository;
    private MovieRepository movieRepository;


    @Before
    public void setUp() {
        mockPrintStream = mock(PrintStream.class);
        mockBufferedReader = mock(BufferedReader.class);
        bookRepository = new InMemoryBookRepository(mockPrintStream);
        movieRepository = new InMemoryMovieRepository(mockPrintStream);
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

    public List<Movie> createMovies() {
        List<Movie> movies = new ArrayList<>();{
            movies.add(new Movie("The Lion King", 2019, "Jon Favreau", "9"));
            movies.add(new Movie("Jumper", 2008, "Doug Liman", "5"));
            movies.add(new Movie("The Avengers", 2012, "Joss Whedon", "9"));
        }
        return movies;
    }

    public String expectedMoviesInfo() {
        StringBuilder moviesInfo = new StringBuilder();
        createMovies().forEach(book -> moviesInfo.append(book.getInfo()));
        return moviesInfo.toString();
    }


    @Test
    public void shouldPrintBooksInfoWhenUserListBooks() throws IOException {
        library = new Library(bookRepository,movieRepository,mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("123-4567","12345",LIST_BOOKS.code(), QUIT.code());

        library.authenticate();

        verify(mockPrintStream).print(expectedBooksInfo().toString());
    }

    @Test
    public void shouldPrintSuccessMessageWhenUserCheckoutABook() throws IOException {
        library = new Library(bookRepository,movieRepository,mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("123-4567","12345",CHECKOUT_BOOK.code(),"Clean Code", QUIT.code());

        library.authenticate();

        verify(mockPrintStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageWhenUserTryToCheckoutAInvalidBook() throws IOException {
        library = new Library(bookRepository,movieRepository,mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("123-4567","12345",CHECKOUT_BOOK.code(),"Pinocchio",QUIT.code());

        library.authenticate();

        verify(mockPrintStream).println("Sorry, that book is not available");
    }

    @Test
    public void shouldPrintSuccessMessageWhenUserReturnABook() throws IOException {
        library = new Library(bookRepository,movieRepository,mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("123-4567","12345",CHECKOUT_BOOK.code(),"Clean Code",RETURN_BOOK.code(),"Clean Code",QUIT.code());

        library.authenticate();

        verify(mockPrintStream).println("Thank you for returning the book");
    }

    @Test
    public void shouldPrintUnsuccessfulMessageWhenUserTryToReturnAInvalidBook() throws IOException {
        library = new Library(bookRepository,movieRepository,mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("123-4567","12345",RETURN_BOOK.code(),"Pinocchio",QUIT.code());

        library.authenticate();

        verify(mockPrintStream).println("This is not a valid book to return");
    }

    @Test
    public void shouldPrintMoviesInfoWhenUserListMovies() throws IOException {
        library = new Library(bookRepository,movieRepository,mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("123-4567","12345",LIST_MOVIES.code(), QUIT.code());

        library.authenticate();

        verify(mockPrintStream).print(expectedMoviesInfo());
    }

    @Test
    public void shouldCheckoutAMovie() throws IOException {
        library = new Library(bookRepository,movieRepository,mockBufferedReader);
        when(mockBufferedReader.readLine()).thenReturn("123-4567","12345",CHECKOUT_MOVIE.code(),"Jumper", QUIT.code());

        library.authenticate();

        verify(mockPrintStream).println("Thank you! Enjoy the movie");
    }

}