package tj.selenium.eyepaxexam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class AuthenticationPage {

    @FindBy(id = "email_create")
    @CacheLookup
    private WebElement emailTextBox;

    @FindBy(id = "SubmitCreate")
    @CacheLookup
    private WebElement createAnAccount;

    public WebElement getEmailTextBox() {
        return emailTextBox;
    }

    public WebElement getCreateAnAccount() {
        return createAnAccount;
    }
}
