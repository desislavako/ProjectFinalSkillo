package tests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import pages.*;

import java.io.File;

public class NewPostTests extends BaseTest {

    @DataProvider(name = "uploadPicture")
    private Object[][] uploadPicture() {
        File uploadPicture = new File("src\\main\\resources\\upload\\pizza.jpg");
        String caption = "Some pizza";

        return new Object[][]{
                {"deskoldes", "d3sk0l", uploadPicture, caption}
        };
    }

    @Test(dataProvider = "uploadPicture")
    public void uploadPictureTest(String username, String password, File file, String caption) {

        LoginPage loginPage = new LoginPage(super.getDriver());
        loginPage.logIn(username, password);

        HomePage homePage = new HomePage(super.getDriver());

        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.clickNewPostLink();

        NewPostPage newPostPage = new NewPostPage(driver);
        newPostPage.uploadFile(file);

        newPostPage.fillInCaption(caption);
        newPostPage.clickCreatePostButton();

//        ProfilePage profilePage = new ProfilePage(super.getDriver());
//        profilePage.openLatestPost();

    }
}
