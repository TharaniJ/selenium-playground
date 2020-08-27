package tj.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import sun.rmi.runtime.NewThreadAction;

public class AutomateMoodle {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();
        try {

            //Navigate to the moodle site
            webDriver.get("https://school.moodledemo.net/");
            //maximize the window
            webDriver.manage().window().maximize();
            //click on login link
            webDriver.findElement(By.linkText("Log in")).click();
            Thread.sleep(5000);

            //login to the moodle
            webDriver.findElement(By.id("username")).sendKeys("student");
            webDriver.findElement(By.id("password")).sendKeys("moodle");
            webDriver.findElement(By.id("loginbtn")).click();
            Thread.sleep(5000);

            //logout from moodle
            WebElement dropDownLink = webDriver.findElement(By.id("action-menu-toggle-1"));
            dropDownLink.click();
            Thread.sleep(2000);

            WebElement logOutLink = webDriver.findElement(By.id("actionmenuaction-6"));
            logOutLink.click();
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            webDriver.quit();
            webDriver.close();
        }


    }
}
