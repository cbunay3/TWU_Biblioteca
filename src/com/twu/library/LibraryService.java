package com.twu.library;

import java.io.BufferedReader;
import java.io.IOException;

import static com.twu.library.MenuOption.*;

public class LibraryService {

    private Library library;
    private BufferedReader bufferedReader;


    LibraryService(Library library, BufferedReader bufferedReader) {
        this.library = library;
        this.bufferedReader = bufferedReader;
    }

    public void begin(){
        library.showWelcomeMessage();
        menu();
    }

    private void menu() {
        Integer option = null;
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

    public void showOption(Integer option) {
        if (option == LIST_BOOKS.code()) {
            System.out.print(String.format("\n  %-25s   %-25s   %-17s\n\n", "TITLE", "AUTHOR", "PUBLICATION YEAR"));
            library.listBooks();
        } else if (option == CHECKOUT_A_BOOK.code()) {
            String bookTitle = enterBookTitle();
            library.checkoutBook(bookTitle);
        } else if (option == RETURN_A_BOOK.code()) {
            String bookTitle = enterBookTitle();
            library.returnBook(bookTitle);
        } else if (isNotQuitOption(option)) {
            System.out.println("Please select a valid option!");
        }
    }

    private boolean isNotQuitOption(Integer option) {
        return !option.equals(QUIT.code());
    }

    public Integer enterOption() {
        try {
            return  Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String enterBookTitle() {
        System.out.println("Enter book's title:");
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
