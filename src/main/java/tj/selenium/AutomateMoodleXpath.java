package tj.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomateMoodleXpath {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:/EDU/Software/chromedriver_win32/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        try{
            webDriver.get("https://school.moodledemo.net/");
            webDriver.manage().window().maximize();

            WebElement findLogIn = webDriver.findElement(By.xpath("//*[@id='page-wrapper']/nav//a[text()='Log in']"));
            findLogIn.click();
            Thread.sleep(2000);

            WebElement findUserName = webDriver.findElement(By.xpath("//*[@id=\"username\"]"));
            findUserName.sendKeys("student");
            Thread.sleep(2000);

            WebElement findFWD = webDriver.findElement(By.xpath("//*[@id=\"password\"]"));
            findFWD.sendKeys("moodle");
            Thread.sleep(2000);

            webDriver.findElement((By.xpath("//*[@id=\"loginbtn\"]"))).click();
            Thread.sleep(3000);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            webDriver.close();
            webDriver.quit();
        }
    }
}
