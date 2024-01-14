package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

public class LoginPageTests extends BaseTest {


    @DataProvider(name = "Users")
    public Object[][] Users() {
        return new Object[][]{
                {"deskoldes", "d3sk0l"},
        };
    }

    @Test(dataProvider = "Users")
    public void loginTest(String username, String password) {

        HomePage homePage = new HomePage(super.getDriver());
        homePage.navigateTo();
        homePage.isUrlLoaded();

        HeaderPage headerMenu = new HeaderPage(driver);
        headerMenu.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.isUrlLoaded();
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();

        headerMenu.clickProfileLink();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.isUrlLoaded();





    }
}