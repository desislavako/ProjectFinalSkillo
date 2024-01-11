package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

public class LoginPageTests extends BaseTest {


    @DataProvider(name = "getUsers")
    public Object[][] Users() {
        return new Object[][]{
                {"deskoldes", "d3sk0l", "deskoldes"},
        };
    }

    @Test(dataProvider = "Users")
    public void loginTest(String username, String password) {

        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();

        HeaderPage header = new HeaderPage(driver);
        header.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyForCorrectUrl();
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        loginPage.clickSignInButton();

        homePage.verifyForCorrectUrl();


    }
}