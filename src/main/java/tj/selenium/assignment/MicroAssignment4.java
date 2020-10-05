package tj.selenium.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class MicroAssignment4 {
    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(MicroAssignment4.class);

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
    public void handleAlert() {
        try {

            //Navigate to the moodle site
            webDriver.get(configProperties.getProperty("selenium.micro.assignment1"));

            webDriver.manage().window().maximize();

            //Wait to click on NoThanks button from wondow
            WebDriverWait waitForNoThanksBtn = new WebDriverWait(webDriver,10);
            WebElement noThanksBtn = waitForNoThanksBtn.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id='at-cv-lightbox-button-holder']/a")));
            noThanksBtn.click();

            /*
            WebDriverWait waitForAlertsAndModels = new WebDriverWait(webDriver, 10);
            WebElement alertsAndModelsLink = waitForAlertsAndModels.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id=\"treemenu\"]/li/ul/li[5]/a")));
            alertsAndModelsLink.click();

            WebElement bootStrapAlertLink = webDriver.findElement(By.xpath("//*[@id='treemenu']/li/ul/li[5]/ul/li[1]/a"));
            bootStrapAlertLink.click();
*/

            WebDriverWait waitForEntitiesDropDown = new WebDriverWait(webDriver, 10);
            WebElement dropDown = waitForEntitiesDropDown.until(ExpectedConditions
                    .presenceOfElementLocated(By.className("dropdown-toggle")));
            Select entitiesDropDown = new Select(dropDown);
            entitiesDropDown.selectByVisibleText("Bootstrap Modals");


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
