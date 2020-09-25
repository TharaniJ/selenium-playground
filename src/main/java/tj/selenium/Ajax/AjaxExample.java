package tj.selenium.Ajax;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;


public class AjaxExample {
    public static void main(String[] args) {
        Logger LOGGERS = LoggerFactory.getLogger(AjaxExample.class);

        String configPropertiesFilePath  = "data/configuration.properties";

        File configFile = new File(configPropertiesFilePath);

        Properties configProperties = new Properties();

        FileReader configFileReader;

        WebDriver webDriver = null ;

        try{

            //instantiate new file reader
            configFileReader = new FileReader(configFile);

            //use file reader to load the property
            configProperties.load(configFileReader);

            System.setProperty("webdriver.chrome.driver", configProperties.getProperty("selenium.driver.chrome"));

            webDriver = new ChromeDriver();

            //Navigate to the moodle site
            webDriver.get(configProperties.getProperty("selenium.w3schools.ajax.url"));

            webDriver.manage().window().maximize();

            WebDriverWait waitForCDInfoBtn = new WebDriverWait(webDriver,10);
            WebElement tableBtn = webDriver.findElement(By.xpath("//*[@id='txtCDInfo']/button"));
            waitForCDInfoBtn.until(ExpectedConditions.elementToBeClickable(tableBtn));
            tableBtn.click();

            WebDriverWait waitForCDInfoTable = new WebDriverWait(webDriver,10);
            waitForCDInfoTable.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='txtCDInfo']/table/tbody/tr[2]/td[1]")));
            WebElement tableContent = webDriver.findElement(By.xpath("//*[@id='txtCDInfo']/table/tbody/tr[2]/td[1]"));


            String actualResult = tableContent.getText();
            String expectedResult = "Empire Burlesque";
            LOGGERS.info("Actual : " + actualResult + " Expected : " + expectedResult);
            Assert.assertEquals(actualResult,expectedResult);

        }catch (Exception e){

            System.out.println("Error occurred : " + e.getMessage());

        }finally {

            webDriver.close();
            webDriver.quit();
        }

    }
}
