package com.neurio.tests.shared;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by Robert on 2016-05-18.
 * Class to navigate through the User Bar
 */
public class UserBar extends Browser{

    public static void assertUserName(String userName){
        String USERNAME_TEXT_BOX_CSS_SELECTOR = StringRef.USERNAME_TEXT_BOX_CSS_SELECTOR;
        WebElement adminTextElement = driver.findElement(By.cssSelector(USERNAME_TEXT_BOX_CSS_SELECTOR));

        String adminText = adminTextElement.getText();

        Assert.assertEquals(adminText, userName, "admin != " + adminText);
    }

    public static void toggleUserMenu(){
        String ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR = StringRef.ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR;
        WebElement adminMenuToggleElement = driver.findElement(By.cssSelector(ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR));
        adminMenuToggleElement.click();
    }

    public static void adminSelectUserByNameID(String name, String id){
        String ID_INPUT_CSS_SELECTOR = StringRef.ID_INPUT_CSS_SELECTOR;
        WebElement userInputElement = driver.findElement(By.cssSelector(ID_INPUT_CSS_SELECTOR));

        userInputElement.sendKeys(name);

        driver.findElement(By.id(id)).click();

        HomePage.waitForHomePage();
    }

    public static void signOut(){
        String ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR = StringRef.ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR;
        String ADMIN_DROPDOWN_MENU_CSS_SELECTOR = StringRef.ADMIN_DROPDOWN_MENU_CSS_SELECTOR;
        String ADMIN_SIGN_OUT_CSS_SELECTOR = StringRef.ADMIN_SIGN_OUT_CSS_SELECTOR;

        WebElement adminMenuToggleElement = driver.findElement(By.cssSelector(ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR));
        adminMenuToggleElement.click();

        WebElement adminMenuElement = driver.findElement(By.cssSelector(ADMIN_DROPDOWN_MENU_CSS_SELECTOR));
        adminMenuElement.findElement(By.cssSelector(ADMIN_SIGN_OUT_CSS_SELECTOR)).click();
    }
}
