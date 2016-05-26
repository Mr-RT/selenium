package com.neurio.tests.shared;

/**
 * Created by Robert on 2016-05-18.
 * This class setups the browser web driver before testing can begin
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Browser {

    public static WebDriver driver;

    /**
     * Reads the arguments in the config.properties file
     * Setups the browser based on the browser entry given in
     * the config file
     */
    @BeforeSuite
    public void initializeBrowser() {
        // Default value is set to firefox here, if not found
        String browser = Common.getPropertyValue("browser", "firefox");

        if(browser.contains("chrome")){
            try {
                URL url = new URL("http://localhost:9515");
                driver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
            } catch (MalformedURLException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else if(browser.contains("htmlunit")) {
            driver = new HtmlUnitDriver();
        } else if(browser.contains("IE")){
            driver = new InternetExplorerDriver();
        } else {
            driver = new FirefoxDriver();
        }

        // Default value is set to 10 here, if not found
        int timeOut = Integer.parseInt(Common.getPropertyValue("timeout", "10"));

        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    /**
     * Closes browser after suite is complete
     */
    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }

    /**
     * The following are helper functions to get web elements from the web page using ID, Class Names, etc.
     */
    public static WebElement getElementByClassName(String name){
        return driver.findElement(By.className(name));
    }

    public static WebElement getElementByID(String name){
        return driver.findElement(By.id(name));
    }

    public static WebElement getElementByCSS(String name){
        return driver.findElement(By.cssSelector(name));
    }

    public static WebElement getElementByName(String name){
        return driver.findElement(By.name(name));
    }

    public static List<WebElement> getElementsByCSS(String name){
        return driver.findElements(By.cssSelector(name));
    }

    public static List<WebElement> getElementsByClassName(String name){
        return driver.findElements(By.className(name));
    }

    public static WebElement getElementFromParentByClass(WebElement element, String name){
        return element.findElement(By.className(name));
    }

    public static WebElement getElementFromParentByCSS(WebElement element, String name){
        return element.findElement(By.cssSelector(name));
    }

    public static WebElement getElementFromParentByName(WebElement element, String name){
        return element.findElement(By.name(name));
    }
}
