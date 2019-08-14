package com.twu.library;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class InMemoryMovieRepository implements MovieRepository {

    PrintStream printStream;

    List<Movie> movies = new ArrayList<>();{
        movies.add(new Movie("The Lion King", 2019, "Jon Favreau", "9"));
        movies.add(new Movie("Jumper", 2008, "Doug Liman", "5"));
        movies.add(new Movie("The Avengers", 2012, "Joss Whedon", "9"));
    }


    List<Movie> checkoutMovies = new ArrayList<>();

    List<User> checkoutUsers = new ArrayList<>();

    InMemoryMovieRepository(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void listMovies() {
        StringBuilder moviesList = new StringBuilder();
        movies.forEach(book -> moviesList.append(book.getInfo()));
        printStream.print(moviesList.toString());
    }

    public Movie findMovieByName(String name) {
        return movies.stream()
                .filter(movie -> movie.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public void checkoutMovieByName(String name) {
        Movie movie = findMovieByName(name);
        if (movie != null) {
            checkoutMovies.add(movie);
            movies.remove(movie);
            printStream.println("Thank you! Enjoy the movie");
        } else {
            printStream.println("Sorry, that movie is not available");
        }
    }


}
