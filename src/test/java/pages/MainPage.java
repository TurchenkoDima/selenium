package pages;

import components.FooterComponent;
import components.HeaderComponent;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import components.LoginComponent;

import java.util.List;


public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String logoutPhraseOnMainPage = "Регистрация";
    private final String BASE_URL = "https://mail.ru/";

    final static Logger logger = Logger.getLogger(MainPage.class);

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

    @FindBy(xpath = "//textarea[@class = 'js-input compose__labels__input']")
    private WebElement addressTextarea;

    @FindBy(xpath = "//input[@name = 'Subject']")
    private WebElement subjectInput;

    @FindBy(xpath = "//td[@class = 'mceIframeContainer mceFirst mceLast']")
    private WebElement bodyTd;

    @FindBy(xpath = "//span[text()='Отправить']")
    private WebElement sendMessageButton;

    @FindBy(xpath = "//span[text()='Сохранить']")
    private WebElement saveMessageButton;

    @FindBy(xpath = "//span[text()='Черновики']")
    private WebElement draftButton;

    @FindBy(xpath = "//td[@class = 'compose__control']")
    private WebElement elementToMoveToWriteBodyMessage;

    @FindBy(xpath = "//div[@class='b-datalist b-datalist_letters b-datalist_letters_to']//div[@data-bem='b-datalist__item']")
    private List<WebElement> draftMessageList;

    @FindBy(xpath = "//div[@class='b-datalist b-datalist_letters b-datalist_letters_to']" +
            "//div[@class = 'b-datalist__item__subj']/span")
    private List<WebElement> bodyLabelDraftPartList;

    @FindBy(xpath = "//div[@class='b-datalist b-datalist_letters b-datalist_letters_to']" +
            "//div[@class = 'b-datalist__item__subj']")
    private List<WebElement> subjectLabelDraftPartList;

    @FindBy(xpath = "//div[@class='b-datalist b-datalist_letters b-datalist_letters_to']" +
            "//div[@class = 'b-datalist__item__addr']")
    private List<WebElement> addressLabelDraftPartList;

    @FindBy(xpath = "//span[text()='Отправленные']")
    private WebElement sentButton;

    @FindBy(xpath = "//div[@id='b-letters']/div/div[position()=8]//div[@class='b-datalist__item__subj']")
    private List<WebElement> subjectLabelSentPartList;

    @FindBy(xpath = "//div[@id='b-letters']/div/div[position()=8]//span[@class='b-datalist__item__subj__snippet']")
    private List<WebElement> bodyLabelSentPartList;

    @FindBy(xpath = "//div[@id='b-letters']/div/div[position()=8]//div[@class='b-datalist__item__addr']")
    private List<WebElement> addressLabelSentPartList;

    @FindBy(xpath = "//div[@id='b-letters']/div/div[position()=8]//div[@class='b-datalist__body']/div")
    private List<WebElement> sentMessageList;

    @FindBy(xpath = "//a[@class='toolbar__message_info__link']")
    private WebElement linkToDraftInWriteProcess;

    @FindBy(xpath = "//body[@id='tinymce']")
    private WebElement bodyField;

    @FindBy(xpath = "//iframe[@title='{#aria.rich_text_area}']")
    private WebElement frameToWriteBodyMessage;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 120);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(BASE_URL);
        logger.info("Opened " + BASE_URL + ".");
    }

    public boolean isLoginedWithUser(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(writeMessageButton));
        logger.info("Check username label.");
        return usernameLabel.getText().equals(username);
    }

    public boolean isLogined() {
        wait.until(ExpectedConditions.elementToBeClickable(writeMessageButton));
        logger.info("Check 'Входящие' button.");
        if (!incomingMessageLabel.isDisplayed())
            return false;
        logger.info("Check 'Написать письмо' button.");
        return writeMessageButton.isDisplayed();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='выход']"))).click();
        logger.info("Click 'выход' button.");
    }

    public boolean isLogout() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("PH_regLink")));
        logger.info("Check 'Регистрация' label to logout.");
        return regPhraseOnMainPage.getText().equals(logoutPhraseOnMainPage);
    }

    public void enterCredential(String username, String password) {
        LoginComponent loginComponent = new LoginComponent(driver);
        loginComponent.enterCredential(username, password);
    }

    public boolean isTrueHeaderAndFooterAfterLogin() {
        HeaderComponent headerComponent = new HeaderComponent(driver);
        FooterComponent footerComponent = new FooterComponent(driver);
        return headerComponent.isTrueHeaderAfterLogin() && footerComponent.isTrueFooterAfterLogin();
    }

    public void writeNewMessageAndSave(String address, String subject, String body) {
        wait.until(ExpectedConditions.elementToBeClickable(writeMessageButton)).click();
        addressTextarea.clear();
        addressTextarea.sendKeys(address);
        logger.info("Enter address in field.");
        subjectInput.clear();
        subjectInput.sendKeys(subject);
        logger.info("Enter subject in field.");
        driver.switchTo().frame(frameToWriteBodyMessage);
        bodyField.sendKeys(body);
        driver.switchTo().defaultContent();
        logger.info("Enter body text in field.");
        wait.until(ExpectedConditions.elementToBeClickable(saveMessageButton));
        saveMessageButton.click();
        logger.info("Click 'Сохранить' button.");
        linkToDraftInWriteProcess.click();
        logger.info("Click 'Черновик' button.");
    }

    public void clickDraftButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Перейти во Входящие']")));
        draftButton.click();
        logger.info("Click 'Черновик' button.");
    }

    public boolean isDraftMessage(String address, String subject, String body) {
        logger.info("Check message in draft part.");
        if (!draftMessageList.isEmpty()) {
            for (int i = 0; i < addressLabelDraftPartList.size(); i++) {
                if (addressLabelDraftPartList.get(i).getText().equals(address)
                        && bodyLabelDraftPartList.get(i).getText().contains(body)
                        && subjectLabelDraftPartList.get(i).getText()
                        .replace(bodyLabelDraftPartList.get(i).getText(), "").equals(subject))
                    return true;
            }
        }
        return false;
    }

    public void sendDraftMessage(String address, String subject, String body) {
        if (!draftMessageList.isEmpty()) {
            for (int i = 0; i < addressLabelDraftPartList.size(); i++) {
                if (addressLabelDraftPartList.get(i).getText().equals(address)
                        && bodyLabelDraftPartList.get(i).getText().contains(body)
                        && subjectLabelDraftPartList.get(i).getText()
                        .replace(bodyLabelDraftPartList.get(i).getText(), "").equals(subject))
                    draftMessageList.get(i).click();
            }
        }
        logger.info("Click last draft message.");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Отправить']")));
        sendMessageButton.click();
        logger.info("Click 'Отправить' button.");
    }

    public void clickSentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(sentButton));
        sentButton.click();
        logger.info("Click 'Отправленные' button.");
    }

    public boolean isSentMessage(String address, String subject, String body) {
        logger.info("Check message in sent part.");
        for (int i = 0; i < addressLabelSentPartList.size(); i++) {
            if (addressLabelSentPartList.get(i).getText().equals(address)
                    && bodyLabelSentPartList.get(i).getText().contains(body)
                    && subjectLabelSentPartList.get(i).getText()
                    .replace(bodyLabelSentPartList.get(i).getText(), "").equals(subject))
                return true;
        }
        return false;
    }
}
