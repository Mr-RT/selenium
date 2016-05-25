package com.neurio.tests;

import com.neurio.tests.shared.*;
import org.testng.annotations.Test;

/**
 * Created by Robert on 5/24/2016.
 * Settings Test
 */
public class SettingsTests extends BasicTest {
    String LOGIN = "robert+89891@neur.io";
    String PASSWORD = "kashani1234";

    @Test
    public void SettingsPageTest01() {

        String NEW_EMAIL = "robert+89892@neur.io";

        String ACCOUNT_NAME = "Robert89891";
        String NEW_ACCOUNT_NAME = ACCOUNT_NAME + " NEW";
        String BAD_ACCOUNT_NAME = "abcdefghijklmnopqrstuvuxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String BAD_PASSWORD = "123456";
        String NEW_PASSWORD = PASSWORD + "new";

        Report("Setting Page Test 01");

        When("I login");
        LoginPage.signIn(LOGIN, PASSWORD);

        UserBar.selectSettings();

        When("I change name to one longer than 50 characters");
        AccountPage.changeAccountName(BAD_ACCOUNT_NAME);

        Then("It should fail");
        AccountPage.checkForErrors();

        When("I change the name, it should work and be checked later on in the test");
        AccountPage.changeAccountName(NEW_ACCOUNT_NAME);

        AccountPage.changePassword(PASSWORD, BAD_PASSWORD);

        Then("It should fail");
        AccountPage.checkForErrors();

        When("I change the password, it should work and be checked later on in the test");
        AccountPage.changePassword(PASSWORD, NEW_PASSWORD);

        SettingsPage.signOut();

        When("I sign in with the new password and check the new account nam");
        LoginPage.signIn(LOGIN, NEW_PASSWORD);

        UserBar.selectSettings();

        Then("It should work now with the new changes");
        UserBar.assertUserName(NEW_ACCOUNT_NAME);

        Report("The email and account name will be changed back");
        AccountPage.changeAccountName(ACCOUNT_NAME);

        AccountPage.changePassword(NEW_PASSWORD, PASSWORD);

        AccountPage.pressChangeEmailButton();

        When("I set new email to one already in use");
        ChangeEmailDialog.changeEmail(PASSWORD, LOGIN);

        Then("It should fail");
        AccountPage.checkForErrors();

        When("I set new email with wrong password");
        ChangeEmailDialog.changeEmail(BAD_PASSWORD, LOGIN);

        Then("It should fail");
        AccountPage.checkForErrors();

        When("I use new emails that do not match");
        ChangeEmailDialog.enterCurrentPassword(PASSWORD);

        ChangeEmailDialog.enterNewEmail(NEW_EMAIL);

        ChangeEmailDialog.confirmNewEmail("new" + NEW_EMAIL);

        ChangeEmailDialog.submitChangeEmail();

        Then("It should fail");
        AccountPage.checkForErrors();

        ChangeEmailDialog.close();

        Report("Setting Page Test 01 Passed!");
    }

    @Test
    public void SettingsPageTest02() {
        Report("Setting Page Test 01");

        When("I login");
        LoginPage.signIn(LOGIN, PASSWORD);

        UserBar.selectSettings();
        SettingsPage.selectSettingsTab(StringRef.SettingTab.LOCATIONS);


    }
}
