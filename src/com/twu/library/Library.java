package com.twu.library;

import java.io.PrintStream;
import java.util.List;

public class Library {

    private List<Book> books;
    private PrintStream printStream;

    public Library(List<Book> books, PrintStream printStream) {
        this.books = books;
        this.printStream = printStream;
    }

    public void viewWelcomeMessage() {
        printStream.println("Welcome to Biblioteca. Your one-stop/shop for great good titles in Bangalore!");
    }

    public void listBooks() {
        String columnSeparator = " | ";
        String bookList = "";
        for (Book book : books) {
            if (book.isAvailable())
                bookList += book.getTitle() + columnSeparator + book.getAuthor() + columnSeparator + book.getYear().toString() + "\n";
        }
        printStream.println(bookList);
    }

    public List<Book> getListBooks() {
        return books;
    }

    public void checkoutBook(String title) {
        Book book = searchBook(title);
        if (book != null) {
            book.setAvailable(false);
            printStream.println("Thank you! Enjoy the book");
        } else {
            printStream.println("Sorry, that book is not available");
        }
    }

    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public void returnBook(String title) {
        Book book = searchBook(title);
        if (book != null) {
            book.setAvailable(true);
            printStream.println("Thank you for returning the book");
        }
        else {
            printStream.println("This is not valid book to return");
        }
    }
}
