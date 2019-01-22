import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import util.DriverManager;
import util.DriverSingleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//@Listeners(SimpleTestListener.class)

public class MailTest {

    private WebDriver driver;

    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        return new Object[][]{
                {"375445930312vk@mail.ru","bk-zhlobin#13","chrome"},
                {"375445930312vk@mail.ru","bk-zhlobin#13", "firefox"}
        };
    }

    /*@BeforeTest
    public void testbefore(){
        //driver = util.DriverSingleton.getDriver();
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        long id = Thread.currentThread().getId();
        System.out.println("Simple test-method Two. Thread id is: " + id);
    }*/

    @Test(dataProvider = "loginData")
    public void oneCanLoginToMail(String username, String password, String browser){

        driver = DriverManager.getDriver();

        long id = Thread.currentThread().getId();
        System.out.println("Test login. Thread id is: " + id);

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.enterCredential(username, password);

        SoftAssert asert = new SoftAssert();

        asert.assertTrue(mainPage.isLogined());
        asert.assertTrue(mainPage.isLoginedWithUser(username));

        asert.assertAll();
    }

    @AfterTest
    public void testafter(){
        long id = Thread.currentThread().getId();
        System.out.println("After login. Thread id is: " + id);
    }
}
