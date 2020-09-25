package tj.selenium.alertsandchildwindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class HandleChildWindow {
    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(HandleChildWindow.class);

        //Assigning to the filepath to a variable
        String configPropertiesFilePath = "data/configuration.properties";

        //Instantiate new file using file path variable
        File configFile = new File(configPropertiesFilePath);

        //instantiate new property
        Properties configProperties = new Properties();

        //Defining file Reader
        FileReader configFileReader;

        //Defining web driver
        WebDriver webDriver = null ;

        try{
            //instantiate new file reader
            configFileReader = new FileReader(configFile);

            //use file reader to load the property
            configProperties.load(configFileReader);

            //set the web driver
            System.setProperty("webdriver.chrome.driver", configProperties.getProperty("selenium.driver.chrome"));

            //initiate the web driver
            webDriver = new ChromeDriver();

            //Navigate to the moodle site
            webDriver.get(configProperties.getProperty("selenium.w3schools.childWindow.url"));

            webDriver.manage().window().maximize();

            //Locate the web element of the click here button
            WebElement clickHereLink = webDriver.findElement(By.xpath("//*[contains(@href,'popup.php')]"));
            clickHereLink.click();

            //identify the unique identifier of the main window
            String mainWindow = webDriver.getWindowHandle();

            // To handle all new opened window.
            Set<String> newWindows = webDriver.getWindowHandles();

            //iterate the all new windows opened.
            Iterator<String> iterateNewWindows = newWindows.iterator();

            //Check weather is there are any windows
            while(iterateNewWindows.hasNext())
            {
                //get the net available new window
                String childWindow = iterateNewWindows.next();

                //check weather the newly identified window is not a main window.
                if(!mainWindow.equalsIgnoreCase(childWindow))
                {

                    // Switching to Child window
                    webDriver.switchTo().window(childWindow);

                    //Locate the web element of the email id Text box
                    WebElement emailIDTextBox = webDriver.findElement(By.name("emailid"));
                    emailIDTextBox.sendKeys("gaurav.3n@gmail.com");

                    //Locate the web element of the submit button
                    WebElement submitButton = webDriver.findElement(By.name("btnLogin"));
                    submitButton.click();

                    // Closing the child Window.
                    webDriver.close();
                }
            }
            // Switch back to the main window.
            webDriver.switchTo().window(mainWindow);


        } catch (Exception e) {
            //Log the error message
            LOGGER.info("Error occurred : " + e.getMessage());
            e.printStackTrace();

        } finally {
            //Close the browser window that the driver has focus of.
            webDriver.close();
            //End the session.
            webDriver.quit();
        }
    }
}
