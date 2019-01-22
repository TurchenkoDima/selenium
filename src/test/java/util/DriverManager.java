package util;

import components.LoginComponent;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> threadLocalScope = new  ThreadLocal<>();
    final static Logger logger = (Logger) Logger.getLogger(LoginComponent.class);

    public final static WebDriver getDriver() {
        return threadLocalScope.get();
    }

    public final static void setDriver(WebDriver user) {
        threadLocalScope.set(user);
    }
}
