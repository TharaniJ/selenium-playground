package tj.selenium.alertsandactionclasses;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tj.selenium.pom.pages.MoodleHomePage;

public class ActionClassMouseOverMovement {
    //Initiate the logger
    Logger LOGGER = LoggerFactory.getLogger(ActionClassKeyBoardUse.class);
    WebDriver webDriver;
    @BeforeClass
    public void InitiateWebDriver() {

        //Setting the driver path
        System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");
        //Instantiating the webdriver
        webDriver = new ChromeDriver();
    }

    @Test
    public void ActionClass() {
        try {

            //chrome driver to load the moodle site
            webDriver.get("https://school.moodledemo.net/");
            //Maximize the page
            webDriver.manage().window().maximize();

            //Initiate the page object nodel
            MoodleHomePage moodleHomePage = PageFactory.initElements(webDriver,MoodleHomePage.class);
            WebDriverWait waitForSearchIcon = new WebDriverWait(webDriver,10);
            waitForSearchIcon.until(ExpectedConditions.visibilityOf(moodleHomePage.getSearchIconButton()));


            Actions mouseMoveOverAction = new Actions(webDriver);
            mouseMoveOverAction.moveToElement(moodleHomePage.getSearchIconButton()).perform();
            LOGGER.info("Moved mouse over Search the search box");

            WebDriverWait waitForDynamicSearchTextBox = new WebDriverWait(webDriver, 10);
            waitForDynamicSearchTextBox.until(ExpectedConditions.visibilityOf(moodleHomePage.getDynamicSearchTextBox()));


            Thread.sleep(3000);

            Actions typeSearchKeyAction = new Actions(webDriver);

            typeSearchKeyAction
                    .sendKeys(moodleHomePage.getDynamicSearchTextBox(), "first ")
                    .keyDown(Keys.SHIFT)
                    .sendKeys("second")
                    .keyUp(Keys.SHIFT)
                    .perform();

            Thread.sleep(3000);



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public void CloseDriver() {

        webDriver.close();
        webDriver.quit();
    }

}
