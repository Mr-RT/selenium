package com.neurio.tests;

import com.neurio.tests.shared.BasicTest;
import com.neurio.tests.shared.LoginPage;
import com.neurio.tests.shared.UserBar;
import org.testng.annotations.Test;

/**
 * Created by Robert on 2016-05-17.
 * Admin Test
 */
public class AdminTest extends BasicTest{

    @Test
    public void AdminTest01() {
        String ADMIN_LOGIN = "admin@energy-aware.com";
        String ADMIN_PASSWORD = "bonny5_worktable";
        String WINDSOR = "windsor";
        String WINDSOR_ID = "yVqQsLETRk2C1D1EjlgEjA";

        Report("Admin Test");
        LoginPage.signIn(ADMIN_LOGIN, ADMIN_PASSWORD);

        When("I login as Admin");
        UserBar.toggleUserMenu();

        Then("I can change to a user");
        UserBar.adminSelectUserByNameID(WINDSOR, WINDSOR_ID);
        Report("Admin Test Passed!");
    }
}