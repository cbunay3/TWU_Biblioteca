package com.twu.library;

public class User {

    private String name;
    private String email;
    private String phoneNumber;
    public String libraryNumber;
    public String password;

    public User(String name, String email, String phoneNumber, String libraryNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getInfo(){
        return String.format("Name: %s\nEmail: %s\nPhone Number: %s\n", name, email,phoneNumber);
    }

}