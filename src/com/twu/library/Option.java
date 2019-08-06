package com.twu.library;

enum Option {
    LIST_OF_BOOKS(1),
    QUIT(9);


    private final Integer code;

    private Option(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
