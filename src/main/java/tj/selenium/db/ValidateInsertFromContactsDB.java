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

public class ValidateInsertFromContactsDB {


    //Initiating logger
    Logger LOGGER = LoggerFactory.getLogger(ValidateInsertFromContactsDB.class);

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
             statement.executeUpdate("INSERT INTO \n "+
                            "`contacts`.`person`(person_id,person_first_name,person_last_name,person_contacted_number,person_date_last_contacted,person_date_added) \n" +
                            " VALUE(9,'Geeth','Jeya',10,'2019-02-01 11:24:45','2019-12-11 11:34:45')");
            LOGGER.info("insert to the DB");

            //Retrieve Data from DB
            resultSet = statement.executeQuery("Select *\n" + "from `contacts`.`person`\n" +
                    "where `person`.person_id  = 9");
            LOGGER.info("Retrieve data from DB");

            String personID = null;
            String personFName= null;
            while (resultSet.next()) {
                personID = resultSet.getString("person_id");
                personFName = resultSet.getString("person_first_name");


            }

            String expectedResultID = configProperties.getProperty("selenium.db.personID.insert.id");
            String actualResultID = personID;
            LOGGER.info("Actual : " + actualResultID + " Expected : " + expectedResultID);
            Assert.assertEquals(actualResultID, expectedResultID);

            String expectedResultFName = configProperties.getProperty("selenium.db.personID.insert.fName");
            String actualResultFName = personFName;
            LOGGER.info("Actual : " + actualResultFName + " Expected : " + expectedResultFName);
            Assert.assertEquals(actualResultFName, expectedResultFName);

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
