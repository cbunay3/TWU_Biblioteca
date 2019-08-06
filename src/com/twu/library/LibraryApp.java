package com.twu.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.twu.library.Option.LIST_OF_BOOKS;

public class LibraryApp {

    private static Library library;
    private static BufferedReader bufferedReader;

    public static void main(String[] args) {
        library = new Library(books(), System.out);
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        library.viewWelcomeMessage();
        menu();
    }

    private static List<Book> books(){
        List<Book> books = new ArrayList<>();
        books.add(new Book("Head First Java","Kathy Sierra",2003));
        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        books.add(new Book("The Agile Samurai","Jonathan Rasmusson", 2010));
        return books;
    }

    private static void menu(){
        System.out.println("\n  Menu");
        System.out.println("1.List of Books");
        System.out.println("Enter an option...");
        Integer option = enterOption();
        while (isValidOption(option)) {
            showOption(option);
            menu();
        }
    }

    private static void showOption(Integer option) {
       if (option == LIST_OF_BOOKS.code()){
            library.listBooks();
            return;
       }
       else{
           System.out.println("Please Select a valid option!");
       }
    }

    private static boolean isValidOption(Integer option){
        if (option !=0)
            return true;
        return false;
    }

    private static Integer enterOption(){
        String option = readLine();
        return Integer.parseInt(option);
    }

    private static String readLine() {
        String option = null;
        try {
            option = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return option;
    }

}