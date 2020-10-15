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

public class ValidateDeleteFromContactsDB {
    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(ValidateDeleteFromContactsDB.class);

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

    @Test
    public void ValidateInsertDataToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/employee?user=root&password=1234");
            LOGGER.info("Connected to the DB");

            statement = connection.createStatement();
            // Result set get the result of the SQL query
            statement.executeUpdate("DELETE FROM `contacts`.`person`\n" +
                    "WHERE `person`.person_ID = 9 ");
            LOGGER.info("Deleted the row from DB");

            //Retrieve Data from DB
            resultSet = statement.executeQuery("Select *\n" + "from `contacts`.`person`\n" +
                    "where `person`.person_id  = 9");
            LOGGER.info("Retrieve data from DB");

            int rowCounter = 0;
            String personID = null;
            while (resultSet.next()) {

                personID = resultSet.getString("person_id");

                LOGGER.info("personID is : " + personID);

                rowCounter++;

            }

            String expectedResult = configProperties.getProperty("selenium.db.personID.delete");
            String actualResult = personID;
            LOGGER.info("Actual : " + actualResult + " Expected : " + expectedResult);
            Assert.assertEquals(actualResult, expectedResult);


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

}
