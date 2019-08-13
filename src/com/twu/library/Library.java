package com.twu.library;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books;
    private List<Book> borrowedBooks;
    private PrintStream printStream;


    public Library(List<Book> books, PrintStream printStream) {
        this.books = books;
        this.printStream = printStream;
        this.borrowedBooks = new ArrayList<>();
    }

    public void showWelcomeMessage() {
        printStream.print("Welcome to Biblioteca. Your one-stop/shop for great good titles in Bangalore!");
    }

    public void listBooks() {
        StringBuilder bookList = new StringBuilder();
        books.forEach(book -> bookList.append(book.getInfo()));
        printStream.print(bookList.toString());
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

    public Book searchBook(String title, List<Book> books) {
        return books.stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }
}
