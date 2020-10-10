package tj.selenium.cookies;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tj.selenium.pom.pages.MoodleHomePage;
import tj.selenium.pom.pages.MoodleLoginPage;

import java.util.Date;
import java.util.Set;


public class CookiesExampleMoodle {
    public static void main( String[] args )
    {
        Logger LOGGER = LoggerFactory.getLogger(CookiesExampleMoodle.class);

        System.setProperty("webdriver.chrome.driver",
                "C:/EDU/Software/chromedriver_win32/chromedriver.exe");

        // Starts the ChromeDriver (located by the property webdriver.chrome.driver)
        // Initiates a session, which starts Chrome web browser
        WebDriver webDriver = new ChromeDriver();

        String cookieName = "he";
        String cookieValue = "he-Is-good!!";
        //String cookieDomain = "omega.playground.com";
        String cookieDomain = ".moodledemo.net";
        String cookiePath = "/";
        boolean isCookieSecure = false;
        boolean isCookieHttpOnly = true;
        Date cookieExpiry = null;


        try {
            // Commands Chrome browser to load Moodle demo site
            webDriver.get("https://school.moodledemo.net/");

            Cookie initCookie = new Cookie(cookieName, cookieValue, cookieDomain, cookiePath, cookieExpiry, isCookieSecure, isCookieHttpOnly);
            webDriver.manage().addCookie(initCookie);

            //webDriver.get("https://school.moodledemo.net/");

            // Maximizes the browser
            webDriver.manage().window().maximize();
            LOGGER.info("Browser opened and maximized");

            Thread.sleep(2000);

            // Instantiates the Page Object for Home Page
            MoodleHomePage moodleHomePage = PageFactory.initElements(webDriver, MoodleHomePage.class);

            moodleHomePage.getLoginLink().click();

            Thread.sleep(3000);

            // Instantiates the Page Object for Login page.
            MoodleLoginPage moodleLoginPage = PageFactory.initElements(webDriver, MoodleLoginPage.class);

            moodleLoginPage.getUsernameTextBox().sendKeys("student");
            moodleLoginPage.getPwdTextBox().sendKeys("moodle");

            Thread.sleep(2000);

            moodleLoginPage.getLoginButton().click();

            Thread.sleep(5000);

            Set<Cookie> cookies = webDriver.manage().getCookies();

            for (Cookie cookie : cookies) {
                LOGGER.info("Cookie Name : " + cookie.getName());
                LOGGER.info("Cookie Value : " + cookie.getValue());
                LOGGER.info("Cookie Domain : " + cookie.getDomain());
                LOGGER.info("Cookie Path : " + cookie.getPath());
                LOGGER.info("Cookie Is Secure : " + cookie.isSecure());
                LOGGER.info("Cookie Is HTTP Only : " + cookie.isHttpOnly());
                LOGGER.info("Cookie Expiry Timestamp : " + cookie.getExpiry());
            }

            webDriver.manage().deleteCookieNamed("MoodleSession");

            Cookie newMoodleCookie = new Cookie("MoodleSession", "TestValue", ".moodledemo.net", "/", cookieExpiry,
                    isCookieSecure, isCookieHttpOnly);

            webDriver.manage().addCookie(newMoodleCookie);

            LOGGER.info("--------------------------------------------------------------------------------------------");
            LOGGER.info("--------------------------------------------------------------------------------------------");

            for (Cookie cookie : cookies) {
                LOGGER.info("Cookie Name : " + cookie.getName());
                LOGGER.info("Cookie Value : " + cookie.getValue());
                LOGGER.info("Cookie Domain : " + cookie.getDomain());
                LOGGER.info("Cookie Path : " + cookie.getPath());
                LOGGER.info("Cookie Is Secure : " + cookie.isSecure());
                LOGGER.info("Cookie Is HTTP Only : " + cookie.isHttpOnly());
                LOGGER.info("Cookie Expiry Timestamp : " + cookie.getExpiry());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webDriver.close();
            webDriver.quit();
        }
    }
}
