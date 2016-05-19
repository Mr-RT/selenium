package com.neurio.tests.shared;

/**
 * Created by robert on 2016-05-18.
 * Class to navigate the Home Page
 */
public class HomePage extends Browser{

    public static void waitForHomePage(){
        final String MAIN_DASHBOARD_CSS_SELECTOR = StringRef.MAIN_DASHBOARD_CSS_SELECTOR;
        // Wait for Element
        Common.waitForElement(MAIN_DASHBOARD_CSS_SELECTOR);
    }
}
