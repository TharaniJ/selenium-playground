package tj.selenium.myntra;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class ValidateScrollDownAndClickSiteMapLink {
    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(ValidateScrollDownAndClickSiteMapLink.class);

    //Defining web driver
    WebDriver webDriver = null;

    //Defining file Reader
    FileReader configFileReader;

    //Defining config file
    File configFile;

    //Defining the properties
    Properties configProperties;

    @BeforeClass
    public void configSetup() {
        try {

            //Assigning to the filepath to a variable
            String configPropertiesFilePath = "data/configuration.properties";

            //Initiate new file using file path variable
            configFile = new File(configPropertiesFilePath);

            //Initiate new property
            configProperties = new Properties();

            //Initiate new file reader
            configFileReader = new FileReader(configFile);

            //use file reader to load the property
            configProperties.load(configFileReader);

            //set the web driver
            System.setProperty("webdriver.chrome.driver", configProperties.getProperty("selenium.driver.chrome"));

            //initiate the web driver
            webDriver = new ChromeDriver();

        }catch (Exception e) {

            LOGGER.info("Error occurred in closing the file: " + e.getMessage());

        }finally {
            try {

                configFileReader.close();

            } catch (Exception e) {
                LOGGER.info("Error occurred in closing the file: " + e.getMessage());
            }
        }
    }

    @Test
    public void validateScrollDownAndClickLink() {
        try {

            //Navigate to the moodle site
            webDriver.manage().deleteAllCookies();

            webDriver.get(configProperties.getProperty("selenium.micro.assignment.validateMouseMovement"));

            webDriver.manage().window().maximize();

            JavascriptExecutor js = (JavascriptExecutor) webDriver;

            WebDriverWait waitForSiteMapLink = new WebDriverWait(webDriver, 10);
            WebElement siteMapLink = waitForSiteMapLink
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath("//*[@id='web-footerMount']/div/footer/div/div[1]/div[2]/a[13]")));
            LOGGER.info("Located the element");

            js.executeScript("arguments[0].scrollIntoView();", siteMapLink);

            LOGGER.info("Scrolled down");

            siteMapLink.click();
            LOGGER.info("Clicked Successfully");

            Thread.sleep(3000);



        } catch (Exception e) {

            LOGGER.info("Error occurred : " + e.getMessage());

        }
    }

    @AfterMethod
    public void closeWebDriver() {

        webDriver.close();

        webDriver.quit();

        LOGGER.info("Browser close successfully!");
    }

}
