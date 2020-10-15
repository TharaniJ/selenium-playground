package tj.selenium.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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

public class DragAndDrop {
    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(DragAndDrop.class);

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
    public void validateDragAndDrop() {
        try {

            //Navigate to the moodle site
            webDriver.manage().deleteAllCookies();

            webDriver.get(configProperties.getProperty("selenium.moodle.url.indexOfPractice"));

            webDriver.manage().window().maximize();

            WebDriverWait waitForDragAndDropLink = new WebDriverWait(webDriver, 10);
            WebElement dragAndDrop = waitForDragAndDropLink.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("/html/body/pre/a[11]")));
            dragAndDrop.click();

            // Find the element which need to be drag
            WebElement sourceElement = webDriver.findElement(By.id("box3"));
            LOGGER.info("Identified the source element");

            // Find the element where is to be drop
            WebElement targetElement = webDriver.findElement(By.id("destination"));
            LOGGER.info("Identified the targeted element");

            //initiate the explicit wait
            WebDriverWait waitForVisibility = new WebDriverWait(webDriver, 10);

            // Waiting or visibility of elements
            waitForVisibility.until(ExpectedConditions.visibilityOf(sourceElement));
            waitForVisibility.until(ExpectedConditions.visibilityOf(targetElement));

            Thread.sleep(3000);// To see properly. otherwise not required
            // Use Actions class to mimic Mouse Move and Hover behavior
            Actions dragAndDropAction = new Actions(webDriver);
            dragAndDropAction.dragAndDrop(sourceElement, targetElement).perform();

            Thread.sleep(3000);// To see properly. otherwise not required
            LOGGER.info("Drag and Drop complete.");

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
