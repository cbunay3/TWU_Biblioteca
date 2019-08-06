package com.twu.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.twu.library.Option.LIST_OF_BOOKS;
import static com.twu.library.Option.QUIT;

public class LibraryApp {

    private static Library library;

    public static void main(String[] args) {
        library = new Library(books(), System.out);

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
            System.out.println("9.Quit Application");
            System.out.println("Enter an option...");
            option = enterOption();
            showOption(option);
        } while (isNotQuitOption(option));
    }

    private static boolean isNotQuitOption(Integer option) {
        if (!option.equals(QUIT.code())) {
            return true;
        }
        return false;
    }

    private static void showOption(Integer option) {
        if (option == LIST_OF_BOOKS.code()) {
            library.listBooks();
        } else if (isNotQuitOption(option)) {
            System.out.println("Please Select a valid option!");
        }
    }

    private static Integer enterOption() {
        Integer option;
        Scanner s = new Scanner(System.in);
        option = s.nextInt();
        return option;
    }
}