package com.twu.library;

public class Book implements Actions {

    private String title;
    private String author;
    private Integer year;
    private  Boolean available;

    public Book(String title, String author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public Boolean isAvailable() {return available;}

    @Override
    public void checkout() {
        available = false;
    }
}
