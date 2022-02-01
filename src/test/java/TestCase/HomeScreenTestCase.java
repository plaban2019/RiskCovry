package TestCase;

import PageObject.HomeScreenObject;
import Utility.ConfigFileReader;
import Utility.DriverManager;
import Utility.ExtentReport;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class HomeScreenTestCase extends ExtentReport {
    ConfigFileReader configFileReader;

    @Parameters({"Browser"})
    @BeforeSuite
    public void beforeTest(@Optional("chrome") String browser){
        configFileReader = new ConfigFileReader();
        driver = DriverManager.getDriver(browser);
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(configFileReader.getApplicationUrl());
        Assert.assertEquals(driver.getCurrentUrl(), configFileReader.getApplicationUrl());
    }

    @Test(description = "Go to home page and click on get protected")
    public void homePageTest() throws Exception {
        HomeScreenObject riskCovryHome = new HomeScreenObject(driver);
        riskCovryHome.waitForGetProtectedToBeVisible();
        riskCovryHome.clickOnGetProtected();
    }

    @AfterTest
    public void afterTest(){
        driver.close();
        driver.quit();
    }

}
