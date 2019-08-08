package com.twu.library;

enum MenuOption {
    LIST_OF_BOOKS(1),
    CHECKOUT_A_BOOK(2),
    QUIT(9);

    private final Integer code;

    private MenuOption(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
