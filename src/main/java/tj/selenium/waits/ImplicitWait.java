package tj.selenium.waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tj.selenium.pom.pages.MoodleHomePage;
import tj.selenium.pom.pages.MoodleLoginPage;
import tj.selenium.pom.pages.MoodleLogoutPage;

import java.util.concurrent.TimeUnit;

public class ImplicitWait {
    public static void main(String[] args) {
        //Initiating the logger
        Logger Logger = LoggerFactory.getLogger(ImplicitWait.class);

        //Set web driver and path
        System.setProperty("webdriver.chrome.driver","C:/EDU/Software/chromedriver_win32/chromedriver.exe");

        //Initiating the web driver
        WebDriver webDriver = new ChromeDriver();

        //Initiating the implicit wait
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try{
            //Chrome browser to load the moodle site
            webDriver.get("https://school.moodledemo.net/");
            //Chrome browser to maximize the window
            webDriver.manage().window().maximize();
            //Log the actions
            Logger.info("Browser Opened and maximized!");

            //Initiate the moodle home page object model
            MoodleHomePage moodleHomePage = PageFactory.initElements(webDriver,MoodleHomePage.class);
            //Get the login link web element and perform action
            moodleHomePage.getLoginLink().click();
            Logger.info("Clicked Login link");

            //Initiate the moodle login page object model
            MoodleLoginPage moodleLoginPage = PageFactory.initElements(webDriver,MoodleLoginPage.class);
            //Send username
            moodleLoginPage.getUsernameTextBox().sendKeys("student");
            //Send password
            moodleLoginPage.getPwdTextBox().sendKeys("moodle");
            //Click login button
            moodleLoginPage.getLoginButton().click();
            Logger.info("Logged into the moodle");

            //Initiate the moodle logout page object model
            MoodleLogoutPage moodleLogoutPage = PageFactory.initElements(webDriver,MoodleLogoutPage.class);
            moodleLogoutPage.getLogoutLink1().click();
            moodleLogoutPage.getLogoutLink2().click();
            Logger.info("Logged out from moodle");

            Thread.sleep(3000);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            webDriver.quit();
            webDriver.close();
        }

    }
}
