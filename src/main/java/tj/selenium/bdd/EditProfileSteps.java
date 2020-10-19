package tj.selenium.bdd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditProfileSteps {
    Logger LOGGER = LoggerFactory.getLogger(EditProfileSteps.class);

    @Given("User navigate to the moodle site and then navigate to the login page")
    public void userNavigateToTheMoodleSiteAndThenNavigateToTheLoginPage() {
        LOGGER.info("Navigated to Moodle site");
    }

    @And("User given {string} as username and {string} as password and login")
    public void userGivenAsUsernameAndAsPasswordAndLogin(String username, String password) {
        LOGGER.info("Username is :" + username + "Password is:" + password);
    }

    @And("User navigate to the profile page")
    public void userNavigateToTheProfilePage() {
        LOGGER.info("User navigated to the profile page");
    }

    @Given("User Navigate to the edit profile page")
    public void userNavigateToTheEditProfilePage() {
        LOGGER.info("User navigated to the edit profile page");
    }

    @And("Scroll down and find the description")
    public void scrollDownAndFindTheDescription() {
        LOGGER.info("Scrolled down for description ");
    }

    @When("User remove the description and add {string} and submit updates")
    public void userRemoveTheDescriptionAndAddAndSubmitUpdates(String description) {
        LOGGER.info("Description is :" + description );
    }

    @Then("User will navigate back to the profile page")
    public void userWillNavigateBackToTheProfilePage() {
        LOGGER.info("User in the profile page" );
    }

    @And("Updated description will appear in the profile")
    public void updatedDescriptionWillAppearInTheProfile() {
        LOGGER.info("Description updated" );
    }

    @And("Scroll down and click interest")
    public void scrollDownAndClickInterest() {
        LOGGER.info("Scrolled down for interest" );
    }

    @When("Add {string} as interest, remove {string} from interest and submit updates")
    public void addAsInterestRemoveFromInterestAndSubmitUpdates(String framing, String music) {
        LOGGER.info("Added" + framing + "Removed" + music );
    }

    @And("Updated interest will appear in the profile")
    public void updatedInterestWillAppearInTheProfile() {

        LOGGER.info("Added farming as interest and removed music from user interest" );
    }

    @And("Scroll down and find the country")
    public void scrollDownAndFindTheCountry() {
        LOGGER.info("scrolled down for country" );

    }

    @When("User update the country as india and submit updates")
    public void userUpdateTheCountryAsIndiaAndSubmitUpdates() {
        LOGGER.info("Country updated as india" );

    }

    @And("Updated country will appear in the profile")
    public void updatedCountryWillAppearInTheProfile() {
        LOGGER.info("updated county available in the profile page" );
    }
}
