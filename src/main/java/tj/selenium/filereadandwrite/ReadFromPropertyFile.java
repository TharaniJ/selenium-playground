package tj.selenium.filereadandwrite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class ReadFromPropertyFile {
    public static void main(String[] args) {
        //Assigning to the filepath to a variable
        String configPropertiesFilePath = "data/configuration.properties";

        //Instantiate new file using file path variable
        File configFile = new File(configPropertiesFilePath);

        //instantiate new property
        Properties configProperties = new Properties();

        //Defining file Reader
        FileReader configFileReader;

        //Defining web driver
        WebDriver webDriver = null;

        try {
            //instantiate new file reader
            configFileReader = new FileReader(configFile);
            //use file reader to load the property
            configProperties.load(configFileReader);

            System.setProperty("webdriver.chrome.driver", configProperties.getProperty("selenium.driver.chrome"));

            webDriver = new ChromeDriver();

            //Navigate to the moodle site
            webDriver.get(configProperties.getProperty("selenium.moodle.url"));
            webDriver.manage().window().maximize();

            Thread.sleep(2000);

        }catch (Exception e){
            System.out.println("Error occurred : " + e.getMessage());
        }finally {
            webDriver.close();
            webDriver.quit();

        }

    }
}
