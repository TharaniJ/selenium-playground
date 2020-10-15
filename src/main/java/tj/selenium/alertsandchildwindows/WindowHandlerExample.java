package tj.selenium.alertsandchildwindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * This example showcases how Selenium WebDriver manages browser windows.
 *
 * Each browser window is assigned an unique identifier by the WebDriver. This is called the 'window handle'
 *
 * During the automation, if an action spawns (i.e. creates) a new browser window (either as a pop-up or as a new tab),
 * although the focus may be on the new tab, the WebDriver still focuses on the original browser window (i.e. the one
 * which was there before the new window came in to being).
 *
 * If we need to operate on the new window, then we need to explicitly switch to the new window, by using the
 * new window's handle.
 *
 * Following example showcases how this is done.
 */
public class WindowHandlerExample {

    public static void main(String[] args) {

        Logger LOGGER = LoggerFactory.getLogger(WindowHandlerExample.class);

        String filePath = "data/configuration.properties";
        File applicationPropertiesFile = new File(filePath);

        Properties applicationProperties = new Properties();

        // Define these outside of the try-catch block so that it's visible to the entire main method.
        FileReader fileReader = null;
        WebDriver webDriver = null;

        try {
            fileReader = new FileReader(applicationPropertiesFile);
            applicationProperties.load(fileReader);

            System.setProperty("webdriver.chrome.driver", applicationProperties.getProperty("selenium.driver.chrome"));

            webDriver = new ChromeDriver();

            webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            webDriver.manage().deleteAllCookies();

            webDriver.get(applicationProperties.getProperty("example.windowshandler.url"));
            webDriver.manage().window().maximize();

            WebElement clickLink = webDriver.findElement(By.xpath("//a[contains(text(),'Click Here')]"));

            // The following click will open a new browser window and sets the focus on the new window
            clickLink.click();

            // Get the handle of the first window (i.e. the very first window the web driver created)
            String currentWindowHandle = webDriver.getWindowHandle();
            LOGGER.info("First (Old) Window Handle : " + currentWindowHandle);

            // Gets all the window handles
            Set<String> windowHandles = webDriver.getWindowHandles();

            /*
             * Following logic is to identify the new window handle. Since we already captured the old one, go through
             * all the window handles (in this case, there are two handles) and find the new one.
             *
             * This is done, since the WebDriver uses the window handles to refer to each of the windows.
             */
            String newWindowHandle = null;
            for (String windowHandle : windowHandles) {
                if (!currentWindowHandle.equalsIgnoreCase(windowHandle)) {
                    newWindowHandle = windowHandle;
                    break;
                }
            }

            LOGGER.info("New Window Handle : " + newWindowHandle);
            // Now that we found the window handle, we use that to switch to the new window handle
            webDriver.switchTo().window(newWindowHandle);

            Thread.sleep(3000); // Waiting for the page to load. DO NOT USE Thread.sleep in real work.

            WebElement emailTextBox = webDriver.findElement(By.name("emailid"));
            WebElement submitButton = webDriver.findElement(By.name("btnLogin"));

            emailTextBox.sendKeys("test@server.com");
            submitButton.click();

            Thread.sleep(3000);
        } catch (Exception e) {
            LOGGER.info("Error occurred : " + e.getMessage());
        } finally {
            webDriver.close();
            webDriver.quit();

            try {
                fileReader.close();
            } catch (Exception e) {
                LOGGER.info("Error occurred in closing FileWriter : " + e.getMessage());
            }
        }
    }
}
