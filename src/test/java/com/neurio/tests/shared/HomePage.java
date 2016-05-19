package com.neurio.tests.shared;

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
}
