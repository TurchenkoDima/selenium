package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import components.LoginComponent;


public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String logoutPhraseOnMainPage = "Регистрация";
    private final String BASE_URL = "https://mail.ru/";

    final static Logger logger = (Logger) Logger.getLogger(MainPage.class);

    @FindBy(xpath = "//i[@id = 'PH_user-email']")
    private WebElement usernameLabel;

    @FindBy(id = "PH_regLink")
    private WebElement regPhraseOnMainPage;

    @FindBy(xpath = "//div[@id='b-nav_folders']//span[@class= 'b-nav__item__text b-nav__item__text_unread']")
    private WebElement incomingMessageLabel;

    @FindBy(id = "pm-logo__link__pic")
    private WebElement mainLogoImg;

    @FindBy(xpath = "//span[@class = 'b-toolbar__btn__text b-toolbar__btn__text_pad']")
    private WebElement writeMessageButton;

    @FindBy(xpath = "//a[text()='выход']")
    private WebElement logoutButton;

    public MainPage(WebDriver driver) {
        this.driver=driver;
        wait = new WebDriverWait(driver, 120);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(BASE_URL);
        logger.info("Opened " + BASE_URL + ".");
    }

    public boolean isLoginedWithUser(String username){
        wait.until(ExpectedConditions.elementToBeClickable(writeMessageButton));
        logger.info("Check username label.");
        return usernameLabel.getText().equals(username);
    }

    public boolean isLogined(){
        wait.until(ExpectedConditions.elementToBeClickable(writeMessageButton));
        logger.info("Check 'Входящие' button.");
        if(!incomingMessageLabel.isDisplayed())
            return false;
        logger.info("Check 'Написать письмо' button.");
        return writeMessageButton.isDisplayed();
    }

    public void logout() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='выход']")));
        driver.get(logoutButton.getAttribute("href"));
        logger.info("Click 'выход' button.");
    }

    public boolean isLogout(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("PH_regLink")));
        logger.info("Check 'Регистрация' label");
        return !regPhraseOnMainPage.getText().equals(logoutPhraseOnMainPage);
    }

    public void enterCredential(String username, String password){
        LoginComponent loginComponent = new LoginComponent(driver);
        loginComponent.enterCredential(username,password);
    }
}
