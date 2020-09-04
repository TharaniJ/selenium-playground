package tj.selenium.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MoodleEditProfilePage {

    @FindBy(id = "id_firstname")
    @CacheLookup
    private WebElement firstNameTextBx;

    @FindBy(id = "id_lastname")
    @CacheLookup
    private WebElement lastNameTextBox;

    @FindBy(id="id_email")
    @CacheLookup
    private WebElement emailIDTextBx;

    @FindBy(id = "id_maildisplay")
    @CacheLookup
    private WebElement emailDisplayDropDown;

    @FindBy(id = "id_description_editoreditable")
    @CacheLookup
    private WebElement descriptionTextBox;

    @FindBy(id = "id_submitbutton")
    @CacheLookup
    private WebElement submitBtn;


    public Select getEmailDisplayDropDown(){
        Select dropDown = new Select(emailDisplayDropDown);
        return dropDown;
    }

    public WebElement getFirstNameTextBx() {
        return firstNameTextBx;
    }

    public WebElement getLastNameTextBox() {
        return lastNameTextBox;
    }

    public WebElement getEmailIDTextBx() {
        return emailIDTextBx;
    }

    public WebElement getDescriptionTextBox() {
        return descriptionTextBox;
    }

    public WebElement getSubmitBtn() {
        return submitBtn;
    }
}
