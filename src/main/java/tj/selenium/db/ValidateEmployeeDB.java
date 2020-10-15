package tj.selenium.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ValidateEmployeeDB {

    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(ValidateEmployeeDB.class);

    //Defining file Reader
    FileReader configFileReader;

    //Defining config file
    File configFile;

    //Defining the properties
    Properties configProperties;

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    @BeforeClass
    public void configSetup() {
        try {

            //Assigning to the filepath to a variable
            String configPropertiesFilePath = "data/configuration.properties";

            //Initiate new file using file path variable
            configFile = new File(configPropertiesFilePath);

            //Initiate new property
            configProperties = new Properties();

            //Initiate new file reader
            configFileReader = new FileReader(configFile);

            //use file reader to load the property
            configProperties.load(configFileReader);


        }catch (Exception e) {

            LOGGER.info("Error occurred in closing the file: " + e.getMessage());

        }finally {
            try {

                configFileReader.close();

            } catch (Exception e) {
                LOGGER.info("Error occurred in closing the file: " + e.getMessage());
            }
        }
    }

    @Test(priority = 2)
    public void ValidateTheDataInEmployeeDB() {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Setup the connection with the DB
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/employee?user=root&password=1234");
            LOGGER.info("Connected to the DB");
            /*
            if (connection != null) {
                System.out.println("Connection Successful!");
            }
            */
            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM `employee`.`employee`");

            System.out.println(" \n Data values : ");

            int rowCounter = 0;
            while (resultSet.next()) {
                System.out.println("Customer Employee ID : " + resultSet.getString("EmpID")); // Uses column name
                System.out.println("Customer Name : " + resultSet.getString(2));
                System.out.println("Employee Name : " + resultSet.getString("EmpName"));
                System.out.println("Employee Department : " + resultSet.getString("Department"));
                System.out.println();

                rowCounter++;

                if (rowCounter >= 3)
                    break;
            }


        } catch (Exception e) {

            LOGGER.info("Error occurred while getting the data from DB " + e.getMessage());


        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

                LOGGER.info("Error occurred while closing" + e.getMessage());

            }

        }
    }


    @Test(priority = 1)
    public void ValidateTheDataInEmployeeDB1() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/employee?user=root&password=1234");
            LOGGER.info("Connected to the DB");

            statement = connection.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery("SELECT * FROM `employee`.`employee`\n" +
                    "where employee.EmpName = 'CHIN YEN'");

            int rowCounter = 0;
            String empID = null;
            while (resultSet.next()) {

                 empID = resultSet.getString("EmpID");

                LOGGER.info("EmpID id : " + empID);

                rowCounter++;

                if (rowCounter >= 3)
                    break;
            }

            String expectedResult = configProperties.getProperty("selenium.db.empID.CHINYEN");
            String actualResult = empID;
            LOGGER.info("Actual : " + actualResult + " Expected : " + expectedResult);
            Assert.assertEquals(actualResult, expectedResult);

        }catch (Exception e){

            LOGGER.info("Error occurred while getting the data from DB " + e.getMessage());

        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

                LOGGER.info("Error occurred while closing" + e.getMessage());

            }

        }

    }
}
