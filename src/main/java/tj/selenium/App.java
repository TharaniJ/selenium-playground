package tj.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver",
                "C:/EDU/Software/chromedriver_win32/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        try {
            webDriver.get("https://www.google.com/");
            webDriver.manage().window().maximize();

            // Find the search box
            WebElement searchTextbox = webDriver.findElement(By.name("q"));
            searchTextbox.sendKeys("Sri Lanka");
            Thread.sleep(1000);
            searchTextbox.clear();
            searchTextbox.sendKeys("America");
            Thread.sleep(3000);
            searchTextbox.sendKeys(Keys.ENTER);
            System.out.println("Hello World!");
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webDriver.close();
            webDriver.quit();
        }
    }
}
