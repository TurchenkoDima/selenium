import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.MainPage;

@Listeners(SimpleTestListener.class)
public class ScenarioTest {
    WebDriver driver;

    public ScenarioTest(WebDriver driver) {
        this.driver = driver;
    }

    @DataProvider(name = "data")
    public Object[][] scenarioData() {
        return new Object[][]{
                {"375445930312vk@mail.ru", "bk-zhlobin#13", "subject" + getRandomNumber(), "body" + getRandomNumber()}
        };
    }

    @Test(dataProvider = "data")
    public void oneCanToDoScenario(String username, String password, String subject, String body) {
        SoftAssert asert = new SoftAssert();
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.enterCredential(username, password);
        asert.assertTrue(mainPage.isLogined(), "Can't login.");
        asert.assertTrue(mainPage.isTrueHeaderAndFooterAfterLogin(), "Footer and Header are wrong.");
        mainPage.writeNewMessageAndSave(username, subject, body);
        asert.assertTrue(mainPage.isDraftMessage(username, subject, body), "Message isn't displayed in Draft.");
        mainPage.sendDraftMessage(username, subject, body);
        mainPage.clickDraftButton();
        asert.assertFalse(mainPage.isDraftMessage(username, subject, body), "Message is displayed in Draft after sent.");
        mainPage.clickSentButton();
        asert.assertTrue(mainPage.isSentMessage(username, subject, body), "Message isn't displayed in Sent after sent.");
        mainPage.logout();
        asert.assertTrue(mainPage.isLogout(), "Can't logout.");

        asert.assertAll();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    private static double getRandomNumber(){
        return Math.floor((Math.random() * 100) + 1);
    }
}
