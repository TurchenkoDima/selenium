import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import selenium.pages.MainPage;
import util.DriverSingleton;

import java.util.concurrent.TimeUnit;

public class LogoutTest {
    private WebDriver driver;
    private static final String USERNAME = "375445930312vk@mail.ru";
    private static final String PASSWORD = "bk-zhlobin#13";

    public LogoutTest( String typeOfDriver) {
        driver = DriverSingleton.getDriver(typeOfDriver);
    }

    @BeforeMethod()
    public void testbefore() {
        long id = Thread.currentThread().getId();
        System.out.println("Before logout. Thread id is: " + id);

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.enterCredential(USERNAME, PASSWORD);
    }

    @Test()
    public void oneCanLogOut() {
        long id = Thread.currentThread().getId();
        System.out.println("Test logout. Thread id is: " + id);

        MainPage mainPage = new MainPage(driver);
        mainPage.logout();
        Assert.assertTrue(mainPage.isLogout());

    }

    @AfterMethod
    public void testafter() {
        long id = Thread.currentThread().getId();
        System.out.println("After logout. Thread id is: " + id);

            DriverSingleton.closeDriver();
    }
}
