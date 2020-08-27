package tj.selenium.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MoodleLogoutPage {

    @FindBy(id = "action-menu-toggle-1")
    @CacheLookup
    private WebElement logoutLink1;
    public WebElement getLogoutLink1() {
        return logoutLink1;
    }

    @FindBy(id = "actionmenuaction-6")
    @CacheLookup
    private WebElement logoutLink2;

    public WebElement getLogoutLink2() {
        return logoutLink2;
    }

    @FindBy(id="actionmenuaction-2")
    @CacheLookup
    private WebElement profileLink;
    public WebElement getProfileLink() {
        return profileLink;
    }

}
