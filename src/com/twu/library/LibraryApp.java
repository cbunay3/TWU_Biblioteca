package com.twu.library;

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
        books.add(new Book("Head First Java","Kathy Sierra",2003));
        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        books.add(new Book("The Agile Samurai","Jonathan Rasmusson", 2010));
        return books;
    }
}