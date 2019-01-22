import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.DriverSingleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SimpleTestListener implements ITestListener {

    final static Logger logger = (Logger) Logger.getLogger(SimpleTestListener.class);
    private long time;


    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("The name of the testcase failed is :"+iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Properties properties = new Properties();

        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = properties.getProperty("mailtest.browser");
        DriverSingleton.getDriver(browser);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        DriverSingleton.closeDriver();
    }
}
