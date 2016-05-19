package com.neurio.tests.shared;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Robert on 2016-05-18.
 * Class to navigate through the Login Page
 */
public class LoginPage extends Browser{

    public static void loadLoginPage(){
        String HOME_PAGE = "https://staging.neur.io/";
        //Go to the home page
        driver.get(HOME_PAGE);
        waitForLoginPage();
    }

    public static void enterFields(String userName, String password){
        String EMAIL = StringRef.EMAIL;
        String PASSWORD = StringRef.PASSWORD;
        String SIGN_IN_BUTTON_CSS_SELECTOR = StringRef.SIGN_IN_BUTTON_CSS_SELECTOR;

        // Find the text input element by its name
        WebElement emailElement = driver.findElement(By.name(EMAIL));
        WebElement passwordElement = driver.findElement(By.name(PASSWORD));

        // Enter something
        emailElement.sendKeys(userName);
        passwordElement.sendKeys(password);

        WebElement signInElement = driver.findElement(By.cssSelector(SIGN_IN_BUTTON_CSS_SELECTOR));
        // Now submit the form
        signInElement.click();
    }

    public static void assertError(){
        final String ERROR_ALERT_CSS_SELECTOR = StringRef.ERROR_ALERT_CSS_SELECTOR;
        // Wait for Error
        Common.waitForElement(ERROR_ALERT_CSS_SELECTOR);
    }

    public static void signIn(String userName, String password){
        enterFields(userName, password);
        HomePage.waitForHomePage();
    }

    public static void clearFields(){
        String EMAIL = StringRef.EMAIL;
        String PASSWORD = StringRef.PASSWORD;
        WebElement emailElement = driver.findElement(By.name(EMAIL));
        WebElement passwordElement = driver.findElement(By.name(PASSWORD));

        emailElement.clear();
        passwordElement.clear();
    }

    public static void waitForLoginPage(){
        final String LOGIN_SIGN_IN_CLASS_NAME = StringRef.LOGIN_SIGN_IN_CLASS_NAME;
        Common.waitForElement(LOGIN_SIGN_IN_CLASS_NAME);
    }
}
