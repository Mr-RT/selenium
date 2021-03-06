package com.neurio.tests.shared;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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
     * @param tab Selected tab
     */
    public static void selectTab(StringRef.Tab tab){
        String selectedTab = StringRef.tabMapEnumToString.get(tab);
        List<WebElement> elementsList = getElementsByCSS(StringRef.TABS_CSS_SELECTOR);
        boolean notFound = true;
        for(WebElement element: elementsList){
            if(element.getText().equals(selectedTab)){
                element.click();
                notFound = false;
                break;
            }
        }
        if(notFound){
            throw new NoSuchElementException("Tab not found");
        }
    }

    /**
     * Selects the settings button in user bar
     */
    public static void selectSettings(boolean url){
        if(url){
            String mode = Common.getPropertyValue("mode", "staging");
            String settingsURL;
            if (mode.contains("staging")){
                settingsURL = StringRef.STAGING_HOME_PAGE;
            } else {
                settingsURL = StringRef.PRODUCTION_HOME_PAGE;
            }
            settingsURL += "#settings";
            goTo(settingsURL);
        } else {
            getElementByCSS(StringRef.SETTINGS_CSS_SELECTOR).click();
        }

    }

    /**
     * Check if the username in the top right of the user bar is correct
     * @param userName User Name given
     */
    public static void assertUserName(String userName){
        String adminText = getElementByCSS(StringRef.USERNAME_TEXT_BOX_CSS_SELECTOR).getText();

        Assert.assertEquals(adminText, userName, userName + " != " + adminText);
    }

    /**
     * Toggle the User Menu in the User Bar to appear
     */
    public static void toggleUserMenu(){
        getElementByCSS(StringRef.ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR).click();
    }

    /**
     * Open an account as admin user
     * @param name - Type in name in input to search for string
     * @param  id - Click on the correct ID found in the search
     */
    public static void adminSelectUserByNameID(String name, String id){
        getElementByCSS(StringRef.ID_INPUT_CSS_SELECTOR).sendKeys(name);

        getElementByID(id).click();

        HomePage.waitForHomePage();
    }

    /**
     * Open an account as admin user
     * @param email - Type in email in input to open account
     */
    public static void adminSelectUserByEmail(String email){
        getElementByClassName(StringRef.ADMIN_INPUT_CLASS_NAME).sendKeys(email);

        getElementByClassName(StringRef.ADMIN_INPUT_CLASS_NAME).sendKeys(Keys.RETURN);

        HomePage.waitForHomePage();
    }

    /**
     * Sign out of the web app
     */
    public static void signOut(){
        Common.waitForElementDisplayed(StringRef.ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR);
        getElementByCSS(StringRef.ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR).click();

        WebElement adminMenuElement = driver.findElement(By.cssSelector(StringRef.ADMIN_DROPDOWN_MENU_CSS_SELECTOR));
        adminMenuElement.findElement(By.cssSelector(StringRef.ADMIN_SIGN_OUT_CSS_SELECTOR)).click();
    }
}
