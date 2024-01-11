package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ModalPage;

public class HomePageTests extends BaseTest {

    private void openLatestPost() {

        homePage.navigateToHomePage();

        ModalPage modalPage = homePage.openLatestPost();
        modalPage.waitForVisibilityOfModal();
    }
    @Test
    public void likePostAsLoggedOutUserTest() {  //da se pogledne
        openLatestPost();

        ModalPage modalPage = new ModalPage(driver);
        modalPage.likePost();
        Assert.assertEquals(homePage.getToastMessage(), "You must login", "The toast message is not 'You must login'");


        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginFormIsVisible();
        loginPage.verifyForCorrectUrl();
    }

    @Test
    public void dislikePostAsLoggedOutUserTest() { //da se pogledne

        openLatestPost();

        ModalPage modalPage = new ModalPage(driver);
        modalPage.dislikePost();
        Assert.assertEquals(homePage.getToastMessage(), "You must login", "The toast message is not 'You must login'");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyLoginFormIsVisible();
        loginPage.verifyForCorrectUrl();
    }


}