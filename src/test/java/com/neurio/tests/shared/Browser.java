package com.neurio.tests.shared;

/**
 * Created by Robert on 2016-05-18.
 * This class setups the browser web driver before testing can begin
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Browser {

    public static WebDriver driver;

    /**
     * Reads the arguments in the config.properties file
     * Setups the browser based on the entry given in
     * the browser variable
     */
    @BeforeSuite
    public void initializeBrowser() {
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

        String browser = prop.getProperty("browser");

        if(browser.contains("chrome")){
            try {
                URL url = new URL("http://localhost:9515");
                driver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
            } catch (MalformedURLException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        } else if(browser.contains("htmlunit")) {
            driver = new HtmlUnitDriver();
        } else if(browser.contains("IE")){
            driver = new InternetExplorerDriver();
        } else {
            driver = new FirefoxDriver();
        }
    }

    /**
     * Closes browser after suite is complete
     */
    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }
}