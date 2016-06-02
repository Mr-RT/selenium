package com.neurio.tests.shared;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

/**
 * Created by Robert on 2016-05-18.
 * Basic Framework for a tests which can be extended to any test
 * Every tests begins with loading the home page
 * And takes a screenshot on every test failure
 */

public class BasicTest extends Browser{

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/web-failure-screenshots/" + testResult.getName() + ".png"));
        }
    }

    public void When(String event){
        Reporter.log(event);
    }

    public void Then(String event){
        Reporter.log(event);
    }

    public void Report(String event){
        Reporter.log(event);
    }
}
