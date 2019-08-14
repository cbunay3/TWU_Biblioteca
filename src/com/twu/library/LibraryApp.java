package com.twu.library;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class LibraryApp {

    public static void main(String[] args) {
        LibraryMenu menu = new LibraryMenu(System.out,new BufferedReader(new InputStreamReader(System.in)));
        menu.show();
    }
}