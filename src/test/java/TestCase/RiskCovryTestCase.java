package TestCase;

import PageObject.AgeSelectObject;
import PageObject.HealthCardObject;
import PageObject.HomeScreenObject;
import Utility.ConfigFileReader;
import Utility.DriverManager;
import Utility.ExtentReport;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class RiskCovryTestCase extends ExtentReport {
    ConfigFileReader configFileReader;

    @Parameters({"Browser"})
    @BeforeTest
    public void beforeTest(String browser){
        configFileReader = new ConfigFileReader();
        driver = DriverManager.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(configFileReader.getApplicationUrl());
        driver.manage().window().fullscreen();
        Assert.assertEquals(driver.getCurrentUrl(), configFileReader.getApplicationUrl());
    }

    @Test(description = "home page Functionality", priority = 0)
    public void homePageTest() throws Exception {
        HomeScreenObject riskCovryHome = new HomeScreenObject(driver);
        riskCovryHome.waitForGetProtectedToBeVisible();
        riskCovryHome.clickOnGetProtected();
    }

    @Test(description = "Card Page Functionality", priority = 1)
    public void healthCard() throws Exception {
        HealthCardObject riskCovryCard = new HealthCardObject(driver);
        riskCovryCard.waitForRisingToBeVisible();
        riskCovryCard.selectRisingHealthCost();
        riskCovryCard.clickNextButton();
    }

    @Test(description = "Age Selection Page Functionality", priority = 2)
    public void ageSelect() throws Exception {
        AgeSelectObject riskCovryAge = new AgeSelectObject(driver);
        riskCovryAge.selectSelfAge();
        Thread.sleep(3000);
        riskCovryAge.validateAge("200");
    }

    @AfterTest
    public void afterTest(){
        driver.close();
        driver.quit();
    }

}
