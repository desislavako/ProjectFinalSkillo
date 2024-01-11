package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private final String LOGIN_URL = BASE_URL + "/users/login";

    @FindBy(css = "app-login form")
    private WebElement loginForm;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameField;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordField;

    @FindBy(id = "sign-in-button")
    private WebElement signInButton;

    @FindBy(linkText = "Register")
    private WebElement registerLink;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateTo(){
        this.driver.get(LOGIN_URL);
    }
    public void logIn(String username, String password) {
        typeInField(usernameField, username);
        typeInField(passwordField, password);
        clickOnElement(signInButton);
        HeaderPage headerPage = new HeaderPage(driver);
    }

    public void enterUserName(String username) {
        enterUserName(usernameField,username);
    }

    private void enterUserName(WebElement usernameField, String username) {

    }

    public void verifyLoginFormIsVisible() {
        waitForVisibilityOfElement(loginForm);
    }

    public void verifyForCorrectUrl() {
        waitUrlToBe(BASE_URL + "/posts/all");
    }

    public RegisterPage clickRegisterLink() {
        clickOnElement(registerLink);
        return new RegisterPage(driver);
    }

    public WebElement clickSignInButton() {
        waitForVisibilityOfElement(signInButton);
        return signInButton;
    }
    public void enterPassword(String password) {}

}


