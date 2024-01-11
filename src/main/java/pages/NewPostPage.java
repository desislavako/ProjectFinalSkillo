package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class NewPostPage extends BasePage {
    private final String NEW_POST_URL = BASE_URL + "/posts/create";
    private WebDriver driver;
    @FindBy(css = ".image-preview")
    private WebElement imagePreview;

    @FindBy(css = ".input-group input")
    private WebElement imageNameInfo;

    @FindBy(css = "input[name='caption']")
    private WebElement captionField;

    @FindBy(id = "create-post")
    private WebElement submitButton;

    @FindBy(css = "input[name='coverUrl']")
    private WebElement fileInput;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    public NewPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterCaption(String caption) {
        captionField.sendKeys(caption);
    }

    public ProfilePage clickSubmitButton() {
        clickOnElement(submitButton);
        return new ProfilePage(driver);
    }


    public void uploadFile(File file) {
        File imagePath = (File) fileInput;
        fileInput.sendKeys(imagePath.getAbsolutePath());
    }

    public void verifyImageIsVisible() {
    }

    WebElement getFileInput = driver.findElement(By.cssSelector("input[name='coverUrl']"));
}


