package util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    final static Logger logger = (Logger) Logger.getLogger(DriverFactory.class);

    public static WebDriver createDriver(BrowserType type) {
        WebDriver driver;
        setDriverPath();
        if (type == BrowserType.CHROME) {
            driver = new ChromeDriver();
            //chrome opts
            logger.info("Created driver with Chrome type of browser");
            return setDriverDefaults(driver);
        }
        if (type == BrowserType.FIREFOX) {
            driver = new FirefoxDriver();
            logger.info("Created driver with Firefox type of browser");
            return setDriverDefaults(driver);
        }
        return null;
    }

    private static WebDriver setDriverDefaults(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Set configuration for browsers");
        return driver;
    }

    private static void setDriverPath() {
        Properties properties = new Properties();

        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty(properties.getProperty("chrome.key"), properties.getProperty("chrome.path"));
        System.setProperty(properties.getProperty("firefox.key"), properties.getProperty("firefox.path"));
        logger.info("Set path for browsers");
    }
}
