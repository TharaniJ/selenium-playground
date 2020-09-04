package tj.selenium.waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tj.selenium.pom.pages.MoodleHomePage;
import tj.selenium.pom.pages.MoodleLoginPage;
import tj.selenium.pom.pages.MoodleLogoutPage;

public class ExplicitWait {
    public static void main(String[] args) {

        Logger LOGGER = LoggerFactory.getLogger(ExplicitWait.class);

        System.setProperty("webdriver.chrome.driver","C:/EDU/Software/chromedriver_win32/chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        try{
            webDriver.get("https://school.moodledemo.net/");
            webDriver.manage().window().maximize();

            MoodleHomePage moodleHomePage = PageFactory.initElements(webDriver,MoodleHomePage.class);
            WebDriverWait waitForLoginLink = new WebDriverWait(webDriver,10);
            waitForLoginLink.until(ExpectedConditions.elementToBeClickable(moodleHomePage.getLoginLink()));
            moodleHomePage.getLoginLink().click();
            LOGGER.info("Clicked login link");

            MoodleLoginPage moodleLoginPage = PageFactory.initElements(webDriver,MoodleLoginPage.class);
            WebDriverWait waitForUsernameTextBx = new WebDriverWait(webDriver,10);
            waitForUsernameTextBx.until(ExpectedConditions.elementToBeClickable(moodleLoginPage.getUsernameTextBox()));
            moodleLoginPage.getUsernameTextBox().sendKeys("student");

            WebDriverWait waitForPWDTextBx = new WebDriverWait(webDriver,10);
            waitForLoginLink.until(ExpectedConditions.elementToBeClickable(moodleLoginPage.getPwdTextBox()));
            moodleLoginPage.getPwdTextBox().sendKeys("moodle");

            WebDriverWait waitForLogin = new WebDriverWait(webDriver,10);
            waitForLogin.until(ExpectedConditions.elementToBeClickable(moodleLoginPage.getLoginButton()));
            moodleLoginPage.getLoginButton().click();
            LOGGER.info("Logging In");

            MoodleLogoutPage moodleLogoutPage = PageFactory.initElements(webDriver,MoodleLogoutPage.class);
            WebDriverWait waitToClickDashboardLink = new WebDriverWait(webDriver,10);
            waitToClickDashboardLink.until(ExpectedConditions.elementToBeClickable(moodleLogoutPage.getLogoutLink1()));
            moodleLogoutPage.getLogoutLink1().click();

            WebDriverWait waitForLogoutLink = new WebDriverWait(webDriver, 10);
            waitForLogoutLink.until(ExpectedConditions.elementToBeClickable(moodleLogoutPage.getLogoutLink2()));
            moodleLogoutPage.getLogoutLink2().click();

            Thread.sleep(2000);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            webDriver.quit();
            webDriver.close();
        }
    }
}
