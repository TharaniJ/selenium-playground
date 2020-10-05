package tj.selenium.scrollupanddown;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ScrollByPage {

    Logger LOGGER = LoggerFactory.getLogger(ScrollByPage.class);
    WebDriver driver;

    @Test
    public void ByPage() {

        try {

            System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");

            driver = new ChromeDriver();

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Launch the application
            driver.get("http://demo.guru99.com/test/guru99home/");

            driver.manage().window().maximize();

            Thread.sleep(3000);

            //This will scroll the web page till end.
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

            Thread.sleep(3000);

        } catch (Exception e) {

            LOGGER.info("Error occurred in closing the file: " + e.getMessage());

        } finally {

            driver.close();
            driver.quit();
        }

    }
}