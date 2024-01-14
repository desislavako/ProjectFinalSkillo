package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.time.Duration;

public class NewPostPage extends BasePage {
    public static final String NEW_POST_URL = "http://training.skillo-bg.com:4200/posts/create";

    private final WebDriver driver;


    @FindBy(name = "caption")
    WebElement postCaptionInput;


    @FindBy(id = "create-post")
    private WebElement clickCreatePostButton;


    @FindBy(xpath = "//input[@class=\"ng-untouched ng-pristine ng-invalid\"]")
    private WebElement fileInput;


    public NewPostPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean newPostUrl() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        return wait.until(ExpectedConditions.urlToBe(NEW_POST_URL));
    }

    public void uploadFile(File file) {
        fileInput.sendKeys(file.getAbsolutePath());
    }

    public void fillInCaption(String postCaption) {
        postCaptionInput.sendKeys(postCaption);
    }


    public void clickCreatePostButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(clickCreatePostButton));
        clickCreatePostButton.submit();
    }

}