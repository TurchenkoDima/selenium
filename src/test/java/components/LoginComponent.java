package selenium.components;

import com.google.common.base.Function;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class LoginComponent {

    private WebElement passwordInput;
    private WebElement usernameInput;
    private WebElement loginButton;

    final static Logger logger = (Logger) Logger.getLogger(LoginComponent.class);

    private WebDriver driver;
    private Wait<WebDriver> wait;


    public LoginComponent(WebDriver driver) {
        this.driver = driver;
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public void  enterCredential (String username, String password){
        passwordInput = wait
                .until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("mailbox:password")));
        usernameInput = wait
                .until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("mailbox:login")));
        usernameInput.clear();
        usernameInput.sendKeys(username);
        logger.info("Entered username :" + username + ".");
        passwordInput.clear();
        passwordInput.sendKeys(password);
        logger.info("Entered password.");
        loginButton = wait
                .until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("mailbox:submit")));

        //Actions actions = new Actions(driver);
        //actions.moveToElement(loginButton).click().perform();

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", loginButton);

        logger.info("Click button 'Войти'.");
    }
}
