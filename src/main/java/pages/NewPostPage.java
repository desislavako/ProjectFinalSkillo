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

//    @FindBy(css = ".image-preview")
//    private WebElement imagePreview;
//
//    @FindBy(css = ".input-group input")
//    private WebElement imageNameInfo;

    @FindBy(name = "caption")
    WebElement postCaptionInput;

//    @FindBy (xpath = "//label[@for=\"customSwitch2\"]")
//    private WebElement accessStatus;
    @FindBy(id = "create-post")
    private WebElement clickCreatePostButton;

//    @FindBy (className= "public-status-label")
//    private WebElement privateLabel;

    //@FindBy(css = "input[name='coverUrl']")
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


    public void clickCreatePostButton(){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(clickCreatePostButton));
        clickCreatePostButton.submit();
    }


    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    public NewPostPage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }
//
//    public NewPostPage() {
//
//    }
//
//    public void enterCaption(String caption) {
//        captionField.sendKeys(caption);
//    }
//
//    public ProfilePage clickSubmitButton() {
//        clickOnElement(clickCreatePostButton);
//        return new ProfilePage(driver);
//    }
//
//
//
//    public void uploadFile(File file) {
////        File imagePath = (File) fileInput;
////        fileInput.sendKeys(imagePath.getAbsolutePath());
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Adjust the timeout as needed
//        fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class=\"ng-untouched ng-pristine ng-invalid\"]")));
//
//        fileInput.sendKeys(file.getAbsolutePath());
//    }
//
//    public void verifyImageIsVisible() {
//    }
//
////    WebElement getFileInput = driver.findElement(By.cssSelector("input[name='coverUrl']"));
//}
//
//
}