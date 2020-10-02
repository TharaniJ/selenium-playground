package tj.selenium.screenshotcapture;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class CaptureScreenshot {
    @Test
    public static void captureScreenMethod() throws Exception{
        Logger LOGGER = LoggerFactory.getLogger(CaptureScreenshot.class);
        System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try{
            driver.get("https://www.softwaretestingmaterial.com");
            driver.navigate().refresh();
            //driver.findElement(By.xpath("//*[@id='cse-search-box']/div/input[4]")).sendKeys("agile"); //Statement with correct Xpath
            driver.findElement(By.xpath("//*[@id='cse']")).sendKeys("agile"); //Statement with incorrect Xpath


        }catch(Exception e){
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("D:\\SoftwareTestingMaterial.png"));
            LOGGER.info("Error occurred in closing the file: " + e.getMessage());
        }
        driver.close();
        driver.quit();
        LOGGER.info("Browser close successfully!");
    }
}