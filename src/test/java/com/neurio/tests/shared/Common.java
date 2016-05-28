package com.neurio.tests.shared;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Robert on 2016-05-18.
 * This is a class for common methods used by all helper classes
 */
public class Common extends Browser{

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    /**
     * Forces Selenium to wait a set amount of seconds
     * @param seconds - how long in seconds to wait
     */
    public static void wait(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Get properties from the the config properties file
     * @param value - The particular value wanted
     * @param defaultValue The default value given if method fails
     * @return String property value
     */
    public static String getPropertyValue(String value, String defaultValue){
        Properties prop = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream;

        try {
            inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        // Default value is set to here, if not found
        return prop.getProperty(value, defaultValue);
    }

    /**
     * Check for element to be on web page
     * @param selector - CSS selector for the element
     * @return boolean true or false
     */
    public static boolean checkForElement(String selector){
        return driver.findElements(By.cssSelector(selector)).size() != 0;
    }

    /**
     * Implicitly wait an error alert to appear on page
     */
    public static boolean assertError(){
        try{
            Common.waitForElement(StringRef.ERROR_ALERT_CSS_SELECTOR);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Check for error to be on web page
     * @return boolean true or false
     */
    public static boolean checkForError(){
        try{
            waitForErrors();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Check for success to be on web page
     * @return boolean true or false
     */
    public static boolean checkForSuccess(){
        try{
            waitForSuccess();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Check if an error occurred on page
     */
    public static void waitForErrors(){
        Common.waitForElementClass(StringRef.HAS_ERROR_CLASS_NAME);
    }

    /**
     * Check if a success alert occurred on page
     */
    public static void waitForSuccess(){
        Common.waitForElementClass(StringRef.ALERT_SUCCESS);
    }

    /**
     * Enter value in a web element by name
     * @param name - Name selector
     * @param input - Input to be entered
     */
    public static void enterValueInElementByName(String name, String input){
        getElementByName(name).clear();
        getElementByName(name).sendKeys(input);
    }

    /**
     * Implicitly wait for an element to appear for 10 seconds
     * @param selector - CSS selector for the element
     */
    public static void waitForElement(final String selector){
        int maxTime = Integer.parseInt(getPropertyValue("timeout", "10"));
        (new WebDriverWait(driver, maxTime)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(By.cssSelector(selector)).size() != 0;

            }
        });
    }

    /**
     * Implicitly wait for an element to appear for 10 seconds
     * @param selector - Class name selector for the element
     */
    public static void waitForElementClass(final String selector){
        int maxTime = Integer.parseInt(getPropertyValue("timeout", "10"));
        (new WebDriverWait(driver, maxTime)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(By.className(selector)).size() != 0;
            }
        });
    }

    /**
     * Implicitly wait for an element to appear for 10 seconds
     * @param selector - ID selector for the element
     */
    public static void waitForElementID(final String selector){
        int maxTime = Integer.parseInt(getPropertyValue("timeout", "10"));
        (new WebDriverWait(driver, maxTime)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(By.id(selector)).size() != 0;
            }
        });
    }

    /**
     * Take a screenshot during the test
     * @param filename - The file name to save as
     */
    public static void takeScreenshot(String filename) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/web-screenshots/" + filename + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Send a GET request
     * @param token - Authorization token
     * @param url - URL of GET Request
     * @return String - Response from API Call
     */
    public static String getRequest(String token, String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        if(responseCode == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response: " + response.toString());

            return response.toString();
        } else {
            System.out.println("No Response");
            return "";
        }
    }

    /**
     * Send a POST request
     * @param authorized - Flag for whether this request is authorized already (You have a token)
     * @param token - Authorization token
     * @param url - URL of GET Request
     * @param reqBody - POST Request Body
     * @return String - Response from API Call
     */
    public static String postRequest(boolean authorized,
                                               String token,
                                               String url,
                                               HashMap<String, String> reqBody) throws Exception {

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        if(authorized){
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", token);
        } else {
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        }

        String urlParameters = getPostDataString(reqBody);

        // Send post request
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        BufferedWriter wr = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        wr.write(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
        if(responseCode == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response: " + response.toString());

            return response.toString();
        } else {
            System.out.println("No Response");
            return "";
        }
    }
}
