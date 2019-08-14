package com.twu.library;

import com.twu.library.exception.AuthenticationFailureException;
import com.twu.library.exception.UserNotFoundException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class AuthenticationServiceTest {

    @Test
    public void UserLoginWithCorrectCredentials() {
        String libraryNumber = "123-4567";
        String password = "12345";
        UserRepository userRepository = new InMemoryUserRepository();
        AuthenticationService authenticationService = new AuthenticationService(userRepository);

        authenticationService.login(libraryNumber,password);

        Session userSession = Session.getCurrentSession();
        assertThat(userSession.getAuthenticatedUser().libraryNumber,is("123-4567"));
        assertThat(userSession.getAuthenticatedUser().password,is("12345"));
    }

    @Test(expected = UserNotFoundException.class)
    public void UserLoginWhereUserDoesNotExit() {
        String libraryNumber = "723-7464";
        String password = "hello123";
        UserRepository userRepository = new InMemoryUserRepository();
        AuthenticationService authenticationService = new AuthenticationService(userRepository);

        authenticationService.login(libraryNumber, password);
    }


    @Test(expected = AuthenticationFailureException.class)
    public void UserLoginWhereUserPasswordIsIncorrect() throws Exception {
        String libraryNumber = "123-4567";
        String password = "ceee2";
        UserRepository userRepository = new InMemoryUserRepository();
        AuthenticationService authenticationService = new AuthenticationService(userRepository);

        authenticationService.login(libraryNumber, password);
    }
}