package com.neurio.tests;

import com.neurio.tests.shared.BasicTest;
import com.neurio.tests.shared.UserBar;
import com.neurio.tests.shared.LoginPage;
import org.testng.annotations.*;
import java.lang.*;

/*
 * Created by Robert T. on 2016-05-16.
 * LoginTest
 */
public class LoginTest extends BasicTest {

    @Test
    public void LoginPageTest() {
        String EMAIL = "email";
        String PASSWORD = "password";
        String ADMIN = "admin";
        String ADMIN_LOGIN = "admin@energy-aware.com";
        String ADMIN_PASSWORD = "bonny5_worktable";

        // LoginPage Case - Invalid Email and Incorrect Password

        LoginPage.enterFields(EMAIL + "@email.com", PASSWORD);

        // Wait for Error
        LoginPage.assertError();
        LoginPage.clearFields();

        // LoginPage Case - Invalid Email and Correct Password

        LoginPage.enterFields(EMAIL + "@email.com", ADMIN_PASSWORD);

        // Wait for Error
        LoginPage.assertError();

        LoginPage.clearFields();

        // LoginPage Case - Correct Email and Invalid Password
        // Enter something

        LoginPage.enterFields(ADMIN_LOGIN, PASSWORD);

        // Wait for Error
        LoginPage.assertError();

        LoginPage.clearFields();

        // LoginPage Case - Valid Email and Password
        LoginPage.signIn(ADMIN_LOGIN, ADMIN_PASSWORD);

        //Check if user name is correct in the top right of screen
        UserBar.assertUserName(ADMIN);
    }
}