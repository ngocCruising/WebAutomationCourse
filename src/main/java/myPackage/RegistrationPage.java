package myPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by ntran on 11/10/15.
 */
public class RegistrationPage {

    WebDriver webDriver;
    @FindBy(linkText="register")
    WebElement registerLink;
    @FindBy(name="email")
    WebElement emailField;
    @FindBy(name="password")
    WebElement passwordField;
    @FindBy(name="birthYear")
    WebElement birthYearField;
    @FindBy(name="zipCode")
    WebElement zipCodeField;
    @FindBy(css="input[value='MALE']")
    WebElement genderMaleSelector;
    @FindBy(name="agreeTermsOfUse")
    WebElement agreeTermsOfUseCheckBox;
    @FindBy(css="input[value='Register']")
    WebElement registerAccountButton;
    @FindBy(linkText="Continue")
    WebElement continueLink;
    @FindBy(className="lightbox_header")
    WebElement congratulationsText;

    public RegistrationPage(WebDriver driver) {
        this.webDriver = driver;

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public void clickRegisterLink() {
        registerLink.click();
    }

    public WebElement getEmailField() {
        return emailField;
    }

    private void setEmailField(String email) {
        emailField.sendKeys(email);
    }

    private void setPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    private void setBirthYearField(String birthYear) {
        birthYearField.sendKeys(birthYear);
    }

    private void setZipCodeField(String zipCode) {
        zipCodeField.sendKeys(zipCode);
    }

    private void selectGenderMale() {
        genderMaleSelector.click();
    }

    private void clickTermsOfUse() {
        agreeTermsOfUseCheckBox.click();
    }

    private void clickRegisterAccount() {
        registerAccountButton.click();
    }

    public WebElement getContinueLink() {
        return continueLink;
    }

    public void clickContinueLink() {
        continueLink.click();
    }

    public String getCongratsText() {
        return congratulationsText.getText();
    }

    public void fillInUserRegistrationDetails(String email, String password, String birthYear, String zipCode) {
        setEmailField(email);
        setPasswordField(password);
        setBirthYearField(birthYear);
        setZipCodeField(zipCode);
        selectGenderMale();
        clickTermsOfUse();
        clickRegisterAccount();
    }

}
