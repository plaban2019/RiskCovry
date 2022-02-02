package PageObject;

import Utility.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AgeSelectObject extends ExtentReport {
    WebDriver driver;
    @FindBy(how = How.XPATH, using = "//div[@id='0_border']//span[@class='age-div mat-menu-trigger ng-star-inserted']")
    private WebElement selfAge;
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Enter age']")
    private WebElement enterAge;
    @FindBy(how = How.XPATH, using = "//*[text()='Maximum limit for adult's age is 99']")
    private WebElement errorMessage;

    public AgeSelectObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void selectSelfAge() throws Exception {
        if (true){
            Assert.assertEquals(selfAge.isEnabled(), true);
            selfAge.click();
            test.log(LogStatus.PASS, "age is selected");
        }else {
            test.log(LogStatus.FAIL, "age is not selectable");
            test.log(LogStatus.FAIL, test.addScreenCapture(getFailScreenshot(driver, "selfAge")));
        }
    }
    public void validateAge(String age) throws Exception {
        if (true){
            enterAge.sendKeys(age);
            Thread.sleep(1000);
            if (enterAge.getText().equals("")){
                test.log(LogStatus.ERROR, "“Invalid age “ has been entered.");
                System.out.println("“Invalid age “ has been entered.");
            }else {
                test.log(LogStatus.PASS, "test pass");
            }
        }else {
            test.log(LogStatus.FAIL, "enter age field is not present");
            test.log(LogStatus.FAIL, test.addScreenCapture(getFailScreenshot(driver, "enterAge")));
        }
    }

}
