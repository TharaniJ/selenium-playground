package tj.selenium.javascriptexecuter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class javaScriptExecuterExample {
    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(javaScriptExecuterExample.class);

        String filePath = "src/main/resources/application.properties";
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

            webDriver.get(applicationProperties.getProperty("example.ajax.url"));
            webDriver.manage().window().maximize();

            Thread.sleep(2000);

            // Create a JavaScript executor. This is not a pure object creation. This just casts the WebDriver
            // to give the form of a JavaScript executor.
            JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;

            /*
             * We are trying to click the button 'Get CD Info' purely by passing JavaScript commands.
             *
             * We use the following strategy;
             * 1. Find the DIV with the id 'txtCDInfo'
             * 2. Find all the button elements (yes it's elements) inside this div.
             *    - This div has only one button
             *    - The function getElementsByTagName returns an array
             * 3. Get the first element in the array (cdInfoButtonArray) and click that.
             */
            String jsScript = "var div = document.getElementById('txtCDInfo');"     // Get the div with id == txtCDInfo
                    + "var cdInfoButtonArray = div.getElementsByTagName('button');" // Get all button elements inside that div
                    + "cdInfoButtonArray[0].click();";                              // Get the first item in the button array and click

            jsExecutor.executeScript(jsScript);

            Thread.sleep(5000);
        } catch (Exception e) {
            LOGGER.info("Error occurred : " + e.getMessage());
            e.printStackTrace();
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
