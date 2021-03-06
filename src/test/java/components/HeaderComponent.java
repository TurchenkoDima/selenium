package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class HeaderComponent {

    final static Logger logger = Logger.getLogger(LoginComponent.class);

    private WebDriver driver;
    private Wait<WebDriver> wait;

    @FindBy(xpath = "//a[@class = 'x-ph__link x-ph__link_first']")
    private WebElement mailRuLabel;

    @FindBy(xpath = "//span[@class = 'x-ph__link__text x-ph__link_selected']")
    private WebElement postLabel;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(this.driver, this);
    }

    public boolean isTrueHeaderAfterLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("portal-headline")));
        logger.info("Check Header after login");
        String s = postLabel.getText();
        return ((mailRuLabel.isDisplayed() && mailRuLabel.getText().equals("Mail.Ru"))
                && (postLabel.isDisplayed() && postLabel.getText().equals("Почта")));
    }
}
