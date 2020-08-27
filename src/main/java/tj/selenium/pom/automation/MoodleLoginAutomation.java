package tj.selenium.pom.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tj.selenium.pom.pages.*;

import java.util.Set;

public class MoodleLoginAutomation {
    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(MoodleLoginAutomation.class);

        System.setProperty("webdriver.chrome.driver","C:/EDU/Software/chromedriver_win32/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        try{
            webDriver.get("https://school.moodledemo.net/");
            webDriver.manage().window().maximize();
            Thread.sleep(2000);
            LOGGER.info("Successfully Open webpage");

            MoodleHomePage moodleHomePage = PageFactory.initElements(webDriver,MoodleHomePage.class);
            moodleHomePage.getLoginLink().click();
            Thread.sleep(2000);

            MoodleLoginPage moodleLoginPage = PageFactory.initElements(webDriver,MoodleLoginPage.class);
            moodleLoginPage.getUsernameTextBox().sendKeys("student");
            Thread.sleep(2000);
            moodleLoginPage.getPwdTextBox().sendKeys("moodle");
            Thread.sleep(2000);
            moodleLoginPage.getLoginButton().click();
            LOGGER.info("Successfully logged in ");

            MoodleLogoutPage moodleLogoutPage = PageFactory.initElements(webDriver,MoodleLogoutPage.class);
            moodleLogoutPage.getLogoutLink1().click();
            Thread.sleep(1000);
            moodleLogoutPage.getProfileLink().click();
            Thread.sleep(1000);

            MoodleProfilePage moodleProfilePage = PageFactory.initElements(webDriver,MoodleProfilePage.class);
            moodleProfilePage.getEditProfileLink().click();
            Thread.sleep(2000);
            LOGGER.info("Clicked on Edit Profile");

            MoodleEditProfilePage moodleEditProfilePage = PageFactory.initElements(webDriver,MoodleEditProfilePage.class);
            moodleEditProfilePage.getFirstNameTextBx().clear();
            moodleEditProfilePage.getFirstNameTextBx().sendKeys("arjun");
            Thread.sleep(2000);
            moodleEditProfilePage.getLastNameTextBox().clear();
            moodleEditProfilePage.getLastNameTextBox().sendKeys("seku");
            Thread.sleep(2000);
            moodleEditProfilePage.getEmailIDTextBx().clear();
            moodleEditProfilePage.getEmailIDTextBx().sendKeys("arr1@gmail.com");
            Thread.sleep(2000);
            moodleEditProfilePage.getDescriptionTextBox().clear();
            moodleEditProfilePage.getDescriptionTextBox().sendKeys("Thank you so much, stay happy.");
            Thread.sleep(2000);
            LOGGER.info("Successfully Edited");

            moodleEditProfilePage.getSubmitBtn().click();
            LOGGER.info("updated successfully");
            Thread.sleep(2000);

            moodleLogoutPage.getLogoutLink1().click();
            moodleLogoutPage.getLogoutLink2().click();
            Thread.sleep(2000);
            LOGGER.info("Successfully Logged out");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            webDriver.close();
            webDriver.quit();
        }
    }
}
