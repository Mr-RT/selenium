package com.neurio.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Properties;


/*
 * Created by Robert T. on 2016-05-16.
 * Login Test
 */
public class LoginTest {

    //private WebDriver driver = new FirefoxDriver();
    private WebDriver driver = null;

    @BeforeTest
    public void launchBrowser() {

        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        String browser = prop.getProperty("browser");
        if(browser.contains("chrome")){
            try {
                URL url = new URL("http://localhost:9515");
                driver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
            } catch (MalformedURLException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else if(browser.contains("htmlunit")){
            driver = new HtmlUnitDriver();
        } else {
            driver = new FirefoxDriver();
        }


        String HOME_PAGE = "https://staging.neur.io/";
        //Go to the home page
        driver.get(HOME_PAGE);
    }

    @AfterTest
    public void closeBrowser() {
        //Close the browser
        driver.quit();
    }

    @Test
    public void LoginPageTest() {
        String EMAIL = "email";
        String PASSWORD = "password";
        String ADMIN = "admin";
        String SIGN_IN_BUTTON_CSS_SELECTOR = ".signin-button";
        String ADMIN_LOGIN = "admin@energy-aware.com";
        String ADMIN_PASSWORD = "bonny5_worktable";
        String USERNAME_TEXT_BOX_CSS_SELECTOR = ".userbar-text > span";
        String ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR = ".dropdown-toggle-multiple";
        String ADMIN_DROPDOWN_MENU_CSS_SELECTOR = ".userbar-text .dropdown-menu";
        String ADMIN_SIGN_OUT_CSS_SELECTOR = ".fa-sign-out";
        final String HOME_PAGE_SIGN_IN_CLASS_NAME = ".signin";
        final String MAIN_DASHBOARD_CSS_SELECTOR = ".main.dashboard";
        final String ERROR_ALERT_CSS_SELECTOR = ".alert-error";

        // Find the text input element by its name
        WebElement emailElement = driver.findElement(By.name(EMAIL));

        WebElement passwordElement = driver.findElement(By.name(PASSWORD));

        WebElement signInElement = driver.findElement(By.cssSelector(SIGN_IN_BUTTON_CSS_SELECTOR));

        // Test Case - Invalid Email and Correct Password
        // Enter something
        emailElement.sendKeys(EMAIL + "@email.com");

        passwordElement.sendKeys(ADMIN_PASSWORD);

        // Now submit the form
        signInElement.click();

        // Wait for Error
        waitForElement(driver, ERROR_ALERT_CSS_SELECTOR);

        emailElement.clear();
        passwordElement.clear();

        // Test Case - Correct Email and Invalid Password
        // Enter something
        emailElement.sendKeys(ADMIN_LOGIN);

        passwordElement.sendKeys(PASSWORD);

        // Now submit the form
        signInElement.click();

        // Wait for Error
        waitForElement(driver, ERROR_ALERT_CSS_SELECTOR);

        emailElement.clear();
        passwordElement.clear();

        // Test Case - Valid Email and Password
        // Enter something
        emailElement.sendKeys(ADMIN_LOGIN);

        passwordElement.sendKeys(ADMIN_PASSWORD);

        // Now submit the form
        signInElement.click();

        // Wait for Element
        waitForElement(driver, MAIN_DASHBOARD_CSS_SELECTOR);

        WebElement adminTextElement = driver.findElement(By.cssSelector(USERNAME_TEXT_BOX_CSS_SELECTOR));

        String adminText = adminTextElement.getText();

        Assert.assertEquals(adminText, ADMIN, "admin != " + adminText);

        WebElement adminMenuToggleElement = driver.findElement(By.cssSelector(ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR));
        adminMenuToggleElement.click();

        WebElement adminMenuElement = driver.findElement(By.cssSelector(ADMIN_DROPDOWN_MENU_CSS_SELECTOR));
        adminMenuElement.findElement(By.cssSelector(ADMIN_SIGN_OUT_CSS_SELECTOR)).click();

        // Wait for Home Page
        waitForElement(driver, HOME_PAGE_SIGN_IN_CLASS_NAME);
    }

    private static void waitForElement(WebDriver driver, final String selector){
        // Wait for Element
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(By.cssSelector(selector)).size() != 0;
            }
        });
    }



}