package com.neurio.tests.shared;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Robert on 2016-05-18.
 * This is a central repository for String values
 */

public class StringRef {
    //Login Page Variables
    public static final String SIGN_IN_BUTTON_CSS_SELECTOR = ".signin-button";
    public static final String ERROR_ALERT_CSS_SELECTOR = ".alert-error";
    public static final String LOGIN_SIGN_IN_CLASS_NAME = ".signin";

    //User Bar Variables
    public static final String USERNAME_TEXT_BOX_CSS_SELECTOR = ".userbar-text > span";
    public static final String ADMIN_DROPDOWN_TOGGLE_CSS_SELECTOR = ".dropdown-toggle-multiple";
    public static final String ID_INPUT_CSS_SELECTOR = ".input-wrapper > input";
    public static final String ADMIN_DROPDOWN_MENU_CSS_SELECTOR = ".userbar-text .dropdown-menu";
    public static final String ADMIN_SIGN_OUT_CSS_SELECTOR = ".fa-sign-out";
    public static final String SETTINGS_CSS_SELECTOR = ".userbar-text > a";

    //Home Page Variables
    public static final String MAIN_DASHBOARD_CSS_SELECTOR = ".main.dashboard";
    public static final String HOME_PAGE_CONSUMPTION_CLASS_NAME = "consumption";
    public static final String HOME_PAGE_GENERATION_CLASS_NAME = "generation";
    public static final String TABS_CSS_SELECTOR = ".header > nav > a > span";

    //General Variables
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String VALUE = "value";
    public static final String UNITS = "unit";
    public static final String COST = "cost";

    public enum Tab {
        HOME, LOCATION, HISTORY
    }

    public static final Map<Tab, String> tabMapEnumToString;
    static {
        tabMapEnumToString = new HashMap<Tab, String>();
        tabMapEnumToString.put(Tab.HOME, "My Home");
        tabMapEnumToString.put(Tab.LOCATION, "Location");
        tabMapEnumToString.put(Tab.HISTORY, "History");
    }

}
