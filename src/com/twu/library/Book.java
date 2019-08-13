package com.twu.library;

public class Book {

    private String title;
    private String author;
    private Integer year;

    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return String.format("| %-25s | %-25s | %-17d |\n", title, author, year);
    }
}
