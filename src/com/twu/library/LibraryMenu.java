package com.twu.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static com.twu.library.LibraryOption.*;

public class LibraryMenu {

    private Library library;
    private BufferedReader bufferedReader;


    LibraryMenu(PrintStream printStream, BufferedReader bufferedReader) {
        this.library = new Library(printStream);
        this.bufferedReader = bufferedReader;
    }

    public void show() {
        library.showWelcomeMessage();
        String option = null;
        do {
            System.out.println("\n  Menu");
            System.out.println("1.List of Books");
            System.out.println("2.Checkout a Book");
            System.out.println("3.Return a Book");
            System.out.println("9.Quit Application");
            System.out.println("Enter an option...");
            option = enterOption();
            showOption(option);
        } while (isNotQuitOption(option));
    }

    private void showOption(String option) {
        if (option.equals(LIST_BOOKS.code())) {
            System.out.print(String.format("\n  %-25s   %-25s   %-17s\n\n", "TITLE", "AUTHOR", "PUBLICATION YEAR"));
            library.listBooks();
        } else if (option.equals(CHECKOUT_BOOK.code())) {
            String bookTitle = enterBookTitle();
            library.checkoutBook(bookTitle);
        } else if (option.equals(RETURN_BOOK.code())) {
            String bookTitle = enterBookTitle();
            library.returnBook(bookTitle);
        } else if (isNotQuitOption(option)) {
            System.out.println("Please select a valid option!");
        }
    }

    private boolean isNotQuitOption(String option) {
        return !option.equals(QUIT.code());
    }

    private String enterOption() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String enterBookTitle() {
        System.out.println("Enter book's title:");
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
