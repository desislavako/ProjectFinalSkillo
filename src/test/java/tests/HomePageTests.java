package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ModalPage;

public class HomePageTests extends BaseTest {
    @Test
    public void likePostAsLoggedOutUserTest() {
        openLatestPost();

        ModalPage modalPage = new ModalPage(driver);
        modalPage.likePost();
        Assert.assertEquals(homePage.getToastMessage(), "You must login", "The toast message is 'You must login'");


        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginFormIsVisible();
        loginPage.verifyForCorrectUrl();
    }

    @Test
    public void dislikePostAsLoggedOutUserTest() {

        openLatestPost();

        ModalPage modalPage = new ModalPage(driver);
        modalPage.dislikePost();
        Assert.assertEquals(homePage.getToastMessage(), "You must login", "The toast message is not 'You must login'");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginFormIsVisible();
        loginPage.verifyForCorrectUrl();
    }

    private void openLatestPost() {

        homePage.navigateToHomePage();

        ModalPage modalPage = homePage.openLatestPost();
        modalPage.waitForVisibilityOfModal();
    }
}