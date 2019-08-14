package com.twu.library;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class LibraryApp {

    public static void main(String[] args) {
        BookRepository bookRepository = new InMemoryBookRepository(System.out);
        MovieRepository movieRepository = new InMemoryMovieRepository(System.out);
        Library library = new Library(bookRepository,movieRepository,new BufferedReader(new InputStreamReader(System.in)));
        library.showMenu();
    }
}