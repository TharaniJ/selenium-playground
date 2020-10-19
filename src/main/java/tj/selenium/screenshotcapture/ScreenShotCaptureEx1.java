package tj.selenium.screenshotcapture;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;


public class ScreenShotCaptureEx1 {

    Logger LOGGER = LoggerFactory.getLogger(ScreenShotCaptureEx1.class);

    WebDriver webDriver = null;

    @Test
    public void GetScreenshot() {
        try {
            System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");

            webDriver = new ChromeDriver();

            //Navigate to URL

            webDriver.get("http://demo.guru99.com/V4/");

            //Call take screenshot function

            this.takeSnapShot(webDriver, "c://test.png");

        } catch (Exception e) {

            LOGGER.info("Error occurred in closing the file: " + e.getMessage());

        }
    }

        @AfterMethod
        public void closeWebDriver() {

            webDriver.close();

            webDriver.quit();

            LOGGER.info("Browser close successfully!");
        }

    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        File DestFile = new File(fileWithPath);

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);

    }

}

