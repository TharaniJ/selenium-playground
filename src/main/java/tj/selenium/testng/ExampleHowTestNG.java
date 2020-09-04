package tj.selenium.testng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.time.LocalTime;

public class ExampleHowTestNG {

    private static Logger LOGGER = LoggerFactory.getLogger(ExampleHowTestNG.class);

    @BeforeSuite
    public void beforeTestSuite() {
        LOGGER.info("Before Test Suite. Time : " + LocalTime.now().toString());
    }

    @BeforeTest
    public void beforeTest() { LOGGER.info("Before Test. Time : " + LocalTime.now().toString()); }

    @BeforeClass
    public void beforeClass() {
        LOGGER.info("Before Class. Time : " + LocalTime.now().toString());
    }

    @BeforeMethod
    public void beforeMethod() {
        LOGGER.info("Before Method. Time : " + LocalTime.now().toString());
    }

    @Test
    public void testScenario1() {
        LOGGER.info("Test scenario 1. Time : " + LocalTime.now().toString());
    }

    @Test
    public void testScenario2() {
        LOGGER.info("Test scenario 2. Time : " + LocalTime.now().toString());
    }

    @AfterMethod
    public void afterMethod() {
        LOGGER.info("After Method. Time : " + LocalTime.now().toString());
    }

    @AfterClass
    public void afterClass() {
        LOGGER.info("After Class. Time : " + LocalTime.now().toString());
    }

    @AfterTest
    public void afterTest() {
        LOGGER.info("After Test. Time : " + LocalTime.now().toString());
    }

    @AfterSuite
    public void afterTestSuite() {
        LOGGER.info("After Test Suite. Time : " + LocalTime.now().toString());
    }

}
