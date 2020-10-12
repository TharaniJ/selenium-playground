package tj.selenium.myntra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Set;

public class ValidateImageClick {
    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(ValidateImageClick.class);

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
    public void validateMouseOverMovementClick() {
        try {

            //Navigate to the moodle site
            webDriver.manage().deleteAllCookies();

            webDriver.get(configProperties.getProperty("selenium.micro.assignment.validateMouseMovement"));

            webDriver.manage().window().maximize();

            WebDriverWait waitForOffersButton = new WebDriverWait(webDriver, 10);
            WebElement offerButton = waitForOffersButton
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath("//*[@id='desktop-header-cnt']/div[2]/nav/div/div[5]/div[@class='desktop-navLink']")));

            Actions mouseMoveOverAction = new Actions(webDriver);
            mouseMoveOverAction.moveToElement(offerButton).perform();


            WebDriverWait waitForSelectMenu = new WebDriverWait(webDriver, 10);
            WebElement selectMenu = waitForSelectMenu
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath("//*[@id='desktop-header-cnt']/div[2]/nav/div/div[5]/div/div/div/div/li[1]/ul/li[1]/a[@class='desktop-categoryName']")));
            selectMenu.click();

            WebDriverWait waitSortBy = new WebDriverWait(webDriver, 10);
            WebElement sortBy = waitSortBy
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath("//*[@id='desktopSearchResults']/div[1]/section/div[1]/div[@class='horizontal-filters-sortContainer']")));

            mouseMoveOverAction.moveToElement(sortBy).perform();

            WebDriverWait waitForImageVisible = new WebDriverWait(webDriver, 10);
            WebElement imageClick = waitForImageVisible
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath("//*[@id='desktopSearchResults']/div[2]/section/ul/li[1]/a/div[1]/div/div/div/picture/img")));
            imageClick.click();

            Thread.sleep(3000);

//            // Get the handle of the first window (i.e. the very first window the web driver created)
//            String currentWindowHandle = webDriver.getWindowHandle();
//            LOGGER.info("First (Old) Window Handle : " + currentWindowHandle);
//
//            // Gets all the window handles
//            Set<String> windowHandles = webDriver.getWindowHandles();
//
//            /*
//             * Following logic is to identify the new window handle. Since we already captured the old one, go through
//             * all the window handles (in this case, there are two handles) and find the new one.
//             *
//             * This is done, since the WebDriver uses the window handles to refer to each of the windows.
//             */
//            String newWindowHandle = null;
//            for (String windowHandle : windowHandles) {
//                if (!currentWindowHandle.equalsIgnoreCase(windowHandle)) {
//                    newWindowHandle = windowHandle;
//                    break;
//                }
//            }
//
//            LOGGER.info("New Window Handle : " + newWindowHandle);
//            // Now that we found the window handle, we use that to switch to the new window handle
//            webDriver.switchTo().window(newWindowHandle);



//            WebDriverWait waitForDisplayAmount = new WebDriverWait(webDriver,10);
//            WebElement displayMessage = waitForDisplayAmount.until(ExpectedConditions
//                    .visibilityOfElementLocated(By.xpath("//strong[contains(text(),'Rs. 874')]")));
//
//            String expectedResult = configProperties.getProperty("selenium.micro.myntra.expectedResult");
//            String actualResult = displayMessage.getText();
//            LOGGER.info("Actual : " + actualResult + " Expected : " + expectedResult);
//            Assert.assertEquals(actualResult, expectedResult);

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
