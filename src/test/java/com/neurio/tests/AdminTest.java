package com.neurio.tests;

import com.neurio.tests.shared.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Robert on 2016-05-17.
 * Admin Test
 */
public class AdminTest extends BasicTest{

    String authToken = "";

    @Test
    public void AdminTest01() {
        authToken = API.getAuthToken();
        HashMap<String, String> map = new HashMap<>();
        map.put(StringRef.GRANT_TYPE, StringRef.CLIENT_CREDENTIALS);
        map.put(StringRef.CLIENT_ID, StringRef.ADMIN_CLIENT_ID);
        map.put(StringRef.CLIENT_SECRET, StringRef.ADMIN_CLIENT_SECRET);
        try {
            String response = API.getRequest(authToken, StringRef.API_STAGING_URL_PREFIX +
                    "locations/yVqQsLETRk2C1D1EjlgEjA");
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


    private ArrayList<ArrayList<String>> findAllCombinations(ArrayList<ArrayList<String>> arrays){
        ArrayList<ArrayList<String>> combinations = new ArrayList<>();
        for(String item1: arrays.get(0)){
            for(String item2: arrays.get(1)){
                for(String item3: arrays.get(2)){
                    for(String item4: arrays.get(3)){
                        for(String item5: arrays.get(4)){
                            for(String item6: arrays.get(5)){
                                for(String item7: arrays.get(6)){
                                    ArrayList<String> temp = new ArrayList<String>() {
                                        {
                                            add(item1);
                                            add(item2);
                                            add(item3);
                                            add(item4);
                                            add(item5);
                                            add(item6);
                                            add(item7);
                                        }
                                    };
                                    combinations.add(temp);
                                }
                            }
                        }
                    }
                }
            }
        }
        return combinations;
    }
}