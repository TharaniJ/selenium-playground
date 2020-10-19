package tj.selenium.bdd;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CalculatorSteps {

    Logger LOGGER = LoggerFactory.getLogger(CalculatorSteps.class);

    private WebDriver webDriver = null;

    public CalculatorSteps() {
        System.setProperty("webdriver.chrome.driver",
                "C:/EDU/Software/chromedriver_win32/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Given("The user opened the Calculator web page")
    public void theUserOpenedTheCalculatorWebPage () {
        webDriver.get("https://www.calculatorsoup.com/calculators/math/basic.php");
        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        // Deletes all the cookies the Web Browser has. This will give a Fresh browser.
        webDriver.manage().deleteAllCookies();

        // Maximizes the browser
        webDriver.manage().window().maximize();
    }


    @And("The input area is empty")
    public void theInputAreaIsEmpty () {
        try {
            WebElement clearEverythingBtn = webDriver.findElement(By.name("clearButton"));
            Thread.sleep(1000);
            clearEverythingBtn.click();
        } catch (Exception e) {
            LOGGER.error("Error occurred", e);
        }
    }

    @When("The user enters {int} and {int} and press Enter key")
    public void theUserEntersNumberAndNumberAndPressEnterKey ( int number1, int number2){
        try {
            WebElement inputArea = webDriver.findElement(By.name("display"));
            Thread.sleep(1000);
            inputArea.sendKeys(String.valueOf(number1));
            Thread.sleep(1000);
            inputArea.sendKeys("+");
            Thread.sleep(1000);
            inputArea.sendKeys(String.valueOf(number2));
            Thread.sleep(1000);
            inputArea.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            LOGGER.error("Error occurred", e);
        }
    }

    @Then("The input area should display the {int}")
    public void theInputAreaShouldDisplayTheAnswer ( int answer){
        try {
            WebElement inputArea = webDriver.findElement(By.name("display"));
            Thread.sleep(1000);
            String calcAnswer = inputArea.getAttribute("value");
            LOGGER.info("Calculator answer is " + calcAnswer);

            assertEquals(String.valueOf(answer), calcAnswer);
        } catch (Exception e) {
            LOGGER.error("Error occurred", e);
        }
    }

    @After
    public void closeWebDriver () {
        webDriver.close();
        webDriver.quit();
    }
}
