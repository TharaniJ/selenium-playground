package tj.selenium.eyepaxexam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class MyWishListPage {
    @FindBy(xpath = "//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/ul[1]/li[1]/a[1]/img[1]")
    @CacheLookup
    private WebElement myWishList;


    @FindBy(id = "add_to_cart")
    @CacheLookup
    private WebElement addToCard;

    @FindBy(xpath = "//@id=\"layer_cart\"]/div[1]/div[1]/span[@Title='Close window']")
    @CacheLookup
    private WebElement closeWindow;


    @FindBy(id = "button_order_cart")
    @CacheLookup
    private WebElement checkOut;
    //*[@id="layer_cart"]/div[1]/div[2]/div[4]/a

    @FindBy(xpath = "//header/div[3]/div[1]/div[1]/div[4]/div[1]/div[2]/div[4]/a[1]/span[1]/i[1]")
    @CacheLookup
    private WebElement proceedToCheckOut;

    public WebElement getCardTitle() {
        return cardTitle;
    }

    @FindBy(xpath = "//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/ul[1]/li[1]/span[1]")
    @CacheLookup
    private WebElement cardTitle;


    public WebElement getMyWishList() {
        return myWishList;
    }

    public WebElement getAddToCard() {
        return addToCard;
    }

    public WebElement getCloseWindow() {
        return closeWindow;
    }

    public WebElement getCheckOut() {
        return checkOut;
    }

    public WebElement getProceedToCheckOut() {
        return proceedToCheckOut;
    }
}
