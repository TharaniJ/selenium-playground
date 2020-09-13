package tj.selenium.alertsandactionclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DragAndDropExample {
    WebDriver webDriver;
    //Initiate the loggers
    Logger LOGGERS = LoggerFactory.getLogger(DragAndDropExample.class);

    @BeforeClass
    public void InitiateBrowser(){

        //Setting the driver path
        System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");

        //Instantiating the webdriver
        webDriver = new ChromeDriver();

        //Initiate the implicit wait
        webDriver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);

        //Deleted all the browser cookies and Started a Fresh browser
        webDriver.manage().deleteAllCookies();
    }

    @Test
    public void DragAndDrop(){
        try {
            // Commands Chrome browser to load a demo site with drag-and-drop feature
            webDriver.get("http://demo.guru99.com/test/drag_drop.html");

            // Maximizes the browser
            webDriver.manage().window().maximize();
            LOGGERS.info("Browser opened and maximized");

            // Find the element which need to be drag
            WebElement sourceElement = webDriver.findElement(By.xpath("//div[@id='products']//li[@id='fourth'][1]"));

            // Find the element where is to be drop
            WebElement targetElement = webDriver.findElement(By.id("amt7"));

            //initiate the explicit wait
            WebDriverWait waitForVisibility = new WebDriverWait(webDriver, 10);

            // Waiting or visibility of elements
            waitForVisibility.until(ExpectedConditions.visibilityOf(sourceElement));
            waitForVisibility.until(ExpectedConditions.visibilityOf(targetElement));

            // Use Actions class to mimic Mouse Move and Hover behavior
            Actions dragAndDropAction = new Actions(webDriver);
            dragAndDropAction.dragAndDrop(sourceElement, targetElement).perform();

            Thread.sleep(3000);
            LOGGERS.info("Drag and Drop complete.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterTest
    public void CloseBrowser(){
        webDriver.close();
        webDriver.quit();

    }
}
