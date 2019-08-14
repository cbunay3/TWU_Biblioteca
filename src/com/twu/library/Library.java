package com.twu.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static com.twu.library.LibraryOption.*;

public class Library {

    private BufferedReader bufferedReader;
    private BookRepository bookRepository;
    private MovieRepository movieRepository;
    private AuthenticationService authenticationService;

    Library(BookRepository bookRepository, MovieRepository movieRepository, BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        this.bookRepository = bookRepository;
        this.movieRepository = movieRepository;
    }

    public void showMenu() {
        String option = null;
        do {
            System.out.println("\n  Menu");
            System.out.println("1.List Books");
            System.out.println("2.Checkout a Book");
            System.out.println("3.Return a Book");
            System.out.println("4.List Movies");
            System.out.println("5.Checkout a Movie");
            System.out.println("6.List checked out books");
            System.out.println("7.My profile");
            System.out.println("9.Quit Application");
            System.out.println("Enter an option...");
            option = readLine();
            showOption(option);
        } while (isNotQuitOption(option));
    }

    private void showWelcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop/shop for great good titles in Bangalore!\n");
    }

    private void showOption(String option) {
        if (option.equals(LIST_BOOKS.code())) {
            System.out.print(String.format("\n  %-25s   %-25s   %-17s\n\n", "TITLE", "AUTHOR", "PUBLICATION YEAR"));
            bookRepository.listBooks();
        } else if (option.equals(CHECKOUT_BOOK.code())) {
            System.out.println("Enter book's title:");
            String bookTitle = readLine();
            bookRepository.checkoutBookByTitle(bookTitle);
        } else if (option.equals(RETURN_BOOK.code())) {
            System.out.println("Enter book's title:");
            String bookTitle = readLine();
            bookRepository.returnBookByTitle(bookTitle);
        } else if (option.equals(LIST_MOVIES.code())) {
            System.out.print(String.format("\n| %-23s | %-15s | %-23s | %-15s |\n\n", "NAME", "YEAR", "DIRECTOR", "RATING"));
            movieRepository.listMovies();
        } else if (option.equals(CHECKOUT_MOVIE.code())) {
            System.out.println("Enter movie's title:");
            String movieName = readLine();
            movieRepository.checkoutMovieByName(movieName);
        } else if (option.equals(BOOKS_CHECKED_OUT.code())) {
            System.out.println(String.format("\n| %-25s | %-25s |\n","LIBRO","RESERVADO POR"));
            bookRepository.listCheckoutBooks();
        }else if (option.equals(MY_PROFILE.code())) {
            System.out.println(String.format("\nMY INFORMATION\n"));
            System.out.println(Session.getCurrentSession().currentUser.getInfo());
        }else if (isNotQuitOption(option)) {
            System.out.println("Please select a valid option!");
        }
    }

    private boolean isNotQuitOption(String option) {
        return !option.equals(QUIT.code());
    }

    private String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void authenticate() {
        System.out.println("Welcome to Biblioteca. Your one-stop/shop for great good titles in Bangalore!\n");
        System.out.println("LOGIN");
        System.out.println("Enter your library number:");
        String libraryNumber = readLine();
        System.out.println("Enter your password:");
        String password = readLine();
        UserRepository userRepository = new InMemoryUserRepository();
        authenticationService = new AuthenticationService(userRepository);
        authenticationService.login(libraryNumber, password);
        Session userSesion = Session.getCurrentSession();
        if (userSesion.currentUser != null) {
            showMenu();
        }
    }
}
