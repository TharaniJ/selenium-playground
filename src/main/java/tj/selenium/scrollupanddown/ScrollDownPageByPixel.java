package tj.selenium.scrollupanddown;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class ScrollDownPageByPixel {
    WebDriver driver;
    Logger LOGGER = LoggerFactory.getLogger(ScrollDownPageByPixel.class);

    @Test
    public void ByPixel() {
        try{
            System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");

            driver = new ChromeDriver();

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Launch the application
            driver.get("http://demo.guru99.com/test/guru99home/");

            //To maximize the window. This code may not work with Selenium 3 jars.
            // If script fails you can remove the line below
            driver.manage().window().maximize();

            Thread.sleep(3000);

            // This  will scroll down the page by  2000 pixel vertical
            js.executeScript("window.scrollBy(0,2000)");

            Thread.sleep(3000);

        }catch (Exception e){
            LOGGER.info("Error occurred in closing the file: " + e.getMessage());
        }finally {
            driver.close();
            driver.quit();
        }

    }
}
