package com.twu.library;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InMemoryMovieRepositoryTest {
    private PrintStream mockPrintStream;

    @Before
    public void setUp() {
        mockPrintStream = mock(PrintStream.class);
    }

    @Test
    public void findMovieSuccessful() {
        String name = "The Avengers";
        MovieRepository movieRepository = new InMemoryMovieRepository(mockPrintStream);

        Movie movie = movieRepository.findMovieByName(name);

        assertThat(movie.name, is("The Avengers"));
    }

    @Test
    public void findMovieUnsuccesful() {
        String name = "Pinocchio";
        MovieRepository movieRepository = new InMemoryMovieRepository(mockPrintStream);

        Movie movie = movieRepository.findMovieByName(name);

        assertThat(movie,is(nullValue()));
    }

    @Test
    public void shouldNotPrintBorrowedMoviesInfo() throws IOException {
        Movie expectedMovie = new Movie("The Lion King", 2019, "Jon Favreau", "9");
        MovieRepository movieRepository = new InMemoryMovieRepository(mockPrintStream);

        movieRepository.checkoutMovieByName("Jumper");
        movieRepository.checkoutMovieByName("The Avengers");
        movieRepository.listMovies();

        verify(mockPrintStream).print(expectedMovie.getInfo());
    }

}