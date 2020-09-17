package tj.selenium.alertsandactionclasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ActionClassRobotClass {
    public static void main(String[] args) {

        Logger Loggers = LoggerFactory.getLogger(ActionClassRobotClass.class);
        //Setting the driver path
        System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");
        //Instantiating the webdriver
        WebDriver webDriver = new ChromeDriver();
        try {
            //web driver to load the moodle website
            webDriver.get("https://school.moodledemo.net/");

            //maximize the window
            webDriver.manage().window().maximize();

            Thread.sleep(1000);

            //Initiating robot class
            Robot robot = new Robot();
            robot.delay(1000);

            Actions rightClickAction = new Actions(webDriver);
            rightClickAction.contextClick().perform();

            Thread.sleep(1000);

            // robot.setAutoDelay(1500); //this will work as implicit wait.

            // Presses the Down-Arrow 3 times to reach the 'Save-As' menu from the right-click menu
            for (int i = 1; i <= 3; i++) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.delay(1000);
                robot.keyRelease(KeyEvent.VK_DOWN);
            }

            // Click the 'Save-As' option by pressing the ENTER key
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.delay(1000);

            //tag keys to get to enter and save file
            for (int i = 1; i <= 3; i++) {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.delay(1000);
                robot.keyRelease(KeyEvent.VK_TAB);
            }

            // Click the 'Save-As' option by pressing the ENTER key
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            robot.delay(1000);

            Loggers.info("Page saved successfully");


        }catch (Exception e){
            e.printStackTrace();

        }finally {
            webDriver.close();
            webDriver.quit();
        }

    }
}
