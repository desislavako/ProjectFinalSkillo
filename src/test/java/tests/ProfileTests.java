package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ModalPage;
import pages.ProfilePage;

public class ProfileTests extends BaseTest {
    private ProfilePage profilePage;

    @DataProvider(name = "credentialsInfo")
    private Object[][] credentialsInfo() {
        return new Object[][]{
                {"deskoldes", "d3sk0l"}
        };
    }

    private void navigateToProfilePage(String username, String password) {
        homePage.navigateToHomePage();

        LoginPage loginPage =  headerPage.clickLoginLink();
        loginPage.isUrlLoaded();

        loginPage.logIn(username, password);
        homePage.verifyForCorrectUrl();
        headerPage.clickProfileLink();
    }

    @Test(dataProvider = "credentialsInfo")
    public void likePostAsLoggedInUserTest(String username, String password) {

        profilePage = new ProfilePage(driver);

        navigateToProfilePage(username, password);
        ModalPage modalPage = profilePage.openLatestPost();
        modalPage.waitForVisibilityOfElement();
        int initialLikesCount = modalPage.getLikesCount();
        modalPage.likePost();


        Assert.assertTrue(modalPage.isHeartIconLiked(), "The Heart Icon is not liked!");
        Assert.assertEquals(modalPage.getLikesCount(), initialLikesCount + 1, "The likes count hasn't increased by 1!");
        Assert.assertEquals(modalPage.getLikedPostToastMessage(), "Post liked");
    }



    @DataProvider(name = "dataForWriteCommentTest")
    private Object[][] dataForWriteCommentTest() {
        return new Object[][]{
                {"deskoldes", "d3sk0l", "shashava rabota"}
        };
    }

    @Test(dataProvider = "dataForWriteCommentTest")
    public void writeCommentTest(String username, String password, String comment) {

        profilePage = new ProfilePage(driver);
        navigateToProfilePage(username, password);

        ModalPage modalPage = profilePage.openLatestPost();
        modalPage.waitForVisibilityOfElement();
        modalPage.addComment(comment);

        Assert.assertTrue(modalPage.isCommentPosted(comment), "shashava rabota");
    }
}
