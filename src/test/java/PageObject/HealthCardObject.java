package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HealthCardObject {
    WebDriver driver;
    @FindBy(how = How.XPATH, using = "//p[@class='sub-heading selected-color']")
    private WebElement risingHealthCost;
    @FindBy(how = How.XPATH, using = "//button[@class='nextBtn']")
    private WebElement nextButton;

    public HealthCardObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //public void selectRising


}
