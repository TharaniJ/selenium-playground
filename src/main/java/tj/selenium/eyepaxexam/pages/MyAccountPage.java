package tj.selenium.eyepaxexam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage {
    @FindBy(xpath = "//body/div[@id='page']/div[2]/div[1]/div[1]/span[2]")
    @CacheLookup
    private WebElement myAccount;

    public WebElement getMyAccount() {
        return myAccount;
    }
    @FindBy(xpath = "//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/ul[1]/li[1]/a[1]/span[1]")
    @CacheLookup
    private WebElement myWishList;

    public WebElement getMyWishList() {
        return myWishList;
    }
}
