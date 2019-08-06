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

    public void viewWelcomeMessage(){
        printStream.println("Welcome to Biblioteca. Your one-stop/shop for great good titles in Bangalore!");
    }

    public void listBooks() {
        String bookList = "";
        for (Book book : books) {
            bookList += book.getTitle() + ",  " + book.getAuthor() +  ",  " + book.getYear().toString() + "\n";
        }
        printStream.println(bookList);
    }
}
