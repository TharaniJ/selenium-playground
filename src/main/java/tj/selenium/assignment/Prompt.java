package tj.selenium.assignment;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

public class Prompt {
    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(Prompt.class);

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
    public void validatePrompt() {
        try {

            //Navigate to the moodle site
            webDriver.manage().deleteAllCookies();

            webDriver.get(configProperties.getProperty("selenium.moodle.url.indexOfPractice"));

            webDriver.manage().window().maximize();

            WebDriverWait waitForPopupLink = new WebDriverWait(webDriver, 10);
            WebElement popUpLink = waitForPopupLink.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("/html/body/pre/a[24]")));
            popUpLink.click();

            WebElement confirmationButton = webDriver.findElement(By.name("prompt"));
            confirmationButton.click();
            LOGGER.info("Click prompt button");

            // Explicitly waiting for 10 seconds until the Alert appears.
            WebDriverWait waitForPromptAlert = new WebDriverWait(webDriver, 10);
            waitForPromptAlert.until(ExpectedConditions.alertIsPresent());
            LOGGER.info("Prompt Alert present");

            Alert promptAlert = webDriver.switchTo().alert();
            String alertText = promptAlert.getText();
            System.out.println("Alert text is " + alertText);
            LOGGER.info("Get and Printed the Prompt text!");

            Thread.sleep(3000); // To see the alert properly. Otherwise not required

            promptAlert.sendKeys("Test User");
            LOGGER.info("Send the text to the prompt!");

            promptAlert.accept();
            LOGGER.info("accept the alert!");

            Thread.sleep(3000);// To see the alert properly. Otherwise not required

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
