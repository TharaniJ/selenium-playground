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
import org.testng.annotations.Test;
import tj.selenium.alertsandchildwindows.HandleChildWindow;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;

public class MicroAssignment2 {
    //Initiating logger
    private Logger LOGGER = LoggerFactory.getLogger(MicroAssignment2.class);

    //Defining web driver
    private WebDriver webDriver = null;

    //Defining file Reader
    private FileReader configFileReader;

    //Defining config file
    private File configFile;

    //Defining the properties
    private Properties configProperties;

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
    public void validateNoOfRowsInTheTable(){
        try {

            //Navigate to the moodle site
            webDriver.get(configProperties.getProperty("selenium.micro.assignment1"));

            webDriver.manage().window().maximize();

            WebDriverWait waitForNoThanksBtn = new WebDriverWait(webDriver,10);
            WebElement noThanksBtn = waitForNoThanksBtn.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id='at-cv-lightbox-button-holder']/a[@href='#']")));
            noThanksBtn.click();

            WebDriverWait waitForTableLink = new WebDriverWait(webDriver,10);
            WebElement tableLink = waitForTableLink.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id=\"treemenu\"]/li/ul/li[3]/a")));
            tableLink.click();

            WebElement tablePaginationLink = webDriver.findElement(By.linkText("Table Pagination"));
            tablePaginationLink.click();

            WebElement table = webDriver.findElement(By.id("myTable"));

            List<WebElement> trList = table.findElements(By.tagName("tr"));
            int index = 0;
            for (WebElement tr : trList) {
                index++;
            }
            LOGGER.info("Count of the index" + index);

            WebDriverWait waitLastTable = new WebDriverWait(webDriver,10);
            WebElement lastTable = waitLastTable.until(ExpectedConditions
                    .elementToBeClickable(By.linkText("3")));
            lastTable.click();

            WebElement lastRowNo = webDriver.findElement(By.xpath("//*[@id=\"myTable\"]/tr[13]/td[1]"));


            String expectedResult = Integer.toString(index);;
            String actualResult = lastRowNo.getText().trim();
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

