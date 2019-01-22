package util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.components.LoginComponent;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {

    private static WebDriver driver;
    private static String typeOfBrowser;
    final static Logger logger = (Logger) Logger.getLogger(LoginComponent.class);

    private DriverSingleton(){}


    public static WebDriver getDriver(String type){
        if (null == driver || !typeOfBrowser.equals(type)){
            typeOfBrowser=type;
            driver = DriverFactory.createDriver(type);
        }
        logger.info("Browser started");
        return driver;
    }

    public static void closeDriver(){
        driver.close();
        driver.quit();
        driver = null;
        logger.info("Browser quited");
    }
}
