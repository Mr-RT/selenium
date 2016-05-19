package com.neurio.tests;

import com.neurio.tests.shared.BasicTest;
import com.neurio.tests.shared.UserBar;
import com.neurio.tests.shared.LoginPage;
import org.testng.annotations.Test;

/**
 * Created by Robert on 2016-05-17.
 * Admin Test
 */
public class AdminTest extends BasicTest{

    @Test
    public void AdminTest() {
        String ADMIN_LOGIN = "admin@energy-aware.com";
        String ADMIN_PASSWORD = "bonny5_worktable";
        String WINDSOR = "windsor";
        String WINDSOR_ID = "yVqQsLETRk2C1D1EjlgEjA";

        LoginPage.signIn(ADMIN_LOGIN, ADMIN_PASSWORD);

        UserBar.toggleUserMenu();

        UserBar.adminSelectUserByNameID(WINDSOR, WINDSOR_ID);
    }
}