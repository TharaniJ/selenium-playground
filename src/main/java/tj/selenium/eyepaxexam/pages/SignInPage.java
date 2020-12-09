package tj.selenium.eyepaxexam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class SignInPage {

    @FindBy(xpath = "//header/div[2]/div[1]/div[1]/nav[1]/div[1]/a[@title=\"Log in to your customer account\"]")
    @CacheLookup
    private WebElement signIn;

    public WebElement getSignIn() {
        return signIn;
    }


    @FindBy(id = "email")
    @CacheLookup
    private WebElement emailAddress;

    @FindBy(id = "passwd")
    @CacheLookup
    private WebElement password;


    @FindBy(id = "SubmitLogin")
    @CacheLookup
    private WebElement logIn;

    public WebElement getEmailAddress() {
        return emailAddress;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getLogIn() {
        return logIn;
    }
}
