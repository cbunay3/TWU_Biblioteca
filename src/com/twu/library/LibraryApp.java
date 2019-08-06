package com.twu.library;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LibraryApp {

    public static void main(String[] args) {
        Library library = new Library(books(), System.out);
        library.viewWelcomeMessage();
        library.listBooks();
    }

    private static List<Book> books(){
        List<Book> books = new ArrayList<>();
        books.add(new Book("Head First Java"));
        books.add(new Book("Test Driven Development by Example"));
        books.add(new Book("The Agile Samurai"));
        return books;
    }
}
