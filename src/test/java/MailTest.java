import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import selenium.pages.MainPage;
import util.DriverSingleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Listeners(SimpleTestListener.class)

public class MailTest {

    private WebDriver driver;

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        Properties properties = new Properties();

        FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties");
        properties.load(inputStream);

        String browser = properties.getProperty("mailtest.browser");
        String username = properties.getProperty("mailtest.username");
        String password = properties.getProperty("mailtest.password");

        return new Object[][]{
                {username,password,browser},
                {"375445930312vk@mail.ru","bk-zhlobin#13","firefox"}
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
    public void oneCanLoginToMail(String username, String password, String typeOfBrowser){

        driver = DriverSingleton.getDriver(typeOfBrowser);

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

        //driver.quit();
        //if(util.DriverSingleton.getDriver()!=null)
            //util.DriverSingleton.closeDriver();
    }
}
