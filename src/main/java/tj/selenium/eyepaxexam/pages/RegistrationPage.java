package tj.selenium.eyepaxexam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

    @FindBy(id = "customer_firstname")
    @CacheLookup
    private WebElement customerFirstName;

    @FindBy(id = "customer_lastname")
    @CacheLookup
    private WebElement customerLastName;

    @FindBy(id = "passwd")
    @CacheLookup
    private WebElement password;

    @FindBy(id = "firstname")
    @CacheLookup
    private WebElement firstName;

    @FindBy(id = "lastname")
    @CacheLookup
    private WebElement lastName;

    @FindBy(id = "address1")
    @CacheLookup
    private WebElement address;

    @FindBy(id = "city")
    @CacheLookup
    private WebElement city;

    @FindBy(id = "id_state")
    @CacheLookup
    private WebElement state;

    @FindBy(id = "postcode")
    @CacheLookup
    private WebElement postcode;

    @FindBy(id = "id_country")
    @CacheLookup
    private WebElement country;

    @FindBy(id = "phone_mobile")
    @CacheLookup
    private WebElement mobileNo;

    @FindBy(id = "alias")
    @CacheLookup
    private WebElement futureReference;

    @FindBy(id = "submitAccount")
    @CacheLookup
    private WebElement SubmitAccount;

    public WebElement getCustomerFirstName() {
        return customerFirstName;
    }

    public WebElement getCustomerLastName() {
        return customerLastName;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getFirstName() {
        return firstName;
    }

    public WebElement getLastName() {
        return lastName;
    }

    public WebElement getAddress() {
        return address;
    }

    public WebElement getCity() {
        return city;
    }

    public Select getState() {
        Select stateDropDown = new Select(state);
        return stateDropDown;
    }

    public WebElement getPostcode() {
        return postcode;
    }

    public Select getCountry() {
        Select CountryDropDown = new Select(country);
        return CountryDropDown;
    }

    public WebElement getMobileNo() {
        return mobileNo;
    }

    public WebElement getFutureReference() {
        return futureReference;
    }

    public WebElement getSubmitAccount() {
        return SubmitAccount;
    }
}
