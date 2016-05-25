package com.neurio.tests.shared;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Robert on 5/24/2016.
 * Class to navigate the Locations Page
 */
public class LocationsPage extends Browser {

    /**
     * Helper function for selecting an item from a drop down form
     * @param formName The name attribute of the select web element
     * @param item Item to be found and selected
     */
    private static void selectorFindHelper(String formName, String item){
        WebElement selector = getElementByName(formName);
        List<WebElement> elementsList = selector.findElements(By.cssSelector(StringRef.OPTION));
        elementListHelper(elementsList, item);
    }


    /**
     * Helper function for selecting an item from a drop down form
     * @param elementsList The select web element
     * @param item Item to be found and selected
     */
    private static void elementListHelper(List<WebElement> elementsList, String item){
        boolean notFound = true;
        for(WebElement element: elementsList){
            if(element.getText().contains(item)){
                element.click();
                notFound = false;
                break;
            }
        }
        if(notFound){
            throw new NoSuchElementException(item + " not found");
        }
    }

    /**
     * Selects first location found with the given location in the locations list
     * @param location given location
     */
    public static void selectLocation(String location){
        List<WebElement> elementsList = getElementsByCSS(StringRef.SETTINGS_LIST_ITEMS_CSS_SELECTOR);
        elementListHelper(elementsList, location);
    }

    /**
     * Inputs a new location name
     * @param newName New location name
     */
    public static void changeLocationName(String newName){
        getElementByName(StringRef.NAME).clear();
        getElementByName(StringRef.NAME).sendKeys(newName);
    }

    /**
     * Inputs a new time zone
     * @param timezone New time zone
     */
    public static void changeTimezone(String timezone){
        selectorFindHelper(StringRef.TIMEZONE, timezone);
    }

    public static void changePostalCode(String postalCode) {
        getElementByName(StringRef.POSTAL_CODE).clear();
        getElementByName(StringRef.POSTAL_CODE).sendKeys(postalCode);
    }

    public static void changeTypeOfHome(String homeType){
        selectorFindHelper(StringRef.HOME_TYPE, homeType);
    }

    public static void changeSizeOfHome(String homeSize){
        getElementByName(StringRef.HOME_SIZE).clear();
        getElementByName(StringRef.HOME_SIZE).sendKeys(homeSize);
    }

    public static void changeNumberOfPeople(String number){
        getElementByName(StringRef.RESIDENTS).clear();
        getElementByName(StringRef.RESIDENTS).sendKeys(number);
    }

    public static void setAlwaysOnTarget(String target){
        getElementByName(StringRef.ALWAYS_ON_TARGET).clear();
        getElementByName(StringRef.ALWAYS_ON_TARGET).sendKeys(target);
    }

    public static void setBudget(String budget){
        getElementByName(StringRef.BUDGET).clear();
        getElementByName(StringRef.BUDGET).sendKeys(budget);
    }

    public static void setBillingCycleStart(int day){
        selectorFindHelper(StringRef.BILLING_CYCLE_DAY, String.valueOf(day));
    }

    public static void setFixedCharges(String cost){
        getElementByName(StringRef.FIXED_CHARGE).clear();
        getElementByName(StringRef.FIXED_CHARGE).sendKeys(cost);
    }

    public static void setBillingPlanType(String type){
        selectorFindHelper(StringRef.BILLING_TYPE, type);
    }

    public static void setEnergyPrice(String rate){
        getElementByName(StringRef.ENERGY_RATE).clear();
        getElementByName(StringRef.ENERGY_RATE).sendKeys(rate);
    }

    public static void setNumberOfTiers(int tier){
        String tierString = String.valueOf(tier);
        selectorFindHelper(StringRef.NUM_TIERS, tierString);
    }

    public static void toggleWeekendPeakPricing(boolean toggleOn){
        if(getElementByName(StringRef.WEEKENDS).isSelected() != toggleOn){
            getElementByName(StringRef.WEEKENDS).click();
        }
    }

    public static void setTier1Detail(String detail){
        getElementByName(StringRef.TIER_1_DETAILS).clear();
        getElementByName(StringRef.TIER_1_DETAILS).sendKeys(detail);
    }

    public static void setTaxes(Double tax) {
        getElementByName(StringRef.TAX_RATE).clear();
        getElementByName(StringRef.TAX_RATE).sendKeys(String.valueOf(tax));
    }

    /**
     * Save location changes
     */
    public static void saveChanges(){
        getElementByCSS(StringRef.SAVE_LOCATION_CHANGES_BUTTON_CSS_SELECTOR).click();
    }
}
