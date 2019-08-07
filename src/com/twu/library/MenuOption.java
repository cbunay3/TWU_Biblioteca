package com.twu.library;

enum MenuOption {
    LIST_OF_BOOKS(1),
    QUIT(9);

    private final Integer code;

    private MenuOption(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
