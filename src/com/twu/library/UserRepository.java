package com.twu.library;

public interface UserRepository {

    User findUserByLibraryNumber(String username);
}
