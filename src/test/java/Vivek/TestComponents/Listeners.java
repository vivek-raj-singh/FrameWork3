package Vivek.TestComponents;

import Vivek.Resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReporterNG.getReportObject();
    ExtentTest Test;

    public void onTestStart(ITestResult result) {
        Test=extent.createTest(result.getMethod().getMethodName());
        // not implemented
    }
    public void onTestSuccess(ITestResult result) {
        Test.log(Status.PASS,"test is passed");
        // not implemented
    }

    public void onTestFailure(ITestResult result) {
        // not implemented
//        Test.log(Status.FAIL,"test is failed");
        Test.fail(result.getThrowable());
        try {
            driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }


        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context) {
        extent.flush();
        // not implemented
    }


}
