package com.twu.library;

public class Movie {

    private String name;
    private Integer year;
    private String director;
    private String rating;

    public Movie(String name, Integer year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getName (){
        return this.name;
    }


    public String getInfo() {
        return String.format("| %-23s | %-15d | %-23s | %-15s |\n", name, year, director, rating);
    }
}
