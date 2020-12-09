package tj.selenium.eyepaxexam.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tj.selenium.eyepaxexam.pages.*;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Set;

public class VerifyRegisteredUserCheckoutProduct {
    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(VerifyCreateAccount.class);

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
            String configPropertiesFilePath = "data/config.properties";

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
    public void verifyCheckout() {
        try {

            //Navigate to the moodle site
            webDriver.manage().deleteAllCookies();

            webDriver.get(configProperties.getProperty("selenium.website.url"));
            LOGGER.info("navigate to webpage");

            webDriver.manage().window().maximize();

            SignInPage signInPage = PageFactory.initElements(webDriver, SignInPage.class);
            WebDriverWait waitForSignInPage = new WebDriverWait(webDriver, 10);
            waitForSignInPage.until(ExpectedConditions.elementToBeClickable(signInPage.getSignIn()));
            signInPage.getSignIn().click();
            LOGGER.info("Clicked on SignIn button");

            signInPage.getEmailAddress().sendKeys(configProperties.getProperty("website.sigIn.email"));
            LOGGER.info("Entered email Address");

            signInPage.getPassword().sendKeys(configProperties.getProperty("website.sigIn.pwd"));
            LOGGER.info("Entered pwd");

            signInPage.getLogIn().click();

            LOGGER.info("Clicked LoginButton");

            MyAccountPage myAccountPage = PageFactory.initElements(webDriver, MyAccountPage.class);
            WebDriverWait waitForMyAccount = new WebDriverWait(webDriver,10);
            waitForMyAccount.until(ExpectedConditions.elementToBeClickable(myAccountPage.getMyWishList()));
            myAccountPage.getMyWishList().click();
            LOGGER.info("Clicked get my wish list button");

            MyWishListPage myWishListPage = PageFactory.initElements(webDriver, MyWishListPage.class);
            WebDriverWait waitForMyWishList = new WebDriverWait(webDriver,10);
            waitForMyWishList.until(ExpectedConditions.elementToBeClickable(myWishListPage.getMyWishList()));
            myWishListPage.getMyWishList().click();
            LOGGER.info("Clicked the product");

            myWishListPage.getAddToCard().click();
            LOGGER.info("Clicked on add to card");

            WebDriverWait waitForProceedButton = new WebDriverWait(webDriver,10);
            waitForProceedButton.until(ExpectedConditions.elementToBeClickable(myWishListPage.getProceedToCheckOut()));
            myWishListPage.getProceedToCheckOut().click();

            WebDriverWait waitForCardTitle = new WebDriverWait(webDriver,10);
            WebElement cardTitle = waitForCardTitle.until(ExpectedConditions
                    .visibilityOf(myWishListPage.getCardTitle()));

            String expectedResult = configProperties.getProperty("summary.cardTitle");
            String actualResult = cardTitle.getText();
            LOGGER.info("Actual : " + actualResult + " Expected : " + expectedResult);
            Assert.assertEquals(actualResult, expectedResult);

            Thread.sleep(4000);

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
