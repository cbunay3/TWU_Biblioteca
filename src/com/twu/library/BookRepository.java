package com.twu.library;

import java.util.List;

public interface BookRepository {
    void listBooks();
    Book findBookByTitleOnAList(String title, List<Book> books);
    Book findAvailableBook(String title);
    Book findCheckoutBook(String title);
    void checkoutBookByTitle(String title);
    void returnBookByTitle(String title);
    void listCheckoutBooks();
}
