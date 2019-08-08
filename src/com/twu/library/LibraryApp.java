package com.twu.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.twu.library.MenuOption.*;

public class LibraryApp {

    private static Library library;
    private static Scanner scanner;

    public static void main(String[] args) {
        library = new Library(books(), System.out);
        scanner = new Scanner(System.in);
        library.viewWelcomeMessage();
        menu();
    }

    private static List<Book> books() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Head First Java", "Kathy Sierra", 2003));
        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        books.add(new Book("The Agile Samurai", "Jonathan Rasmusson", 2010));
        return books;
    }

    private static void menu() {
        Integer option = null;
        do {
            System.out.println("\n  Menu");
            System.out.println("1.List of Books");
            System.out.println("2.Checkout a Book");
            System.out.println("9.Quit Application");
            System.out.println("Enter an option...");
            option = enterOption();
            showOption(option);
        } while (isNotQuitOption(option));
    }

    private static void showOption(Integer option) {
        if (option == LIST_OF_BOOKS.code()) {
            System.out.println("\nTITLE  |  AUTHOR  |  PUBLICATION YEAR\n");
            library.listBooks();
        } else if (option == CHECKOUT_A_BOOK.code()) {
            String bookTitle = enterBookTitle();
            library.checkoutBook(bookTitle);
        } else if (isNotQuitOption(option)) {
            System.out.println("Please select a valid option!");
        }
    }

    private static boolean isNotQuitOption(Integer option) {
        return !option.equals(QUIT.code());
    }

    private static Integer enterOption() {
        return scanner.nextInt();
    }

    private static String enterBookTitle() {
        System.out.println("Enter book's title:");
        scanner.nextLine();
        return scanner.nextLine();
    }
}