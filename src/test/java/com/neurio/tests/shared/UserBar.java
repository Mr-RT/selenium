package com.neurio.tests.shared;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by Robert on 2016-05-18.
 * Class to navigate through the User Bar
 */
public class UserBar extends Browser{

    /**
     * Selects tab in the user bar
     * @param tab selected tab
     */
    public static void selectTab(StringRef.Tab tab){
        String selectedTab = StringRef.tabMapEnumToString.get(tab);
        List<WebElement> elementsList = getElementsByCSS(StringRef.TABS_CSS_SELECTOR);
        for(WebElement element: elementsList){
            if(element.getText().equals(selectedTab)){
                element.click();
                break;
            }
        }
    }

    /**
     * Selects the settings button in user bar
     */
    public static void selectSettings(){
        getElementByCSS(StringRef.SETTINGS_CSS_SELECTOR).click();
    }

    /**
     * Check if the username in the top right of the user bar is correct
     * @param userName User Name given
     */
    public static void assertUserName(String userName){
        WebElement adminTextElement = driver.findElement(By.cssSelector(StringRef.USERNAME_TEXT_BOX_CSS_SELECTOR));

        String adminText = adminTextElement.getText();

        Assert.assertEquals(adminText, userName, userName + " != " + adminText);
    }

    /**
     * Toggle the User Menu in the User Bar to appear
     */
    public static void toggleUserMenu(){
        WebElement adminMenuToggleElement = driver.findElement(
                By.cssSelector(StringRef.ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR));
        adminMenuToggleElement.click();
    }

    /**
     * Open an account as admin user
     * @param name - Type in name in input to search for string
     * @param  id - Click on the correct ID found in the search
     */
    public static void adminSelectUserByNameID(String name, String id){
        WebElement userInputElement = driver.findElement(By.cssSelector(StringRef.ID_INPUT_CSS_SELECTOR));

        userInputElement.sendKeys(name);

        driver.findElement(By.id(id)).click();

        HomePage.waitForHomePage();
    }

    /**
     * Sign out of the web app
     */
    public static void signOut(){
        WebElement adminMenuToggleElement = driver.findElement(
                By.cssSelector(StringRef.ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR));
        adminMenuToggleElement.click();

        WebElement adminMenuElement = driver.findElement(By.cssSelector(StringRef.ADMIN_DROPDOWN_MENU_CSS_SELECTOR));
        adminMenuElement.findElement(By.cssSelector(StringRef.ADMIN_SIGN_OUT_CSS_SELECTOR)).click();
    }
}
