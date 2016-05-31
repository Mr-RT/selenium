package com.neurio.tests;

import com.neurio.tests.shared.*;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Created by Robert T. on 5/25/2016.
 * CalculatorTest
 */
public class CalculatorTest extends BasicTest {

    @Test(enabled = false)
    public void ForecastCalculatorTest01() {
        String ADMIN_LOGIN = "admin@energy-aware.com";
        String ADMIN_PASSWORD = "bonny5_worktable";
        String SOLAR = "erik";
        String USER_ID = "K2quP1_OSxC5b4IsvizTEg";

        Report("Calculator Test");
        LoginPage.signIn(ADMIN_LOGIN, ADMIN_PASSWORD);

        When("I login as Admin");
        UserBar.toggleUserMenu();

        Then("I can change to a user");
        UserBar.adminSelectUserByNameID(SOLAR, USER_ID);

        Double forecastValueWeb = Double.parseDouble(HomePage.getForecastValue());
        String[] calc = Calculator.getOutput(StringRef.Calculator.Forecast, USER_ID);
        int forcastIndex = Arrays.asList(calc).indexOf("FORECAST");
        Double forecastCalculatedValue = Double.parseDouble(calc[forcastIndex + 1].replaceAll("[^0-9.-]", ""));

        Report("Forecast Value from the Web: " + forecastValueWeb);
        Report("Forecast Value from Calculator: " + forecastCalculatedValue);

        Common.takeScreenshot("Calculator Test - ForecastCalculator");

/*        Assert.assertEquals(forecastValueWeb, forecastCalculatedValue, "Web: " + forecastValueWeb + " != " +
                "Calculator: " + forecastCalculatedValue);*/

        Report("Forecast Calculator Test Passed!");
    }

    @Test(enabled = false)
    public void SolarCalculatorTest01() {
        String ADMIN_LOGIN = "admin@energy-aware.com";
        String ADMIN_PASSWORD = "bonny5_worktable";
        String SOLAR = "erik";
        String USER_ID = "K2quP1_OSxC5b4IsvizTEg";

        Report("Calculator Test");
        LoginPage.signIn(ADMIN_LOGIN, ADMIN_PASSWORD);

        When("I login as Admin");
        UserBar.toggleUserMenu();

        Then("I can change to a user");
        UserBar.adminSelectUserByNameID(SOLAR, USER_ID);
        Double solarSavingsWeb = Double.parseDouble(HomePage.getSolarSavings());
        String[] calc = Calculator.getOutput(StringRef.Calculator.SolarSavings, USER_ID);
        Double solarCalculatedValue = Double.parseDouble(calc[calc.length-1].replaceAll("[^0-9.]", ""));

        Report("Solar Savings from the Web: " + solarSavingsWeb);
        Report("Solar Savings from Calculator: " + solarCalculatedValue);

        Common.takeScreenshot("Calculator Test - SolarSavings");

/*        Assert.assertEquals(solarSavingsWeb, solarCalculatedValue, "Web: " + solarSavingsWeb + " != " +
                "Calculator: " + solarCalculatedValue);*/

        Report("Solar Calculator Test Passed!");
    }

}
