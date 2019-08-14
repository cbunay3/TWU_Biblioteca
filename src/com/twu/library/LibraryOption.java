package com.twu.library;

enum LibraryOption {
    LIST_BOOKS("1"),
    CHECKOUT_BOOK("2"),
    RETURN_BOOK("3"),
    QUIT("9");

    private final String code;

    private LibraryOption(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
