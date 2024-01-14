package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ProfilePage extends BasePage {
    public static final String PROFILE_URL = "http://training.skillo-bg.com:4200/users/";
    private final WebDriver driver;

//    @FindBy(css = ".gallery-item")
//    private List<WebElement> listOfUsersPosts;
    By elementSelector = By.cssSelector(".gallery-item");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(PROFILE_URL));
    }
    public ModalPage openLatestPost() {
        List<WebElement> listOfUsersPosts = null;
        int latestPostIndex = listOfUsersPosts.size() - 1;

        try {
            listOfUsersPosts = getUserPosts();
            clickOnElement(listOfUsersPosts.stream().reduce((first, second) -> second).orElse(null));
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("The User does NOT have any Public Posts!");
            throw new IndexOutOfBoundsException();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ModalPage(driver);
    }
    public List<WebElement> getUserPosts() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementSelector));
    }

    public int getUsersPostsCount() {
        return getUserPosts().size();
    }

}

