package tj.selenium.webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tj.selenium.waits.ExplicitWait;

public class AlertExample {
    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(AlertExample.class);

        System.setProperty("webdriver.chrome.driver","C:/EDU/Software/chromedriver_win32/chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        try{
            webDriver.get("http://demo.guru99.com/test/delete_customer.php");
            webDriver.manage().window().maximize();

            // Find the search box
            WebElement customerIdTextBox = webDriver.findElement(By.name("cusid"));
            Thread.sleep(2000);
            customerIdTextBox.sendKeys("123");
            LOGGER.info("Sent customer id");
            Thread.sleep(3000);

            WebElement submitButton = webDriver.findElement(By.name("submit"));
            submitButton.click();
            LOGGER.info("Click submit button");

            // Explicitly waiting for 10 seconds until the Alert appears.
            WebDriverWait waitForAlert = new WebDriverWait(webDriver, 10);
            waitForAlert.until(ExpectedConditions.alertIsPresent());
            LOGGER.info("Alert present");

            Alert confirmationAlert = webDriver.switchTo().alert();

            Thread.sleep(3000); // To see the alert properly. No need otherwise
            confirmationAlert.accept();
            LOGGER.info("Accepted the alert");

            // Here we are re-using the waitForAlert object (which is of the type WebDriverWait)
            waitForAlert.until(ExpectedConditions.alertIsPresent());
            LOGGER.info("Waiting for second alert");
            Alert infoAlert = webDriver.switchTo().alert();

            Thread.sleep(3000); // To see the alert properly. No need otherwise
            infoAlert.accept();
            LOGGER.info("Accepted the second alert");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            webDriver.close();
            webDriver.quit();
        }

    }


}
