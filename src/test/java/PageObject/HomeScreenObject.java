package PageObject;

import Utility.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomeScreenObject extends ExtentReport {
    WebDriver driver;

    @FindBy(how = How.XPATH, using = "//a[@class='get-protected']")
    private WebElement getProtected;

    public HomeScreenObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void waitForGetProtectedToBeVisible(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(getProtected));
    }
    public void clickOnGetProtected() throws Exception {
        if (getProtected.isDisplayed()){
            Assert.assertEquals(getProtected.isEnabled(), true);
            getProtected.click();
            test.log(LogStatus.PASS, "get protected is clicked");

        }else{
            test.log(LogStatus.FAIL, "get protected is not clicked");
            test.log(LogStatus.FAIL, test.addScreenCapture(getFailScreenshot(driver, "getProtected")));
        }

    }



}
