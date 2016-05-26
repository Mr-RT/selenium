package com.neurio.tests.shared;

import org.openqa.selenium.WebElement;

/**
 * Created by robert on 2016-05-18.
 * Class to navigate the Home Page
 */
public class HomePage extends Browser{

    /**
     * Implicitly wait for the Home Page to appear
     */
    public static void waitForHomePage(){
        // Wait for Element
        Common.waitForElement(StringRef.MAIN_DASHBOARD_CSS_SELECTOR);
    }

    /**
     * Gets the Current Consumption Value on the Home Page
     */
    public static int getCurrentConsumptionValue(){
        WebElement consumptionElement = getElementByClassName(StringRef.HOME_PAGE_CONSUMPTION_CLASS_NAME);
        return Integer.parseInt(getElementFromParentByClass(consumptionElement, StringRef.VALUE).getText());
    }

    /**
     * Gets the Current Consumption Cost on the Home Page
     */
    public static String getCurrentConsumptionCost(){
        WebElement consumptionElement = getElementByClassName(StringRef.HOME_PAGE_CONSUMPTION_CLASS_NAME);
        return getElementFromParentByClass(consumptionElement, StringRef.COST).getText();
    }

    /**
     * Gets the Current Generation Value on the Home Page
     */
    public static int getCurrentGenerationValue(){
        WebElement consumptionElement = getElementByClassName(StringRef.HOME_PAGE_GENERATION_CLASS_NAME);
        return Integer.parseInt(getElementFromParentByClass(consumptionElement, StringRef.VALUE).getText());
    }

    /**
     * Gets the Current Generation Cost on the Home Page
     */
    public static String getCurrentGenerationCost(){
        WebElement consumptionElement = getElementByClassName(StringRef.HOME_PAGE_GENERATION_CLASS_NAME);
        return getElementFromParentByClass(consumptionElement, StringRef.COST).getText();
    }

    public static String getSolarSavings(){
        return getElementByCSS(StringRef.SOLAR_SAVINGS_TEXT_CSS_SELECTOR).getText();
    }
}
