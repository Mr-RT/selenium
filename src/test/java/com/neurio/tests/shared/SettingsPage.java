package com.neurio.tests.shared;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Robert T. on 5/24/2016.
 *
 */
public class SettingsPage extends Browser {
    public static void selectSettingsTab(StringRef.SettingTab tab){
        String selectedTab = StringRef.settingTabMapEnumToString.get(tab);
        List<WebElement> elementsList = getElementsByCSS(StringRef.SETTINGS_LIST_ITEMS_CSS_SELECTOR);
        for(WebElement element: elementsList){
            if(element.getText().equals(selectedTab)){
                element.click();
                break;
            }
        }

    }
}
