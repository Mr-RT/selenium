package com.neurio.tests;

import com.neurio.tests.shared.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Robert on 5/24/2016.
 * Settings Test
 */
public class SettingsTests extends BasicTest {
    String LOGIN = "robert+89891@neur.io";
    String PASSWORD = "kashani1234";
    String ACCOUNT_NAME = "Robert89891";
    String NEW_ACCOUNT_NAME = ACCOUNT_NAME + " NEW";
    String BAD_ACCOUNT_NAME = "abcdefghijklmnopqrstuvuxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    @Test
    public void SettingsPageTest01() {

        String NEW_EMAIL = "robert+89892@neur.io";
        String BAD_PASSWORD = "123456";
        String NEW_PASSWORD = PASSWORD + "new";

        Report("Setting Page Test 01");

        When("I login");
        LoginPage.signIn(LOGIN, PASSWORD);

        UserBar.selectSettings();

        When("I change name to one longer than 50 characters");
        AccountPage.changeAccountName(BAD_ACCOUNT_NAME);

        Then("It should fail");
        Assert.assertTrue(Common.checkForError(), "Errors did not appear");

        When("I change the name, it should work and be checked later on in the test");
        AccountPage.changeAccountName(NEW_ACCOUNT_NAME);

        AccountPage.changePassword(PASSWORD, BAD_PASSWORD);

        Then("It should fail");
        Assert.assertTrue(Common.checkForError(), "Errors did not appear");

        When("I change the password, it should work and be checked later on in the test");
        AccountPage.changePassword(PASSWORD, NEW_PASSWORD);

        SettingsPage.signOut();

        When("I sign in with the new password and check the new account name");
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
        Assert.assertTrue(Common.checkForError(), "Errors did not appear");

        When("I set new email with wrong password");
        ChangeEmailDialog.changeEmail(BAD_PASSWORD, LOGIN);

        Then("It should fail");
        Assert.assertTrue(Common.checkForError(), "Errors did not appear");

        When("I use new emails that do not match");
        ChangeEmailDialog.enterCurrentPassword(PASSWORD);

        ChangeEmailDialog.enterNewEmail(NEW_EMAIL);

        ChangeEmailDialog.confirmNewEmail("new" + NEW_EMAIL);

        ChangeEmailDialog.submitChangeEmail();

        Then("It should fail");
        Assert.assertTrue(Common.checkForError(), "Errors did not appear");

        ChangeEmailDialog.close();

        SettingsPage.signOut();

        Report("Setting Page Test 01 Passed!");
    }

    @Test
    public void SettingsPageTest02() {
        String LA = "Los_Angeles";
        String NEW_YORK = "New_York";
        String POSTAL_CODE = "V6B 1G4";
        String NEW_POSTAL_CODE = "V5H 4N2";
        String HOME_TYPE = "House";
        String NEW_HOME_TYPE = "Condo";

        Report("Setting Page Test 02");

        When("I login");
        LoginPage.signIn(LOGIN, PASSWORD);

        UserBar.selectSettings();
        SettingsPage.selectSettingsTab(StringRef.SettingTab.LOCATIONS);

        LocationsPage.selectLocation(1);

        LocationsPage.setLocationName(BAD_ACCOUNT_NAME);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForError(), "Bad Location Name Passed");

        LocationsPage.setLocationName(NEW_ACCOUNT_NAME);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new Account Name");

        LocationsPage.setTimezone(NEW_YORK);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new Time Zone");

        LocationsPage.setPostalCode(NEW_POSTAL_CODE);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new Postal Code");

        LocationsPage.setTypeOfHome(NEW_HOME_TYPE);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new Home Type");

        //Reset
        LocationsPage.setTimezone(LA);
        LocationsPage.setPostalCode(POSTAL_CODE);
        LocationsPage.setTypeOfHome(HOME_TYPE);

        LocationsPage.saveChanges();

        UserBar.selectTab(StringRef.Tab.HOME);

        Report("Setting Page Test 02 Passed!");
    }
}
