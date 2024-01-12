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
        headerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginFormIsVisible();
        loginPage.logIn(username, password);
        homePage.verifyForCorrectUrl();
        headerPage.clickProfileLink();
    }

    @Test(dataProvider = "credentialsInfo") ///da se pogledne
    public void likePostAsLoggedInUserTest(String username, String password) {

        profilePage = new ProfilePage(driver);

        navigateToProfilePage(username, password);
        ModalPage modalPage = profilePage.openLatestPost();
        modalPage.waitForVisibilityOfModal();
        int initialLikesCount = modalPage.getLikesCount();
        modalPage.likePost();


        Assert.assertTrue(modalPage.isHeartIconLiked(), "The Heart Icon is not liked!");
      //  Assert.assertEquals(modalPage.getLikesCount(), initialLikesCount + 1, "The likes count hasn't increased by 1!");
        Assert.assertEquals(modalPage.getLikedPostToastMessage(), "Post liked");
    }

    @Test(dataProvider = "credentialsInfo")
    public void dislikePostAsLoggedInUserTest(String username, String password) {

        profilePage = new ProfilePage(driver);
        navigateToProfilePage(username, password);

        ModalPage modalPage = profilePage.openLatestPost();
        modalPage.waitForVisibilityOfModal();
        int initialDislikesCount = modalPage.getDislikesCount();
        modalPage.dislikePost();

        Assert.assertTrue(modalPage.isThumbsDownIconLiked(), "The Thumbs Down Icon is not liked!");
        Assert.assertEquals(modalPage.getDislikesCount(), initialDislikesCount + 1, "The dislikes count hasn't increased by 1!");
        Assert.assertEquals(modalPage.getDislikedPostToastMessage(), "Post disliked", "The toast message is 'Post disliked'");
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
        modalPage.waitForVisibilityOfModal();
        modalPage.addComment(comment);

        Assert.assertTrue(modalPage.isCommentPosted(comment), "shashava rabota");
    }

    @Test(dataProvider = "credentialsInfo")
    public void deletePostTest(String username, String password) {

        profilePage = new ProfilePage(driver);
        navigateToProfilePage(username, password);
        int initialPostsCount = profilePage.getUsersPostsCount();

        ModalPage modalPage = profilePage.openLatestPost();
        modalPage.waitForVisibilityOfModal();
        modalPage.deletePost();

        Assert.assertEquals(modalPage.getDeletedPostToastMessage(), "Post Deleted!", "The toast message is not 'Post Deleted!'");

        int afterDeletionPostsCount = profilePage.getUsersPostsCount();
        Assert.assertEquals(afterDeletionPostsCount, initialPostsCount - 1, "The Public Posts count hasn't decreased by 1!");
    }
}