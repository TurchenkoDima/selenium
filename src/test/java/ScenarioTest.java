import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import util.DriverManager;

@Listeners(SimpleTestListener.class)
public class ScenarioTest {
    WebDriver driver;

    public ScenarioTest(WebDriver driver) {
        this.driver = driver;
    }

    @DataProvider(name = "data")
    public Object[][] scenarioData() {
        return new Object[][]{
                {"375445930312vk@mail.ru", "bk-zhlobin#13", "subject" + Math.floor((Math.random() * 100) + 1),
                        "body" + Math.floor((Math.random() * 100) + 1)}
        };
    }

    @Test(dataProvider = "data")
    public void oneCanToDoScenario(String username, String password, String subject, String body) {
        SoftAssert asert = new SoftAssert();
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.enterCredential(username, password);
        asert.assertTrue(mainPage.isLogined(), "1");
        asert.assertTrue(mainPage.isTrueHeaderAndFooterAfterLogin(), "2");
        mainPage.writeNewMessageAndSave(username, subject, body);
        asert.assertTrue(mainPage.isDraftMessage(username, subject, body), "3");
        mainPage.sendDraftMessage();
        mainPage.clickDraftButton();
        asert.assertFalse(mainPage.isDraftMessage(username, subject, body), "4");
        mainPage.clickSentButton();
        asert.assertTrue(mainPage.isSentMessage(username, subject, body), "5");
        mainPage.logout();
        asert.assertTrue(mainPage.isLogout(), "6");

        asert.assertAll();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
