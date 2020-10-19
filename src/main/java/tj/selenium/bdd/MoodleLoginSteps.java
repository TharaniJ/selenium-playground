package tj.selenium.bdd;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

public class MoodleLoginSteps {
    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(MoodleLoginSteps.class);

    WebDriver webDriver ;

//    @Before
//    public void validateStemConfig(){
//
//        System.setProperty("webdriver.chrome.driver","C:/EDU/Software/chromedriver_win32/chromedriver.exe");
//
//        webDriver = new ChromeDriver();
//
//    }

    @Given("User navigate to the moodle site")
    public void userNavigateToTheMoodleSite() {
        try{
            webDriver.get("https://school.moodledemo.net/");

            LOGGER.info("Navigated to the moodle site");

            webDriver.manage().window().maximize();

        }catch (Exception e){

            LOGGER.info("Error occurred in closing the file: " + e.getMessage());

        }
    }

    @And("User navigate to the login page")
    public void userNavigateToTheLoginPage() {
        try{
            WebDriverWait waitForLogInButton= new WebDriverWait(webDriver, 10);

            WebElement loginButton = waitForLogInButton
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath("//*[@id='page-wrapper']/nav/ul[2]/li[3]/div/span/a")));

            loginButton.click();

            LOGGER.info("Clicked on login link.");

        }catch (Exception e){

            LOGGER.info("Error occurred in closing the file: " + e.getMessage());

        }

    }

    @And("User given {string} as username and {string} as password")
    public void userGivenAsUsernameAndAsPassword(String username, String password) {
        try{
            WebDriverWait waitForUserNameTextBox= new WebDriverWait(webDriver, 10);
            WebElement userNameTextBox = waitForUserNameTextBox
                    .until(ExpectedConditions.elementToBeClickable(By.id("username")));
            userNameTextBox.sendKeys(username);
            LOGGER.info("User entered the username");

            WebElement passwordTextBox = webDriver.findElement(By.id("password"));
            passwordTextBox.sendKeys(password);
            LOGGER.info("User entered the password");

            WebElement rememberMe = webDriver.findElement(By.id("rememberusername"));
            rememberMe.click();
            LOGGER.info("Checked on remember me check box.");


        }catch (Exception e){

            LOGGER.info("Error occurred in closing the file: " + e.getMessage());

        }
    }

    @When("User clicks the login button")
    public void userClicksTheLoginButton() {
        try{

            WebElement logInButton  = webDriver.findElement(By.id("loginbtn"));

            logInButton.click();

            LOGGER.info("User clicked on login button");

        }catch (Exception e){

            LOGGER.info("Error occurred in closing the file: " + e.getMessage());
        }
    }

    @Then("User should be navigate to the home moodle page")
    public void userShouldBeNavigateToTheHomeMoodlePage() {

        String actualResult = webDriver.getTitle().trim();

        String expectedResult = "Dashboard";

        LOGGER.info("Actual : " + actualResult + " Expected : " + expectedResult);

        assertEquals(actualResult,expectedResult);
    }

    @And("Username should appear in the right top corner of the home page")
    public void usernameShouldAppearInTheRightTopCornerOfTheHomePage() {

        WebElement userNameOfTheUser  = webDriver
                .findElement(By.xpath("//*[@id='action-menu-toggle-1']/span/span[@class='usertext mr-1']"));

        String actualResult = userNameOfTheUser.getText();

        String expectedResult = "Barbara Gardner";

        LOGGER.info("Actual : " + actualResult + " Expected : " + expectedResult);

        assertEquals(actualResult,expectedResult);
    }

//    @After
//    public void closeWebDriver() {
//
//        webDriver.close();
//
//        webDriver.quit();
//
//        LOGGER.info("Browser close successfully!");
//    }
}
