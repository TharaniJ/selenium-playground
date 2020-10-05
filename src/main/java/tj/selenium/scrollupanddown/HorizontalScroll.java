package tj.selenium.scrollupanddown;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class HorizontalScroll {

    Logger LOGGER = LoggerFactory.getLogger(HorizontalScroll.class);

    WebDriver driver;

    @Test
    public void ScrollHorizontally() {

        try {

            System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");

            driver = new ChromeDriver();

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Launch the application
            driver.get("http://demo.guru99.com/test/guru99home/scrolling.html");

            driver.manage().window().maximize();

            WebElement Element = driver.findElement(By.linkText("VBScript"));

            Thread.sleep(3000);

            //This will scroll the page Horizontally till the element is found
            js.executeScript("arguments[0].scrollIntoView();", Element);

            Thread.sleep(3000);

        }catch (Exception e) {

            LOGGER.info("Error occurred in closing the file: " + e.getMessage());

        }finally {

            driver.close();
            driver.quit();

        }
    }
}
