import org.testng.annotations.Factory;

public class LogoutFactory {
    @Factory
    public Object[] factoryMethod() {
        return new Object[] { new LogoutTest("chrome"), new LogoutTest("firefox")  };
    }
}
