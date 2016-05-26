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
        List<WebElement> elementsList = getElementsByCSS(StringRef.LOCATION_LIST_ITEMS_CLASS_NAME);
        elementListHelper(elementsList, location);
    }

    /**
     * Selects first location found with the given index in the locations list (index 1 based)
     * @param number given index
     */
    public static void selectLocation(int number){
        List<WebElement> elementsList = getElementsByCSS(StringRef.LOCATION_LIST_ITEMS_CSS_SELECTOR);
        WebElement locationElement = elementsList.get(number + 1);
        locationElement.click();
    }

    /**
     * Inputs a new location name
     * @param newName New location name
     */
    public static void setLocationName(String newName){
        getElementByName(StringRef.NAME).clear();
        getElementByName(StringRef.NAME).sendKeys(newName);
    }

    /**
     * Inputs a new time zone
     * @param timezone New time zone
     */
    public static void setTimezone(String timezone){
        selectorFindHelper(StringRef.TIMEZONE, timezone);
    }

    /**
     * Inputs a new postal code
     * @param postalCode New postal code
     */
    public static void setPostalCode(String postalCode) {
        Common.enterValueInElementByName(StringRef.POSTAL_CODE, postalCode);
    }

    /**
     * Inputs a new home type
     * @param homeType New home type
     */
    public static void setTypeOfHome(String homeType){
        selectorFindHelper(StringRef.HOME_TYPE, homeType);
    }

    /**
     * Inputs a new home size
     * @param homeSize New home size
     */
    public static void setSizeOfHome(String homeSize){
        Common.enterValueInElementByName(StringRef.HOME_SIZE, homeSize);
    }

    /**
     * Inputs new number of people
     * @param number New number of people
     */
    public static void setNumberOfPeople(String number){
        Common.enterValueInElementByName(StringRef.RESIDENTS, number);
    }

    /**
     * Inputs a new always on target
     * @param target New always on target
     */
    public static void setAlwaysOnTarget(String target){
        Common.enterValueInElementByName(StringRef.ALWAYS_ON_TARGET, target);
    }

    /**
     * Inputs a new budget
     * @param budget New Budget
     */
    public static void setBudget(String budget){
        Common.enterValueInElementByName(StringRef.BUDGET, budget);
    }

    /**
     * Inputs a new biling cycle start day
     * @param day New start day
     */
    public static void setBillingCycleStart(int day){
        selectorFindHelper(StringRef.BILLING_CYCLE_DAY, String.valueOf(day));
    }

    /**
     * Inputs a new fixed charges
     * @param cost New fixed charges cost
     */
    public static void setFixedCharges(String cost){
        Common.enterValueInElementByName(StringRef.FIXED_CHARGE, cost);
    }

    /**
     * Inputs a new billing type
     * @param type New billing plan type
     */
    public static void setBillingPlanType(String type){
        selectorFindHelper(StringRef.BILLING_TYPE, type);
    }

    /**
     * Inputs a new energy price rate
     * @param rate New energy price rate
     */
    public static void setEnergyPrice(String rate){
        Common.enterValueInElementByName(StringRef.ENERGY_RATE, rate);
    }

    /**
     * Inputs a new number of tiers
     * @param tier New number of tiers
     */
    public static void setNumberOfTiers(int tier){
        selectorFindHelper(StringRef.NUM_TIERS, String.valueOf(tier));
    }

    /**
     * Toggles the weekend option on or off
     * @param toggleOn Choose to toggle on or off
     */
    public static void toggleWeekendPeakPricing(boolean toggleOn){
        if(getElementByName(StringRef.WEEKENDS).isSelected() != toggleOn){
            getElementByName(StringRef.WEEKENDS).click();
        }
    }

    /**
     * Inputs a new Tier 1 detail
     * @param detail New Tier 1 Detail
     */
    public static void setTier1Detail(String detail){
        Common.enterValueInElementByName(StringRef.TIER_1_DETAILS, detail);
    }

    /**
     * Inputs a new Tier 1 rate
     * @param rate New Tier 1 Rate
     */
    public static void setTier1EnergyRates(String rate){
        Common.enterValueInElementByName(StringRef.TIER_1_ENERGY_RATES, rate);
    }

    /**
     * Inputs a new Tier 1 rate
     * @param rate New Tier 1 Rate
     */
    public static void setTier2EnergyRates(String rate){
        Common.enterValueInElementByName(StringRef.TIER_2_ENERGY_RATES, rate);
    }

    /**
     * Inputs a new tax rate
     * @param tax New tax rate
     */
    public static void setTaxes(Double tax) {
        Common.enterValueInElementByName(StringRef.TAX_RATE, String.valueOf(tax));
    }

    /**
     * Save location changes
     */
    public static void saveChanges(){
        getElementByCSS(StringRef.SAVE_LOCATION_CHANGES_BUTTON_CSS_SELECTOR).click();
    }
}
