package tj.selenium.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MoodleEditProfilePage {
    public WebElement getFirstNameTextBx() { return firstNameTextBx; }
    @FindBy(id = "id_firstname")
    @CacheLookup
    private WebElement firstNameTextBx;

    public WebElement getLastNameTextBox() { return lastNameTextBox; }
    @FindBy(id = "id_lastname")
    @CacheLookup
    private WebElement lastNameTextBox;

    public WebElement getEmailIDTextBx() { return emailIDTextBx; }
    @FindBy(id="id_email")
    @CacheLookup
    private WebElement emailIDTextBx;

    public WebElement getEmailDisplayDropDown() { return emailDisplayDropDown; }
    @FindBy(id = "id_maildisplay")
    @CacheLookup
    private WebElement emailDisplayDropDown;

    public WebElement getDescriptionTextBox() { return descriptionTextBox; }
    @FindBy(id = "id_description_editoreditable")
    @CacheLookup
    private WebElement descriptionTextBox;

    public WebElement getSubmitBtn() { return submitBtn; }
    @FindBy(id = "id_submitbutton")
    @CacheLookup
    private WebElement submitBtn;

}
