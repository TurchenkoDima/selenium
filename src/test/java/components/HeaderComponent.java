package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderComponent {
    final static Logger logger = (Logger) Logger.getLogger(LoginComponent.class);

    private WebDriver driver;

    private WebElement mailRULabel;
    private WebElement postLael;
    private WebElement loginButton;

    public HeaderComponent(WebDriver driver) {
        this.mailRULabel = mailRULabel;
    }
}
