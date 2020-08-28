package tj.selenium.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MoodleProfilePage {

    // TODO enhance the xpath
    @FindBy(xpath = "//*[@id=\"region-main\"]/div/div/div[2]/section[1]/div/ul/li[1]/span/a")
    @CacheLookup
    private WebElement editProfileLink;
    public WebElement getEditProfileLink() {
        return editProfileLink;
    }


}
