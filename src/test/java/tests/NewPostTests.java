package tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import pages.*;

import java.io.File;

public class NewPostTests extends BaseTest {


    protected static final String BASE_URL = "http://training.skillo-bg.com:4200";
    private static final String NEW_POST_URL = BASE_URL + "/posts/create";


    @DataProvider(name = "uploadPicture")
    private Object[][] uploadPicture() {
        File uploadPicture = new File("src\\main\\resources\\upload\\pizza.jpeg");
        String caption = "Some pizza";

        return new Object[][]{
                {"deskoldes", "d3sk0l", uploadPicture, caption}
        };
    }

    @Test(dataProvider = "uploadPicture")
    public void uploadPictureTest(String username, String password, File file, String caption) {

        WebDriver driver = super.getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.logIn(username, password);

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.clickNewPostLink();

        NewPostPage newPostPage = new NewPostPage(driver);
        newPostPage.uploadFile(file);

        newPostPage.enterCaption(caption);
        newPostPage.clickSubmitButton();
    }
}
