package com.twu.library;

enum MenuOption {
    LIST_BOOKS(1),
    CHECKOUT_A_BOOK(2),
    RETURN_A_BOOK(3),
    QUIT(9);

    private final Integer code;

    private MenuOption(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
