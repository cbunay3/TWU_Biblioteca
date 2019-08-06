package com.twu.library;

enum Option {
    LIST_OF_BOOKS(1);

    private final Integer code;

    private Option(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return 1;
    }
}
