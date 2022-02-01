package Utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {
    public WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static  ExtentTest logger;
    @BeforeSuite(groups = ( "Sanity" ))
    public void beforeSuite()
    {
        //Report Directory and Report Name
        extent = new ExtentReports("RiskCovry_execution_report.html", true); //Provide Desired Report Directory Location and Name
        extent.loadConfig(new File("src/test/java/Utility/ExtentConfig.xml")); //Supporting File for Extent Reporting
        extent.addSystemInfo("Environment","RiskCovry site"); //It will provide Execution Machine Information
    }

    @BeforeMethod(groups = { "Sanity" })
    public  void beforeMethod(Method method)
    {
        test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
        test.assignAuthor("Plaban Kumar Dwivedy"); //Test Script Author Name
        test.assignCategory("RiskCovry  :: " + "ORT ENV" + " :: APP VERSION - "+ "ver"); //Test Category Defined Here
    }

    public String getScreenShot(WebDriver driver, String screenshotName, WebElement element) throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        BufferedImage fullImg = ImageIO.read(source);
        //Get the location of element on the page
        Point point = element.getLocation();
        //Get width and height of the element
        int eleWidth = element.getSize().getWidth();
        int eleHeight = element.getSize().getHeight();
        //Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth,
                eleHeight);
        ImageIO.write(eleScreenshot, "png", source);
        String destination = "target/screenShots/"+screenshotName+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
        return destination;
    }
    public static String getFailScreenshot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = "target/screenShots/"+screenshotName+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    //Test Case Reporting Ends Here
    @AfterMethod
    public void getResult(ITestResult result) throws Exception
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            //String screenShotPath = getScreenshot(driver, "screenShotName");
            test.log(LogStatus.FAIL, result.getThrowable());
            logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
            logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
            //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method.
            //String screenshotPath = getScreenShot(driver, result.getName(), element);
            //To add it in the extent report
            //logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
        }else if(result.getStatus() == ITestResult.SKIP){
            logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
        }
            //test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));

        extent.endTest(test);
        extent.flush();
    }

    @AfterTest(groups = { "Sanity" })
    public void afterTest() throws Exception {
        System.out.println(" in After Test");
    }

    @AfterSuite(groups = { "Sanity" })
    public void afterSuite()
    {
        //System.out.println("in afterSuite");
        extent.close();  // close the Test Suite
    }

}