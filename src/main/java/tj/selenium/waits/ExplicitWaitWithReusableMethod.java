package tj.selenium.waits;

import okio.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tj.selenium.pom.pages.MoodleHomePage;
import tj.selenium.pom.pages.MoodleLoginPage;
import tj.selenium.pom.pages.MoodleLogoutPage;

public class ExplicitWaitWithReusableMethod {
    public static void main(String[] args) {

        Logger LOG = LoggerFactory.getLogger(ExplicitWaitWithReusableMethod.class);

        System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        try {

            webDriver.get("https://school.moodledemo.net/");
            webDriver.manage().window().maximize();

            LOG.info("Window maximized!");

            MoodleHomePage moodleHomePage = PageFactory.initElements(webDriver, MoodleHomePage.class);
            ClickAndWait(webDriver,moodleHomePage.getLoginLink(), 10);

            MoodleLoginPage moodleLoginPage = PageFactory.initElements(webDriver, MoodleLoginPage.class);
            SendKeyAndWait(webDriver,moodleLoginPage.getUsernameTextBox(), "student",10);
            SendKeyAndWait(webDriver,moodleLoginPage.getPwdTextBox(),"moodle",10);
            ClickAndWait(webDriver, moodleLoginPage.getLoginButton(), 10);

            MoodleLogoutPage moodleLogoutPage = PageFactory.initElements(webDriver, MoodleLogoutPage.class);
            ClickAndWait(webDriver,moodleLogoutPage.getLogoutLink1(),10);
            ClickAndWait(webDriver,moodleLogoutPage.getLogoutLink2(),10);

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            webDriver.quit();
            webDriver.close();
        }
    }


    public static void ClickAndWait(WebDriver webDriver, WebElement webElement, long timeout)
    {
        WebDriverWait clickWait = new WebDriverWait(webDriver, timeout);
        clickWait.until(ExpectedConditions.elementToBeClickable(webElement)).click();

    }

    public  static void SendKeyAndWait(WebDriver webDriver, WebElement webElement,String KeysToSend,  long timeout)
    {
        WebDriverWait sendKeyWait = new WebDriverWait(webDriver,timeout);
        sendKeyWait.until(ExpectedConditions.visibilityOf(webElement)).sendKeys(KeysToSend);
    }

}