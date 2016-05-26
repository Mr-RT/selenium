package com.neurio.tests;

import com.neurio.tests.shared.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Robert T. on 5/25/2016.
 * CalculatorTest
 */
public class CalculatorTest extends BasicTest {

    @Test(enabled = false)
    public void CalculatorTest01() {
        String ADMIN_LOGIN = "admin@energy-aware.com";
        String ADMIN_PASSWORD = "bonny5_worktable";
        String SOLAR = "windsor";
        String SOLAR_ID = "yVqQsLETRk2C1D1EjlgEjA";

        String command = "cmd.exe /c python C:\\Users\\Robert\\Documents\\qa\\SolarSavingsCalculator.py " + SOLAR_ID;

        String s;
        String save = "";

        try {
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(command);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            while ((s = stdInput.readLine()) != null) {
                save += (s + "\n");
            }

            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                save += (s + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Report("Calculator Test");
        LoginPage.signIn(ADMIN_LOGIN, ADMIN_PASSWORD);

        When("I login as Admin");
        UserBar.toggleUserMenu();

        Then("I can change to a user");
        UserBar.adminSelectUserByNameID(SOLAR, SOLAR_ID);

        String solarSavingsWeb = HomePage.getSolarSavings();
        String[] calc = save.split(" ");
        String solarCalculatedValue = calc[calc.length-1];

        solarCalculatedValue = solarCalculatedValue.replace("$", "").substring(0, solarCalculatedValue.length() - 5);

        Report("Solar Savings from the Web: " + solarSavingsWeb);
        Report("Solar Savings from Calculator: " + solarCalculatedValue);

        Common.takeScreenshot("Calculator Test - SolarSavings");

        Assert.assertEquals(solarSavingsWeb, solarCalculatedValue, "Web: " + solarSavingsWeb + " != " +
                "Calculator: " + solarCalculatedValue);

        Report("CalculatorTest Test Passed!");
    }

}
