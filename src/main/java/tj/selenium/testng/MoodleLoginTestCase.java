package tj.selenium.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sun.rmi.runtime.Log;
import tj.selenium.pom.pages.MoodleHomePage;
import tj.selenium.pom.pages.MoodleLoginPage;

import java.util.concurrent.TimeUnit;

public class MoodleLoginTestCase {

    Logger LOGGERS = LoggerFactory.getLogger(MoodleLoginTestCase.class);
    WebDriver webDriver;

    @BeforeClass
    public void InitiateWebDriver() {
        System.setProperty("webdriver.chrome.driver","C:/EDU/Software/chromedriver_win32/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void LoginWithInvalidPWDTestCase() {
        try {

            webDriver.get("https://school.moodledemo.net/");
            webDriver.manage().window().maximize();
            LOGGERS.info("Navigated to moodle page and maximized");

            MoodleHomePage moodleHomePage = PageFactory.initElements(webDriver,MoodleHomePage.class);
            moodleHomePage.getLoginLink().click();
            LOGGERS.info("Click log In button ");

            MoodleLoginPage moodleLoginPage = PageFactory.initElements(webDriver,MoodleLoginPage.class);
            moodleLoginPage.getUsernameTextBox().sendKeys("student");
            LOGGERS.info("Send username");

            moodleLoginPage.getPwdTextBox().sendKeys("moodle");
            LOGGERS.info("SendPassword");

            moodleLoginPage.getLoginButton().click();
            LOGGERS.info("Clicked on login button");

            String actualResult = webDriver.getTitle();
            String expectedResult = "Dashboard";
            LOGGERS.info("Actual : " + actualResult + " Expected : " + expectedResult);
            Assert.assertEquals(actualResult,expectedResult);

        } catch(Exception e) {
            LOGGERS.info("Error Occurred!",e);
        }
    }


    @AfterMethod
    public void CloseWebDriver() {
        webDriver.close();
        webDriver.quit();
        LOGGERS.info("Browser close successfully!");

    }
}
