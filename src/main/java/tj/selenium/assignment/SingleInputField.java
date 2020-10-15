/**
 * Micro-Test-1
 *
 * Steps:
 * Load this URL - https://www.seleniumeasy.com/test/basic-first-form-demo.html
 * Under “Single Input Field” form, enter the text “Test” in the text box
 * Click “Show Message”
 * Check whether it updates “Your Message:” to “Your Message: Test”
 *
 * Technical Requirements:
 * Must be implemented in a TestNG test case
 * A test configuration should be present
 */


package tj.selenium.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tj.selenium.alertsandchildwindows.HandleChildWindow;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class SingleInputField {
    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(HandleChildWindow.class);

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
    public void validateInput(){
        try {

            //Navigate to the moodle site
            webDriver.get(configProperties.getProperty("selenium.micro.assignment1"));

            webDriver.manage().window().maximize();

            WebDriverWait waitForNoThanksBtn = new WebDriverWait(webDriver,10);
            WebElement noThanksBtn = waitForNoThanksBtn.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id='at-cv-lightbox-button-holder']/a[@href='#']")));
            noThanksBtn.click();

            WebDriverWait waitForTextBox = new WebDriverWait(webDriver,10);
            WebElement textBox = waitForTextBox.until(ExpectedConditions
                    .elementToBeClickable(By.id("user-message")));
            textBox.sendKeys(configProperties.getProperty("selenium.micro.assignment1.expectedResult"));

            WebElement messageButton = webDriver.findElement(By.xpath("//*[@id='get-input']/button"));
            messageButton.click();

            WebDriverWait waitForDisplayMessage = new WebDriverWait(webDriver,10);
            WebElement displayMessage = waitForDisplayMessage.until(ExpectedConditions
                    .visibilityOfElementLocated(By.id("display")));

            String expectedResult = configProperties.getProperty("selenium.micro.assignment1.expectedResult");
            String actualResult = displayMessage.getText().trim();
            LOGGER.info("Actual : " + actualResult + " Expected : " + expectedResult);
            Assert.assertEquals(actualResult, expectedResult);

        }catch (Exception e) {

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
