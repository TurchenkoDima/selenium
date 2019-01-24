import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
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
                {"375445930312vk@mail.ru", "bk-zhlobin#13", "test", "\"test\""}
        };
    }

    @Test(dataProvider = "data")
    public void oneCanToDoScenario(String username, String password, String subject, String body) {
        SoftAssert asert = new SoftAssert();
        MainPage mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.enterCredential(username, password);

        asert.assertTrue(mainPage.isLogined());
        asert.assertTrue(mainPage.isTrueHeaderAndFooterAfterLogin());

        mainPage.writeNewMessageAndSend(username, subject, body);


        asert.assertAll();
    }
}
