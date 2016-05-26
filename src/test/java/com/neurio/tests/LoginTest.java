package com.neurio.tests;

import com.neurio.tests.shared.BasicTest;
import com.neurio.tests.shared.LoginPage;
import com.neurio.tests.shared.UserBar;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
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

        Report("Login Test");
        When("LoginPage Case - Invalid Email and Incorrect Password");

        LoginPage.enterFields(EMAIL + "@email.com", PASSWORD);

        Then("Check for Error to Appear");
        Assert.assertTrue(LoginPage.assertError(), "No Error Appeared");
        LoginPage.clearFields();

        When("LoginPage Case - Invalid Email and Correct Password");

        LoginPage.enterFields(EMAIL + "@email.com", ADMIN_PASSWORD);

        Then("Check for Error to Appear");
        Assert.assertTrue(LoginPage.assertError(), "No Error Appeared");
        LoginPage.clearFields();

        When("LoginPage Case - Correct Email and Invalid Password");
        LoginPage.enterFields(ADMIN_LOGIN, PASSWORD);

        Then("Check for Error to Appear");
        Assert.assertTrue(LoginPage.assertError(), "No Error Appeared");
        LoginPage.clearFields();

        When("LoginPage Case - Valid Email and Password");
        LoginPage.signIn(ADMIN_LOGIN, ADMIN_PASSWORD);

        Then("Check if user name is correct in the top right of screen");
        UserBar.assertUserName(ADMIN);

        Report("Login Test passed!");
    }
}