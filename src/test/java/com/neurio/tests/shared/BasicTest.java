package com.neurio.tests.shared;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;

/**
 * Created by Robert on 2016-05-18.
 *
 */
/**
 * Basic Framework for a tests which can be extended to any test
 * Every tests begins with loading the home page
 */

public class BasicTest extends Browser{

    @BeforeTest
    public void beforeTest() {
        //Go to the home page
        LoginPage.loadLoginPage();
    }

    @AfterTest
    public void afterTest() {
        //Sign out
        UserBar.signOut();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/web-failure-screenshots/" + testResult.getName() + ".png"));
        }
    }
}
