package com.twu.library;

import java.io.PrintStream;
import java.security.PrivateKey;
import java.util.*;

public class InMemoryBookRepository implements BookRepository {

    private PrintStream printStream;

    List<Book> books = new ArrayList<>();
    {
        books.add(new Book("Head First Java", "Kathy Sierra", 2003));
        books.add(new Book("The Agile Samurai", "Jonathan Rasmusson", 2010));
    }

    List<Book> checkoutBooks = new ArrayList<>();
    {
        checkoutBooks.add(new Book("Clean Code", "Robert C. Martin", 2008));
    }


    List<String> checkoutUsers = new ArrayList<>();{
        checkoutUsers.add("Carlos");
    }

    InMemoryBookRepository(PrintStream printStream) {
        this.printStream = printStream;
    }


    public void listBooks() {
        StringBuilder bookList = new StringBuilder();
        books.forEach(book -> bookList.append(book.getInfo()));
        printStream.print(bookList.toString());
    }

    public void listCheckoutBooks() {
        StringBuilder checkoutBooksInfo = new StringBuilder();
        for (int i = 0; i < checkoutBooks.size(); i++) {
            checkoutBooksInfo.append(String.format("| %-25s | %-25s |\n", checkoutBooks.get(i).title,checkoutUsers.get(i)));
        }
        printStream.print(checkoutBooksInfo.toString());
    }

    public Book findBookByTitleOnAList(String title, List<Book> books) {
        return books.stream()
                .filter(book -> book.title.equals(title))
                .findFirst()
                .orElse(null);
    }

    public Book findAvailableBook(String title){
        return findBookByTitleOnAList(title, books);
    }

    public Book findCheckoutBook(String title){
        return findBookByTitleOnAList(title, checkoutBooks);
    }

    public void checkoutBookByTitle(String title) {
        Book book = findAvailableBook(title);
        if (book != null) {
            checkoutBooks.add(book);
            checkoutUsers.add(Session.getCurrentSession().currentUser.getName());
            books.remove(book);
            printStream.println("Thank you! Enjoy the book");
        } else {
            printStream.println("Sorry, that book is not available");
        }
    }

    public void returnBookByTitle(String title){
        Book book = findCheckoutBook(title);
        if (book != null) {
            books.add(book);
            checkoutBooks.remove(book);
            printStream.println("Thank you for returning the book");
        } else {
            printStream.println("This is not a valid book to return");
        }
    }
}
