package com.twu.library;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private List<Book> borrowedBooks;
    private List<Movie> movies;
    private PrintStream printStream;


    public Library(PrintStream printStream) {
        this.books = createBooks();
        this.printStream = printStream;
        this.borrowedBooks = new ArrayList<>();
        this.movies = createMovies();
    }

    public void showWelcomeMessage() {
        printStream.print("Welcome to Biblioteca. Your one-stop/shop for great good titles in Bangalore!");
    }

    public void listBooks() {
        StringBuilder bookList = new StringBuilder();
        books.forEach(book -> bookList.append(book.getInfo()));
        printStream.print(bookList.toString());
    }

    public void listMovies() {
        StringBuilder moviesInfo = new StringBuilder();
        movies.forEach(movie -> moviesInfo.append(movie.getInfo()));
        printStream.print( moviesInfo.toString());
    }

    public void checkoutBook(String title) {
        Book book = searchBook(title, books);
        if (book != null) {
            borrowedBooks.add(book);
            books.remove(book);
            printStream.println("Thank you! Enjoy the book");
        } else {
            printStream.println("Sorry, that book is not available");
        }
    }



    public void returnBook(String title) {
        Book book = searchBook(title, borrowedBooks);
        if (book != null) {
            books.add(book);
            borrowedBooks.remove(book);
            printStream.println("Thank you for returning the book");
        } else {
            printStream.println("This is not a valid book to return");
        }
    }

    private Book searchBook(String title, List<Book> books) {
        return books.stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }


    private List<Book> createBooks() {
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

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }



}
