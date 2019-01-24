import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.DriverManager;

public class SimpleTestListener implements ITestListener {

    final static Logger logger = Logger.getLogger(SimpleTestListener.class);
    private long time;


    @Override
    public void onTestStart(ITestResult iTestResult) {
        //DriverFactory.createDriver();
        //DriverManager.setDriver(DriverFactory.createDriver());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("The name of the testcase failed is :" + iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        //DriverManager.getDriver().quit();
    }
}
