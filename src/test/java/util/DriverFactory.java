package util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    final static Logger logger = Logger.getLogger(DriverFactory.class);

    public static WebDriver createDriver(BrowserType type) throws Exception {
        WebDriver driver;
        setDriverPath();
        switch (type) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-infobars");//Prevent infobars from appearing.
                options.addArguments("--disable-boot-animation");//Disables wallpaper boot animation
                driver = new ChromeDriver(options);
                logger.info("Created driver with Chrome type of browser");
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                logger.info("Created driver with Firefox type of browser");
                break;
            default:
                throw new Exception("Browser type is incorrect. This type of browser is not supported.");
        }
        return setDriverDefaults(driver);
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
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.path"));
        System.setProperty("webdriver.gecko.driver", properties.getProperty("firefox.path"));
        logger.info("Set path for browsers");
    }
}
