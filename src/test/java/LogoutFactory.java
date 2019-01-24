import org.testng.annotations.Factory;
import util.BrowserType;
import util.DriverFactory;

public class LogoutFactory {
    @Factory
    public Object[] factoryMethod() {
        return new Object[]{new LogoutTest(DriverFactory.createDriver(BrowserType.CHROME)),
                new LogoutTest(DriverFactory.createDriver(BrowserType.FIREFOX)),
                new MailTest(DriverFactory.createDriver(BrowserType.FIREFOX)),
                new MailTest(DriverFactory.createDriver(BrowserType.CHROME))};
    }
}
