package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class FooterComponent {
    final static Logger logger = Logger.getLogger(LoginComponent.class);

    private WebDriver driver;
    private Wait<WebDriver> wait;

    @FindBy(xpath = "//span[@class = 'portal-footer__link__text']")
    private WebElement mailRuLabel;

    @FindBy(xpath = "//span[text() = 'Блог Почты']")
    private WebElement blogPostLabel;




    public FooterComponent(WebDriver driver) {
        this.driver = driver;
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(this.driver, this);
    }

    public boolean isTrueFooterAfterLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = 'portal-footer__portal-links']")));
        Actions action = new Actions(driver);
        action.moveToElement(mailRuLabel);
        return mailRuLabel.isDisplayed() && mailRuLabel.getText().equals("Mail.Ru") && blogPostLabel.isDisplayed();
    }
}
