package com.neurio.tests;

import com.neurio.tests.shared.*;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by Robert on 2016-05-17.
 * Admin Test
 */
public class AdminTest extends BasicTest{

    @Test
    public void AdminTest01() {
        HashMap<String, String> map = new HashMap<>();
        map.put(StringRef.GRANT_TYPE, StringRef.CLIENT_CREDENTIALS);
        map.put(StringRef.CLIENT_ID, StringRef.ADMIN_CLIENT_ID);
        map.put(StringRef.CLIENT_SECRET, StringRef.ADMIN_CLIENT_SECRET);
        try {
            String response = Common.postRequest(false, "" ,
                    StringRef.API_STAGING_URL_PREFIX + "token", map);
            JSONObject obj = new JSONObject(response);
            String token = obj.getString(StringRef.ACCESS_TOKEN);

            response = Common.getRequest(token,  StringRef.API_STAGING_URL_PREFIX + "status");
            System.out.println(response);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test(enabled = false)
    public void AdminTest02() {
        String ADMIN_LOGIN = "admin@energy-aware.com";
        String ADMIN_PASSWORD = "bonny5_worktable";
        String WINDSOR = "windsor";
        String WINDSOR_ID = "yVqQsLETRk2C1D1EjlgEjA";

        Report("Admin Test");
        LoginPage.signIn(ADMIN_LOGIN, ADMIN_PASSWORD);

        When("I login as Admin");
        UserBar.toggleUserMenu();

        Then("I can change to a user");
        UserBar.adminSelectUserByNameID(WINDSOR, WINDSOR_ID);
        Report("Admin Test Passed!");
    }


}