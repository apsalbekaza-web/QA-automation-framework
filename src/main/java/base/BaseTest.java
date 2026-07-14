package base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.ExtentReportManager;
import utils.Log;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver; 
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport(){
        extent = ExtentReportManager.gExtentReports();
    }
    @AfterSuite
    public void tearDownReport(){
        extent.flush();
    }

    @BeforeMethod
    public void setUp(){

        Log.info("Setting up WebDriver...");
        driver = new ChromeDriver(); 
        driver.manage().window().maximize();
        Log.info("Navigating to URL...");
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @AfterMethod
    public void tearDown(ITestResult result){ 

        if(result.getStatus() == ITestResult.FAILURE){
            String screenshotPath = ExtentReportManager.captureScreenshot(driver, "LoginFailure");
            test.fail("Test Failed.. Check Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()); // takes screenshot from the path and attaches to the test report 

        }

        if (driver != null){
            Log.info("Closing Browser...");
        }
    }
}
