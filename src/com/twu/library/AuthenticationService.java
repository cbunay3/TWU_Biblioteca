package com.twu.library;

import com.twu.library.exception.AuthenticationFailureException;
import com.twu.library.exception.UserNotFoundException;

public class AuthenticationService {
    private UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(String libraryNumber, String password) {
        User foundUser = userRepository.findUserByLibraryNumber(libraryNumber);

        if (foundUser == null) {
            throw new UserNotFoundException(String.format("User with libraryNumber %s could not be found!", libraryNumber));
        }

        if (foundUser.libraryNumber.equals(libraryNumber) && foundUser.password.equals(password)) {
            Session session = Session.getCurrentSession();
            session.setAuthenticatedUser(foundUser);
        } else {
            throw new AuthenticationFailureException("Provided password was incorrect!");
        }
    }
}
