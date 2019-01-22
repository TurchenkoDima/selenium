import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.DriverSingleton;

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
        String string = iTestContext.getSuite().getParameter("typeOfBrowser");
        DriverSingleton.getDriver(string);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        DriverSingleton.closeDriver();
    }
}
