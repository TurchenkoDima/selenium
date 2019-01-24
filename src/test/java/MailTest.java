import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.MainPage;

@Listeners(SimpleTestListener.class)

public class MailTest {

    private WebDriver driver;

    public MailTest(WebDriver driver) {
        this.driver = driver;
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"375445930312vk@mail.ru", "bk-zhlobin#13"}
        };
    }

    @Test(dataProvider = "loginData")
    public void oneCanLoginToMail(String username, String password) {

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
    public void testafter() {
        long id = Thread.currentThread().getId();
        System.out.println("After login. Thread id is: " + id);

        driver.quit();
    }
}
