package com.neurio.tests;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.neurio.tests.shared.*;
import org.testng.annotations.Test;

import java.util.*;

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
        List<Set<String>> listOfLists = Lists.newArrayList();
        listOfLists.add(new LinkedHashSet<>(Arrays.asList("Original Soup", "Spicy Soup", "Watercress Soup", "Thai Spicy Soup", "Malaysia Spicy Soup")));
        listOfLists.add(new LinkedHashSet<>(Arrays.asList("Udon", "Ramen", "Egg Noodle", "Flat Rice Noodle", "Vermicelli", "Instant Noodle")));
        listOfLists.add(new LinkedHashSet<>(Arrays.asList("Fish Cube", "Fish Ball", "Ham", "Squid", "Seaweed")));
        Set<List<String>> combo = findAllCombinations(listOfLists);
        System.out.println(combo);
        System.out.println("Generated " + combo.size() + " combinations");
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


    private Set<List<String>> findAllCombinations(List<Set<String>> arrays){
        return Sets.cartesianProduct(arrays);
    }
}