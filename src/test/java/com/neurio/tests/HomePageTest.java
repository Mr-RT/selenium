package com.neurio.tests;

import com.neurio.tests.shared.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Robert T. on 5/20/2016.
 * Home Page Test
 */
public class HomePageTest extends BasicTest {

    @Test(enabled = false)
    public void HomePageTest01() {
        String LOGIN = "robert+89891@neur.io";
        String PASSWORD = "kashani1234";

        Report("Home Page Test");

        When("I login");
        LoginPage.signIn(LOGIN, PASSWORD);

        Then("My current values should be 0");
        Assert.assertEquals(HomePage.getCurrentConsumptionValue(), 0, "Current Consumption Value is not 0; it is " +
            HomePage.getCurrentConsumptionValue());

        Assert.assertEquals(HomePage.getCurrentGenerationValue(), 0, "Current Generation Value is not 0; it is " +
                HomePage.getCurrentGenerationValue());

        Report("Home Page Test Passed");
    }
}
