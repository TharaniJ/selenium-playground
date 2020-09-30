package tj.selenium.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MicroAssignment3 {
    Logger LOGGER = LoggerFactory.getLogger(MicroAssignment3.class);

    WebDriver webDriver = null;

    //Defining file Reader
    FileReader configFileReader;

    //Defining config file
    File configFile;

    //Defining the properties
    Properties configProperties;


    @BeforeClass
    @Parameters("browser")
    public void setupConfiguration(@Optional("na") String browser) {
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

            if(browser.equalsIgnoreCase("chrome")) {
                //set the web driver
                System.setProperty("webdriver.chrome.driver", configProperties.getProperty("selenium.driver.chrome"));
                //initiate the web driver
                webDriver = new ChromeDriver();

            } else if(browser.equalsIgnoreCase("edge")) {
                //set the web driver
                System.setProperty("webdriver.edge.driver", configProperties.getProperty("selenium.driver.edge"));
                //initiate the web driver
                webDriver = new EdgeDriver();
            }


        } catch (Exception e) {

            LOGGER.info("Error occurred in closing the file: " + e.getMessage());

        } finally {

            try {

                configFileReader.close();

            } catch (Exception e) {

                LOGGER.info("Error occurred in closing the file: " + e.getMessage());
            }

        }
    }

    @Test
    public void validateTheRecordCanBeFindByFilters() {
        try {

            //Navigate to the site
            webDriver.get(configProperties.getProperty("selenium.micro.assignment1"));

            webDriver.manage().window().maximize();

            WebDriverWait waitForNoThanksBtn = new WebDriverWait(webDriver, 10);
            WebElement noThanksBtn = waitForNoThanksBtn.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id='at-cv-lightbox-button-holder']/a[@href='#']")));
            noThanksBtn.click();

            WebDriverWait waitForTableLink = new WebDriverWait(webDriver, 10);
            WebElement tableLink = waitForTableLink.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id='treemenu']/li/ul/li[3]/a")));
            tableLink.click();

            WebElement sortAndSearchLink = webDriver.findElement(By.xpath("//*[@id='treemenu']/li/ul/li[3]/ul/li[4]/a"));
            sortAndSearchLink.click();

            WebDriverWait waitForEntitiesDropDown = new WebDriverWait(webDriver, 10);
            WebElement dropDown = waitForEntitiesDropDown.until(ExpectedConditions
                    .presenceOfElementLocated(By.name("example_length")));
            Select entitiesDropDown = new Select(dropDown);
            entitiesDropDown.selectByVisibleText("25");

            WebElement searchBox = webDriver.findElement(By.xpath("//*[@id='example_filter']/label/input[@type='search']"));
            searchBox.sendKeys("Accountant");


        }catch (Exception e) {

            LOGGER.info("Error occurred : " + e.getMessage());
            Assert.fail("Exception Occured" + e.getMessage(),e);
        }

    }

    @AfterMethod
    public void closeWebDriver() {

        webDriver.close();
        webDriver.quit();

        LOGGER.info("Browser close successfully!");
    }

}
