package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPage extends BasePage {

    @FindBy(id = "nav-link-login")
    private WebElement loginLink;

    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;

    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;

    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickLoginLink() {
        clickOnElement(loginLink);
        return new LoginPage(driver);
    }

    public void clickNewPostLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(newPostLink));
        newPostLink.click();
    }

    public ProfilePage clickProfileLink() {
        clickOnElement(profileLink);
        return new ProfilePage(driver);
    }

    public void openLoginPage() {
        clickOnElement(loginLink);
    }

    public void openProfilePage() {
    }
}
