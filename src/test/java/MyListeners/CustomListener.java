package MyListeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Custom TestNG listener for logging test execution events.
 */
public class CustomListener implements ITestListener {

    /**
     * Logs the test method name and its parameters when execution starts.
     *
     * @param result the result object containing test execution details
     */
    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("Test Started" + result.getMethod().getMethodName());
        System.out.println("Test Parameters" + result.getParameters());
    }

    /**
     * Logs the successful completion of a test.
     *
     * @param result the result object containing test execution details
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Success" + result.isSuccess());
    }

    /**
     * Logs when a test fails.
     *
     * @param result the result object containing test execution details
     */
    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("Test Failure");
    }

    /**
     * Logs when a test is skipped.
     *
     * @param result the result object containing test execution details
     */
    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("Test Skipped");
    }
}