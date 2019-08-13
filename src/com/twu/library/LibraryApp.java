package com.twu.library;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class LibraryApp {

    private static LibraryService libraryService;

    public static void main(String[] args) {
        libraryService = new LibraryService(new Library(books(), System.out), new BufferedReader(new InputStreamReader(System.in)));
        libraryService.begin();
    }

    private static List<Book> books() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Head First Java", "Kathy Sierra", 2003));
        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        books.add(new Book("The Agile Samurai", "Jonathan Rasmusson", 2010));
        return books;
    }
}