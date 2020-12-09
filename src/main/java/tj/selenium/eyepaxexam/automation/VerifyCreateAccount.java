package tj.selenium.eyepaxexam.automation;
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
import tj.selenium.eyepaxexam.pages.AuthenticationPage;
import tj.selenium.eyepaxexam.pages.MyAccountPage;
import tj.selenium.eyepaxexam.pages.RegistrationPage;
import tj.selenium.eyepaxexam.pages.SignInPage;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class VerifyCreateAccount {

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
    public void verifyCreateUserAccount() {
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

            AuthenticationPage authenticationPage = PageFactory.initElements(webDriver, AuthenticationPage.class);
            WebDriverWait waitForAuthenticationPage = new WebDriverWait(webDriver, 10);
            waitForAuthenticationPage.until(ExpectedConditions.elementToBeClickable(authenticationPage.getEmailTextBox()));
            authenticationPage.getEmailTextBox().sendKeys(configProperties.getProperty("selenium.website.createAnAccount.email"));
            LOGGER.info("Enter email address in the email text box to create an account");

            authenticationPage.getCreateAnAccount().click();
            LOGGER.info("Clicked on create an button");

            RegistrationPage registrationPage = PageFactory.initElements(webDriver, RegistrationPage.class);
            WebDriverWait waitForRegistrationPage = new WebDriverWait(webDriver, 10);
            waitForRegistrationPage.until(ExpectedConditions.elementToBeClickable(registrationPage.getCustomerFirstName()));
            registrationPage.getCustomerFirstName().sendKeys(configProperties.getProperty("website.createAnAccount.customerFirstName"));
            registrationPage.getCustomerLastName().sendKeys(configProperties.getProperty("website.createAnAccount.customerLastName"));
            registrationPage.getPassword().sendKeys(configProperties.getProperty("website.createAnAccount.pwd"));
            registrationPage.getFirstName().sendKeys(configProperties.getProperty("website.createAnAccount.firstName"));
            registrationPage.getLastName().sendKeys(configProperties.getProperty("website.createAnAccount.lastName"));
            registrationPage.getAddress().sendKeys(configProperties.getProperty("website.createAnAccount.address"));
            registrationPage.getCity().sendKeys(configProperties.getProperty("website.createAnAccount.city"));
            registrationPage.getState().selectByVisibleText(configProperties.getProperty("website.createAnAccount.state"));
            registrationPage.getPostcode().sendKeys(configProperties.getProperty("website.createAnAccount.postalCode"));
            registrationPage.getCountry().selectByVisibleText(configProperties.getProperty("website.createAnAccount.country"));
            registrationPage.getMobileNo().sendKeys(configProperties.getProperty("website.createAnAccount.mobile"));
            registrationPage.getFutureReference().sendKeys(configProperties.getProperty("website.createAnAccount.alias"));

            registrationPage.getSubmitAccount().click();

            MyAccountPage myAccountPage = PageFactory.initElements(webDriver, MyAccountPage.class);
            WebDriverWait waitForMyAccount = new WebDriverWait(webDriver,10);
            WebElement myAccount = waitForMyAccount.until(ExpectedConditions
                    .visibilityOf(myAccountPage.getMyAccount()));

            String expectedResult = configProperties.getProperty("createAnAccount.expectedResult");
            String actualResult = myAccount.getText();
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
