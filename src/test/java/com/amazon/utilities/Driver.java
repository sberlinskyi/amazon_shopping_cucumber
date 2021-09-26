package com.amazon.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class Driver {
    private Driver() {}

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();    // currently it is NULL as we did not instantiate it

    public static WebDriver getDriver() {
        //if(driver == null) {
        if(driverPool.get() == null) {

            // Wrapping code in SYNCHRONIZED to allow multi-thread and not confuse WebDriver object
            synchronized (Driver.class) {

                // reading a browser name from config file
                String browserType = ConfigReader.getProperty("browser");

                switch (browserType) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions optionsChrome = new ChromeOptions();
                        optionsChrome.addArguments("--kiosk");
                        //driver = new ChromeDriver(optionsChrome);
                        driverPool.set(new ChromeDriver());
                        //driverPool.get().manage().window().maximize();

                        break;

                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions optionsFirefox = new FirefoxOptions();
                        optionsFirefox.addArguments("--kiosk");
                        //driver = new FirefoxDriver(optionsFirefox);
                        driverPool.set(new FirefoxDriver());
                        //driverPool.get().manage().window().maximize();

                        break;

                    default:
                        System.out.println("Invalid browser type");
                        return null;
                }
                //driverPool.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
        }
        // If driver was created - return driver
        return driverPool.get();
    }

    /**
     * This method makes sure we have some sort of session or driver id
     * If driver is not null - quit session and assign it a value of null
     */
    public static void closeDriver() {
        /*
        if(driver!=null) {
            driver.quit();
            driver = null;
         */

        if(driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }

}
