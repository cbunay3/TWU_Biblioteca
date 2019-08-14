package com.twu.library;

public interface MovieRepository {

    void listMovies();
    Movie findMovieByName(String name);
    void checkoutMovieByName(String name);
}
