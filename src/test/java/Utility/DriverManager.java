package Utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManager {
    public static WebDriver driver;
    public static WebDriver getDriver(String browser){
        if(browser.equals("chrome")){
            System.setProperty("Webdriver.chrome.driver", "./chromedriver");
            //WebDriverManager.chromedriver().setup();
            //WebDriverManager.chromedriver().driverVersion("97.0.4692.99").setup();
            driver = new ChromeDriver();
            return driver;
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            return driver;
        }else {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            return driver;
        }

    }
}
