package tj.selenium.pom.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;


public class MoodleHomePage {

    @FindBy(xpath = "//*[@id='page-wrapper']/nav//a[text()='Log in']")
    @CacheLookup
    private WebElement loginLink;

    @FindBy(xpath = "//div[@class='search-input-wrapper nav-link']")
    @CacheLookup
    private WebElement searchIconButton;

    @FindBy(name = "q")
    private WebElement dynamicSearchTextBox;


    public WebElement getSearchIconButton() { return searchIconButton; }

    public WebElement getDynamicSearchTextBox() { return dynamicSearchTextBox; }

    public WebElement getLoginLink() {
        return loginLink;
    }


}
