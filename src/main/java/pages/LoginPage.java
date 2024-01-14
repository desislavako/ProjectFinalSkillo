package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage extends BasePage {
    public static final String LOGIN_URL = "http://training.skillo-bg.com:4200/users/login";
    private final WebDriver driver;
    @FindBy(css = "app-login form")
    private WebElement loginForm;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameField;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordField;

    @FindBy(id = "sign-in-button")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@href=\"/users/register\"]")
    private WebElement registerLink;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isLoginPageLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
    }

    public void enterUserName(String username) {usernameField.sendKeys(username);}

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignInButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public void verifyLoginFormIsVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForVisibilityOfElement(loginForm);
    }

    public void verifyForCorrectUrl() {
        waitUrlToBe(BASE_URL + "/posts/all");
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(LOGIN_URL));
    }

    public void navigateTo() {
        this.driver.get(LOGIN_URL);
    }

    public void logIn(String username, String password) {
        navigateTo();
        enterUserName(username);
        enterPassword(password);
        clickSignInButton();
    }
}



