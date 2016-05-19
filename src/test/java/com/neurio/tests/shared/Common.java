package com.neurio.tests.shared;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

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

    /**
     * Implicitly wait for an element to appear for 10 seconds
     * @param selector - CSS selector for the element
     */
    public static void waitForElement(final String selector){
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
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
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
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
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
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
