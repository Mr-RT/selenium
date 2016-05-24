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

    //Setting Page Variables
    public static final String SETTINGS_LIST_ITEMS_CSS_SELECTOR = ".settings-sidebar > .list > a";
    public static final String ACCOUNT_DETAILS_NAME_INPUT_CSS_SELECTOR = ".first-card input";
    public static final String ACCOUNT_DETAILS_SAVE_CHANGES_BUTTON_CSS_SELECTOR = ".first-card .loading-button";
    public static final String ACCOUNT_DETAILS_HELP_BLOCK_CSS_SELECTOR = ".first-card .help-block";
    public static final String CURRENT_PASSWORD_NAME = "currentPassword";
    public static final String CONFIRM_PASSWORD_NAME = "confirmPassword";
    public static final String NEW_EMAIL_NAME = "newEmail";
    public static final String CONFIRM_EMAIL_NAME = "confirmEmail";
    public static final String PASSWORD_INPUT_CLASS_NAME = "password-input";
    public static final String CHANGE_PASSWORD_BUTTON_CSS_SELECTOR = ".loading-button:nth-child(4)";
    public static final String CHANGE_EMAIL_BUTTON_CSS_SELECTOR = ".btn-primary:nth-child(1)";
    public static final String HAS_ERROR_CLASS_NAME = "has-error";
    public static final String CHANGE_EMAIL_DIALOG_CLASS_NAME = "modal-body";
    public static final String CONFIRM_BUTTON_CLASS_NAME = "confirm-button";
    public static final String CLOSE_BUTTON_CLASS_NAME = "close-button";
    public static final String SETTINGS_SIGN_OUT_CSS_SELECTOR = ".userbar-text span:nth-child(3)";

    //General Variables
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String VALUE = "value";
    public static final String UNITS = "unit";
    public static final String COST = "cost";
    public static final String STAGING_HOME_PAGE = "https://staging.neur.io/";
    public static final String PRODUCTION_HOME_PAGE = "https://production.neur.io/";
    public static final String HELP_BLOCK_CLASS_NAME = "help-block";

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

    public enum SettingTab {
        ACCOUNT, LOCATIONS, APPLIANCES_PROFILE, APPS, EXPORT_DATA
    }

    public static final Map<SettingTab, String> settingTabMapEnumToString;
    static {
        settingTabMapEnumToString = new HashMap<SettingTab, String>();
        settingTabMapEnumToString.put(SettingTab.ACCOUNT, "Account");
        settingTabMapEnumToString.put(SettingTab.LOCATIONS, "Locations");
        settingTabMapEnumToString.put(SettingTab.APPLIANCES_PROFILE, "Appliances Profile");
        settingTabMapEnumToString.put(SettingTab.APPS, "Apps");
        settingTabMapEnumToString.put(SettingTab.EXPORT_DATA, "Export Data");
    }

}
