package tj.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomateMoodleCSS {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:/EDU/Software/chromedriver_win32/chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();

        try{
            webDriver.get("https://school.moodledemo.net/");
            webDriver.manage().window().maximize();

            webDriver.findElement(By.linkText("Log in")).click();
            Thread.sleep(5000);

            WebElement findUserName = webDriver.findElement(By.cssSelector("input[name='username']"));
            findUserName.sendKeys("student");
            Thread.sleep(5000);

            WebElement findPWD = webDriver.findElement(By.cssSelector("input[name='password']"));
            findPWD.sendKeys("moodle");
            Thread.sleep(5000);

            //webDriver.findElement(By.id("loginbtn")).click();
            webDriver.findElement(By.cssSelector("button[id='loginbtn']")).click();
            Thread.sleep(5000);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            webDriver.close();
            webDriver.quit();
        }
    }
}
