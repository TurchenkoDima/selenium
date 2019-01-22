package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static WebDriver createDriver( String type){
        Properties properties = new Properties();

        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        WebDriver driver;

        if(type.equals("chrome")){
            System.setProperty(properties.getProperty("chrome.key"), properties.getProperty("chrome.path"));
            driver = new ChromeDriver();
            //chrome opts
            return manageDriver(driver);
        }
        if(type.equals("firefox")){
            System.setProperty(properties.getProperty("firefox.key"), properties.getProperty("firefox.path"));
            driver = new FirefoxDriver();
            return manageDriver(driver);
        }

        return null;
    }

    private static WebDriver manageDriver(WebDriver driver){
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverManager.setDriver(driver);
        return driver;
    }
}
