package tj.selenium.assignment;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class CrossBrowserTest {

    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(CrossBrowserTest.class);

    //Defining web driver
    WebDriver webDriver = null;

    //Defining file Reader
    FileReader configFileReader;

    //Defining config file
    File configFile;

    //Defining the properties
    Properties configProperties;

    @BeforeClass
    @Parameters("browser")
    public void configSetup(String browser) {
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

            if (browser.equalsIgnoreCase("chrome")) {
                //set the web driver
                System.setProperty("webdriver.chrome.driver", configProperties.getProperty("selenium.driver.chrome"));
                //initiate the web driver
                webDriver = new ChromeDriver();

            } else if (browser.equalsIgnoreCase("edge")) {
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

    @Test(priority = 2)
    public  void HelloMessage(){
        LOGGER.info("Hello Everyone ! how is going life? ");
    }

    @Test(priority = 1)
    public void validateInput() {
        try {

            //Navigate to the site
            webDriver.get(configProperties.getProperty("selenium.micro.assignment1"));

            webDriver.manage().window().maximize();

            WebDriverWait waitForNoThanksBtn = new WebDriverWait(webDriver, 10);
            WebElement noThanksBtn = waitForNoThanksBtn.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//*[@id='at-cv-lightbox-button-holder']/a[@href='#']")));
            noThanksBtn.click();

            LOGGER.info("Clicked no thanks button");

            WebDriverWait waitForTextBox = new WebDriverWait(webDriver, 10);
            WebElement textBox = waitForTextBox.until(ExpectedConditions
                    .elementToBeClickable(By.id("user-message")));
            textBox.sendKeys(configProperties.getProperty("selenium.micro.assignment1.expectedResult"));

            LOGGER.info("Typed good morning message");

            WebElement messageButton = webDriver.findElement(By.xpath("//*[@id='get-input']/button"));
            messageButton.click();

            LOGGER.info("Clicked on Submit button");

        } catch (Exception e) {

            LOGGER.info("Error occurred : " + e.getMessage());

        }

    }

    @Test(priority = 3)
    public void validateResult() {
        try {

            WebDriverWait waitForDisplayMessage = new WebDriverWait(webDriver, 10);
            WebElement displayMessage = waitForDisplayMessage.until(ExpectedConditions
                    .visibilityOfElementLocated(By.id("display")));

            LOGGER.info("Validate the display message");

            String expectedResult = configProperties.getProperty("selenium.micro.assignment1.expectedResult");
            String actualResult = displayMessage.getText().trim();
            LOGGER.info("Actual : " + actualResult + " Expected : " + expectedResult);
            Assert.assertEquals(actualResult, expectedResult);


        }catch (Exception e){
            LOGGER.info("Error occurred : " + e.getMessage());
        }
    }



    @AfterClass
    public void closeWebDriver() {

        webDriver.close();

        webDriver.quit();

        LOGGER.info("Browser close successfully!");
    }
}
