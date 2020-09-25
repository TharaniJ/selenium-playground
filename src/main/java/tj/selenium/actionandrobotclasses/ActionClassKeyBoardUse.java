package tj.selenium.actionandrobotclasses;
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

public class ActionClassKeyBoardUse {
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

                //Initiating the page object model
                MoodleHomePage moodleHomePage = PageFactory.initElements(webDriver, MoodleHomePage.class);

                //initiate the explicit wait
                WebDriverWait waitForSearchBoxVisible = new WebDriverWait(webDriver, 10);
                waitForSearchBoxVisible.until(ExpectedConditions.elementToBeClickable(moodleHomePage.getSearchIconButton()));
                moodleHomePage.getSearchIconButton().click();
                LOGGER.info("Clicked on the Search box");

                //initiate the explicit wait
                WebDriverWait waitForDynamicSearchTextBox = new WebDriverWait(webDriver, 10);
                waitForDynamicSearchTextBox.until(ExpectedConditions.visibilityOf(moodleHomePage.getDynamicSearchTextBox()));
                LOGGER.info("located the web element of the search box");

                //Clear the search box before send key values
                moodleHomePage.getDynamicSearchTextBox().clear();
                //give extra sleeping time
                Thread.sleep(3000);

                //initiate the action class
                Actions typeSearchKeyAction = new Actions(webDriver);

                typeSearchKeyAction
                        .sendKeys(moodleHomePage.getDynamicSearchTextBox(), "first ")
                        .keyDown(Keys.SHIFT)
                        .sendKeys("second")
                        .keyUp(Keys.SHIFT)
                        .perform();

                Thread.sleep(3000);


            } catch (Exception e) {
                e.printStackTrace();

            }
        }

            @AfterClass
                    public void CloseDriver() {

                    webDriver.close();
                    webDriver.quit();
                }
            }



