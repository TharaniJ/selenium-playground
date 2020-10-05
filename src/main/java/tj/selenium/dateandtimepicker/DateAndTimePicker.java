package tj.selenium.dateandtimepicker;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class DateAndTimePicker {
    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(DateAndTimePicker.class);

    WebDriver driver;

    @Test

    public void dateTimePicker(){

        try {

            System.setProperty("webdriver.chrome.driver", "C:/EDU/Software/chromedriver_win32/chromedriver.exe");

            driver = new ChromeDriver();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.get("http://demo.guru99.com/test/");

            //Find the date time picker control

            WebElement dateBox = driver.findElement(By.xpath("//form//input[@name='bdaytime']"));

            //Fill date as mm/dd/yyyy as 09/25/2013

            dateBox.sendKeys("09252013");
            Thread.sleep(4000);

            //Press tab to shift focus to time field

            dateBox.sendKeys(Keys.TAB);

            //Fill time as 02:45 PM

            dateBox.sendKeys("0245PM");

            Thread.sleep(4000);

        }catch (Exception e){

            LOGGER.info("Error occurred : " + e.getMessage());

        }

    }

    @AfterMethod
    public void closeWebDriver() {

        driver.close();

        driver.quit();

        LOGGER.info("Browser close successfully!");
    }

}

