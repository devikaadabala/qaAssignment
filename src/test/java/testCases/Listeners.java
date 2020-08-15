package testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import framework.BasePage;
import framework.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BasePage implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.extentReportsPath();
    public static ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = extent.createTest(iTestResult.getMethod().getMethodName());
        threadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        threadLocal.get().log(Status.PASS, "Test Passed");
        String testMethodName = iTestResult.getMethod().getMethodName();

        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            threadLocal.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver), iTestResult.getMethod().getMethodName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        threadLocal.get().fail(iTestResult.getThrowable());
        String testMethodName = iTestResult.getMethod().getMethodName();

        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            threadLocal.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver), iTestResult.getMethod().getMethodName());

        } catch (IOException e) {
            e.printStackTrace();
        }

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
        extent.flush();
    }
}
