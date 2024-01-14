package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    private static final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";
    private final WebDriver driver;

    @FindBy(css = ".post-feed-img")
    private WebElement latestPost;

    @FindBy(css = ".toast-message")
    private WebElement loginToastMessage;

    @FindBy(id = "nav-link-profile")
    WebElement profileLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {

        driver.get(HOME_URL);
    }

    public void verifyForCorrectUrl() {
     waitUrlToBe(HOME_URL);
    }
    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(HOME_URL));
    }
    public void clickProfileLink(){
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(profileLink));
        profileLink.click();
    }
    public String getToastMessage() {
        return getToastMessage(loginToastMessage);
    }

    public ModalPage openLatestPost() {
        clickOnElement(latestPost);
        return new ModalPage(driver);
    }

    public void navigateTo() {
        this.driver.get(HOME_URL);
    }
}