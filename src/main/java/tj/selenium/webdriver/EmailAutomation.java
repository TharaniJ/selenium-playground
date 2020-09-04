package tj.selenium.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmailAutomation {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:/EDU/Software/chromedriver_win32/chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        try {
            //Navigate to the webpage
            webDriver.get("https://www.gmail.com");
            //maximize the window
            webDriver.manage().window().maximize();

            WebElement emailIDTextBox = webDriver.findElement(By.name("identifier"));
            emailIDTextBox.sendKeys("Tharani03091992@gmail.com");
            Thread.sleep(1000);

            webDriver.findElement(By.className("VfPpkd-RLmnJb")).click();
            Thread.sleep(10000);

            WebElement passwordTextBox = webDriver.findElement(By.name("password"));
            passwordTextBox.sendKeys("Citi@123");

            Thread.sleep(2000);

            webDriver.findElement(By.className("VfPpkd-vQzf8d")).click();
            Thread.sleep(2000);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            webDriver.close();
            webDriver.quit();
        }

    }
}
