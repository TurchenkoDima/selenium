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

    @DataProvider(name = "data")
    public Object[][] scenarioData() {
        return new Object[][]{
                {"375445930312vk@mail.ru", "bk-zhlobin#13"}
        };
    }

    @Test(dataProvider = "data")
    public void oneCanToDoScenario(String username, String password) {
        driver = DriverManager.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.enterCredential(username, password);

        SoftAssert asert = new SoftAssert();
        asert.assertTrue(mainPage.isLogined());

        asert.assertAll();
    }
}
