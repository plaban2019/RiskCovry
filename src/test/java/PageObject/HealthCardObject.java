package PageObject;

import Utility.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class HealthCardObject extends ExtentReport {
    WebDriver driver;
    @FindBy(how = How.XPATH, using = "//div[@class='cardHeight']//p[text()='Rising Health Costs']")
    private WebElement risingHealthCost;
    @FindBy(how = How.XPATH, using = "//div[@class='col-lg-12']//button[text()='NEXT']")
    private WebElement nextButton;

    public HealthCardObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void waitForButtonToBeClickable(){
        WebDriverWait wait = new WebDriverWait(driver, 80);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
    }
    public void waitForRisingToBeVisible(){
        WebDriverWait wait = new WebDriverWait(driver, 80);
        wait.until(ExpectedConditions.visibilityOf(risingHealthCost));
    }

    public void getAlert() throws Exception {
        if (true){
            Alert alert = driver.switchTo().alert();
            test.log(LogStatus.PASS, "Alert is present", alert.getText());
            alert.dismiss();
        }else {
            test.log(LogStatus.FAIL, "Alert is not Present");
            test.log(LogStatus.FAIL, test.addScreenCapture(getFailScreenshot(driver, "alertOne")));
        }
    }
    public void selectRisingHealthCost() throws Exception {
        if (driver.getCurrentUrl().equals("https://iifl-diy.demo.riskcovry.com/stepper/health-card")){
            Assert.assertEquals(risingHealthCost.isEnabled(), true);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click()", risingHealthCost);
            //risingHealthCost.click();
            test.log(LogStatus.PASS, "Rising Health Cost is clicked");
        }else {
            test.log(LogStatus.FAIL, "Rising Health Cost is not clicked");
            test.log(LogStatus.FAIL, test.addScreenCapture(getFailScreenshot(driver, "risingHealthCost")));
        }
    }
    public void clickNextButton() throws Exception {
        if (true){
            Assert.assertEquals(nextButton.isEnabled(), true);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click()", nextButton);
            test.log(LogStatus.PASS, "Next Button is clicked");
        }else {
            test.log(LogStatus.FAIL, "Next Button is not clicked");
            test.log(LogStatus.FAIL, test.addScreenCapture(getFailScreenshot(driver, "nextButton")));
        }
    }

}
