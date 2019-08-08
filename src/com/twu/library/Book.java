package com.twu.library;

public class Book {

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

    public void setAvailable(boolean available) {this.available = available;}

}
