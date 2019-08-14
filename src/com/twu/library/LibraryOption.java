package com.twu.library;

enum LibraryOption {
    LIST_BOOKS("1"),
    CHECKOUT_BOOK("2"),
    RETURN_BOOK("3"),
    LIST_MOVIES("4"),
    CHECKOUT_MOVIE("5"),
    BOOKS_CHECKED_OUT("6"),
    QUIT("9");

    private final String code;

    private LibraryOption(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
