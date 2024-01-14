package tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.RegisterPage;

public class RegisterTests extends BaseTest {
    RegisterPage registerPage;
    SoftAssert softAssert;


    @DataProvider(name = "TestWithValidCredentials")
    public Object[][] TestWithValidCredentials() {
        return new Object[][]{
                {"deskkodess05", "desskkkooo5@gmail.bg", "d3sk0l", "d3sk0l"}
        };
    }

    private void navigateToRegisterPage() {

        homePage.navigateToHomePage();

        LoginPage loginPage =  headerPage.clickLoginLink();
        loginPage.isUrlLoaded();
        //loginPage.verifyLoginFormIsVisible();
       // RegisterPage registerPage = loginPage.clickRegisterLink();
        registerPage.isUrlLoaded();
        registerPage.verifyRegisterFormIsVisible();
    }

    @Test(dataProvider = "TestWithValidCredentials")
    public void registerWithValidCredentialsTest(String username, String email, String password, String confirmPassword) {
        registerPage = new RegisterPage(driver);

        navigateToRegisterPage();

        registerPage.populateUsernameField(username);
        Assert.assertTrue(registerPage.isUsernameFieldValidSignDisplayed(), "The Username field sign is not correct!");

        registerPage.populateEmailField(email);
        Assert.assertTrue(registerPage.isEmailFieldValidSignDisplayed(), "The Email field sign is not correct!");

        registerPage.populatePasswordField(password);
        Assert.assertTrue(registerPage.isPasswordFieldValidSignDisplayed(), "The Password field sign is not correct!");

        registerPage.populateConfirmPasswordField(confirmPassword);
        Assert.assertTrue(registerPage.isConfirmPasswordFieldValidSignDisplayed(), "The Confirm Password field sign is not correct!");

        registerPage.clickSignInButton();
        Assert.assertEquals(registerPage.getToastMessage(), "Successful register!", "The toast message is not 'Successful register!'");

//        homePage.verifyForCorrectUrl()
//        ;
    }

    @DataProvider(name = "TestWithInvalidCredentials")
    public Object[][] TestWithInvalidCredentials() {
        return new Object[][]{
                {"dekoldeskoldes3", "deskole3#gmail.bg", "15952", "123123"}
        };
    }

    @Test(dataProvider = "TestWithInvalidCredentials")
    public void registerWithInvalidCredentialsTest(String username, String email, String password, String confirmPassword) {

        registerPage = new RegisterPage(driver);

        navigateToRegisterPage();

        registerPage.populateUsernameField(username);
        Assert.assertTrue(registerPage.isUsernameFieldValidSignDisplayed(), "The Username field sign is not correct!");


        registerPage.populateEmailField(email);
        Assert.assertTrue(registerPage.isEmailFieldInvalidSignDisplayed(), "The Email field sign is not correct!");
        Assert.assertEquals(registerPage.getEmailFieldFeedbackMessage(), "Email invalid!", "The Email field feedback message is not 'Email invalid!'");


        registerPage.populatePasswordField(password);
        Assert.assertTrue(registerPage.isPasswordFieldInvalidSignDisplayed(), "The Password field sign is not correct!");
        Assert.assertEquals(registerPage.getPasswordFieldFeedbackMessage(), "Minimum 6 characters !", "The Password field feedback message is not 'Minimum 6 characters !'");


        registerPage.populateConfirmPasswordField(confirmPassword);
        Assert.assertTrue(registerPage.isConfirmPasswordFieldInvalidSignDisplayed(), "The Confirm Password field sign is not correct!");
        Assert.assertEquals(registerPage.getConfirmPasswordFieldFeedbackMessage(), "Passwords do not match!", "The Confirm Password field feedback message is not 'Passwords do not match!'");

        registerPage.clickSignInButton();
        Assert.assertEquals(registerPage.getToastMessage(), "Registration failed!", "The toast message is not 'Registration failed!'");

        registerPage.verifyForCorrectUrl();
    }

}