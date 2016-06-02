package com.neurio.tests;

import com.neurio.tests.shared.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Robert on 5/24/2016.
 * Settings Test
 */
public class SettingsTest extends BasicTest {
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
        LoginPage.loadLoginPage();
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
        String ONE_MILLION_ONE = "1000001";
        String NEGATIVE_ONE = "-1";
        String ABC = "abc";
        String ONE = "1";
        String ONE_POINT_ONE = "1.1";
        String TWO = "2";

        testMethod sizeOfHome = (String input) -> LocationsPage.setSizeOfHome(input);
        testMethod alwaysOnTaget = (String input) -> LocationsPage.setAlwaysOnTarget(input);
        testMethod budget = (String input) -> LocationsPage.setBudget(input);
        testMethod fixedCharges = (String input) -> LocationsPage.setFixedCharges(input);
        testMethod setFlatCost = (String input) -> LocationsPage.setEnergyPrice(input);
        testMethod setTierEnergyRates = (String input) ->{
            LocationsPage.setTierEnergyRates(1, input);
            LocationsPage.setTierEnergyRates(2, input);
        };
        testMethod setTaxRate = (String input) -> LocationsPage.setTaxes(input);
        testMethod setPeakPrice = (String input) -> LocationsPage.setPeakRate(1, input);

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

        String[] badValues = {ONE_MILLION_ONE, NEGATIVE_ONE, ABC};
        locationPageInputLoop(badValues, sizeOfHome, true);

        LocationsPage.setSizeOfHome(TWO);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new size of home");

        badValues = new String[]{ONE_MILLION_ONE, NEGATIVE_ONE, ABC, ONE_POINT_ONE};
        locationPageInputLoop(badValues, alwaysOnTaget, true);

        LocationsPage.setAlwaysOnTarget(TWO);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new always on target");

        badValues = new String[]{ONE_MILLION_ONE, "-100000.99", ABC};
        locationPageInputLoop(badValues, budget, true);

        LocationsPage.setBudget(TWO);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new budget");

        LocationsPage.setBillingCycleStart(19);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new billing cycle");

        badValues = new String[]{ONE_MILLION_ONE, NEGATIVE_ONE, ABC};
        locationPageInputLoop(badValues, fixedCharges, true);

        LocationsPage.setFixedCharges(TWO);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new fixed charges");

        LocationsPage.setBillingPlanType(StringRef.BillingType.FLAT);
        locationPageInputLoop(badValues, setFlatCost, true);

        LocationsPage.setEnergyPrice(TWO);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new energy price");

        LocationsPage.setBillingPlanType(StringRef.BillingType.TIERED);
        LocationsPage.setNumberOfTiers(2);

        LocationsPage.setTierDetail(1, ONE_MILLION_ONE);

        badValues = new String[]{ONE_MILLION_ONE, NEGATIVE_ONE};
        locationPageInputLoop(badValues, setTierEnergyRates, false);

        LocationsPage.setTierDetail(1, TWO);
        LocationsPage.setTierEnergyRates(1, TWO);
        LocationsPage.setTierEnergyRates(2, TWO);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new tiered details");

        LocationsPage.setBillingPlanType(StringRef.BillingType.TIME_OF_USE);

        LocationsPage.setOffPeakPrice(TWO);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to save new off peak pricing");

        LocationsPage.toggleWeekendPeakPricing(true);
        LocationsPage.saveChanges();
        Assert.assertTrue(Common.checkForSuccess(), "Unable to exclude weekends from off peak price");

        badValues = new String[]{ONE_MILLION_ONE, NEGATIVE_ONE};
        locationPageInputLoop(badValues, setPeakPrice, false);

        LocationsPage.addPeakPeriod();
        Assert.assertTrue(LocationsPage.checksIfPeriodToPeriodIsEditable(2), "Peak 2 From Value can be changed");
        LocationsPage.addPeakPeriod();
        Assert.assertTrue(LocationsPage.checksIfAddPeriodIsThere(), "Can add more than 3 peaks periods");

        badValues = new String[]{ONE_MILLION_ONE, NEGATIVE_ONE, ABC};
        locationPageInputLoop(badValues, setTaxRate, true);


        //Reset
        LocationsPage.setTimezone(LA);
        LocationsPage.setPostalCode(POSTAL_CODE);
        LocationsPage.setTypeOfHome(HOME_TYPE);
        LocationsPage.setSizeOfHome(ONE);
        LocationsPage.setAlwaysOnTarget(ONE);
        LocationsPage.setBudget(ONE);
        LocationsPage.setBillingCycleStart(1);
        LocationsPage.setFixedCharges(ONE);
        LocationsPage.saveChanges();

        Report("Setting Page Test 02 Passed!");
    }

    /**
     * Using Lambda Functions to pass functions as parameter to loop through test cases
     */
    interface testMethod {
        void runMethod(String text);
    }

    private void locationPageInputLoop(String[] list, testMethod method, boolean warning){
        for(String input: list){
            Common.wait(1);
            method.runMethod(input);
            LocationsPage.saveChanges();
            //Error is an input warning
            if(warning){
                Assert.assertTrue(Common.checkForError(), "Bad input passed: " + input + " and no error given");
            //Error is an alert banner
            } else {
                Assert.assertTrue(Common.assertError(), "Bad input passed: " + input + " and no error given");
            }

        }
    }

}
