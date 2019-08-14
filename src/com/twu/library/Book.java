package com.twu.library;

public class Book {

    public String title;
    private String author;
    private Integer publicationYear;

    public Book(String title, String author, Integer publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getInfo() {
        return String.format("| %-25s | %-25s | %-17d |\n", title, author, publicationYear);
    }
}
