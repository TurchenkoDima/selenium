import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;

@Listeners(SimpleTestListener.class)
public class LogoutTest {
    private WebDriver driver;
    private static final String USERNAME = "375445930312vk@mail.ru";
    private static final String PASSWORD = "bk-zhlobin#13";

    public LogoutTest(WebDriver driver) {
        this.driver = driver;
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

        driver.quit();
    }
}
