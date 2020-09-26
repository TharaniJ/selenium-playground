
package tj.selenium.iFrame;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class IFrameExample {

    public static void main(String[] args) {

        Logger LOGGER = LoggerFactory.getLogger(IFrameExample.class);

        String configPropertyPath =  "data/configuration.properties";

        File configFile = new File(configPropertyPath);

        Properties configProperties = new Properties();

        FileReader configFileReader;

        WebDriver webDriver = null;

        try{

            configFileReader = new FileReader(configFile);

            configProperties.load(configFileReader);

            System.setProperty("webdriver.chrome.driver",configProperties.getProperty("selenium.driver.chrome"));

            webDriver = new ChromeDriver();

            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            webDriver.get(configProperties.getProperty("selenium.iframe.url"));

            webDriver.manage().window().maximize();

            int size = webDriver.findElements(By.tagName("iframe")).size();
            System.out.println("Total Frames : " + size);

            webDriver.switchTo().frame(webDriver.findElement(By.id("frame1")));
            LOGGER.info("Navigated to first iframe");
            webDriver.switchTo().frame(webDriver.findElement(By.id("frame3")));
            LOGGER.info("Navigated to second iframe");
            WebElement checkBoxSelect = webDriver.findElement(By.id("a"));
            checkBoxSelect.click();
            LOGGER.info("Selected check box");

            Thread.sleep(2000);

            webDriver.switchTo().defaultContent();
            LOGGER.info("Navigated back to main page");

            webDriver.switchTo().frame(webDriver.findElement(By.id("frame2")));
            LOGGER.info("Navigated to Third iframe");

            Select dropDownSelect = new Select(webDriver.findElement(By.id("animals")));
            dropDownSelect.selectByVisibleText("Avatar");
            LOGGER.info("Selected Avatar from drop down");

            Thread.sleep(2000);


        } catch (Exception e) {

            LOGGER.info("Error occurred : " + e.getMessage());

            e.printStackTrace();

        } finally {

            webDriver.close();

            webDriver.quit();

        }
    }
}
