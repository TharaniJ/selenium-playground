package tj.selenium.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import javax.xml.bind.Element;
import javax.xml.xpath.XPath;

public class MoodleHomePage {

    @FindBy(xpath = "//*[@id='page-wrapper']/nav//a[text()='Log in']")
    @CacheLookup
    private WebElement loginLink;
    public WebElement getLoginLink() {
        return loginLink;
    }

}
