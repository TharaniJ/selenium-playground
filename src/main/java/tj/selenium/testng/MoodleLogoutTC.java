package tj.selenium.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tj.selenium.pom.pages.MoodleHomePage;
import tj.selenium.pom.pages.MoodleLoginPage;
import tj.selenium.pom.pages.MoodleLogoutPage;

import java.util.concurrent.TimeUnit;

public class MoodleLogoutTC {
    Logger LOGGERS = LoggerFactory.getLogger(MoodleLoginTestCase.class);
    WebDriver webDriver;

    @BeforeClass
    public void InitiateWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeTest
    public void LogIn() {
        try {

            webDriver.get("https://school.moodledemo.net/");
            webDriver.manage().window().maximize();
            LOGGERS.info("Navigated to moodle page and maximized");

            MoodleHomePage moodleHomePage = PageFactory.initElements(webDriver, MoodleHomePage.class);
            moodleHomePage.getLoginLink().click();
            LOGGERS.info("Click log In button ");

            MoodleLoginPage moodleLoginPage = PageFactory.initElements(webDriver, MoodleLoginPage.class);
            moodleLoginPage.getUsernameTextBox().sendKeys("student");
            LOGGERS.info("Send username");

            moodleLoginPage.getPwdTextBox().sendKeys("moodle");
            LOGGERS.info("SendPassword");

            moodleLoginPage.getLoginButton().click();
            LOGGERS.info("Clicked on login button");

        } catch (Exception e) {
            LOGGERS.info("Error Occurred!", e);
        }
    }

    @Test
    public void LogOut() {

        MoodleLogoutPage moodleLogoutPage = PageFactory.initElements(webDriver, MoodleLogoutPage.class);
        moodleLogoutPage.getLogoutLink1().click();
        LOGGERS.info("Clicked on dashboard link!");
        moodleLogoutPage.getLogoutLink2().click();
        LOGGERS.info("Clicked on Logged out button!");


        MoodleHomePage moodleHomePage = PageFactory.initElements(webDriver,MoodleHomePage.class);
        if(moodleHomePage.getLoginLink().isDisplayed()){
            LOGGERS.info("Testcase passed : Logged out was successful");
        }else{
            System.out.println("Test case failed!");
        }
    }

    @AfterMethod
    public void CloseWebDriver() {
        webDriver.close();
        webDriver.quit();
        LOGGERS.info("Browser close successfully!");
    }

}
