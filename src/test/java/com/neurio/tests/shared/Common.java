package com.neurio.tests.shared;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Robert on 2016-05-18.
 * This is a class for common methods used by all helper classes
 */
public class Common extends Browser{

    /**
     * Forces Selenium to wait a set amount of seconds
     * @param seconds - how long in seconds to wait
     */
    public static void wait(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getPropertyValue(String value, String defaultValue){
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream;

        try {
            inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        // Default value is set to here, if not found
        return prop.getProperty(value, defaultValue);
    }

    /**
     * Implicitly wait for an element to appear for 10 seconds
     * @param selector - CSS selector for the element
     */
    public static boolean checkForElement(String selector){
        return driver.findElements(By.cssSelector(selector)).size() != 0;
    }

    /**
     * Implicitly wait for an element to appear for 10 seconds
     * @param selector - CSS selector for the element
     */
    public static void waitForElement(final String selector){
        int maxTime = Integer.parseInt(getPropertyValue("timeout", "10"));
        (new WebDriverWait(driver, maxTime)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(By.cssSelector(selector)).size() != 0;

            }
        });
    }

    /**
     * Implicitly wait for an element to appear for 10 seconds
     * @param selector - Class name selector for the element
     */
    public static void waitForElementClass(final String selector){
        int maxTime = Integer.parseInt(getPropertyValue("timeout", "10"));
        (new WebDriverWait(driver, maxTime)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(By.className(selector)).size() != 0;
            }
        });
    }

    /**
     * Implicitly wait for an element to appear for 10 seconds
     * @param selector - ID selector for the element
     */
    public static void waitForElementID(final String selector){
        int maxTime = Integer.parseInt(getPropertyValue("timeout", "10"));
        (new WebDriverWait(driver, maxTime)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(By.id(selector)).size() != 0;
            }
        });
    }

    /**
     * Take a screenshot during the test
     * @param filename - The file name to save as
     */
    public static void takeScreenshot(String filename) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/web-screenshots/" + filename + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
