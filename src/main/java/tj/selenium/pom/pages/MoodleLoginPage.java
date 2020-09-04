package tj.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MoodleLoginPage {
    @FindBy(id="username")
    @CacheLookup
    private WebElement usernameTextBox;
    public WebElement getUsernameTextBox() {
        return usernameTextBox;
    }

    @FindBy(id="password")
    @CacheLookup
    private WebElement pwdTextBox;
    public WebElement getPwdTextBox() {
        return pwdTextBox;
    }

    @FindBy(id="loginbtn")
    @CacheLookup
    private WebElement loginButton;
    public WebElement getLoginButton() {
        return loginButton;
    }

    @FindBy(id = "loginerrormessage")
    @CacheLookup
    private WebElement loginErrorMessage;
    public WebElement getLoginErrorMessage() { return loginErrorMessage; }

}
