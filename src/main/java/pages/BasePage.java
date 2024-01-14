package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasePage {
    protected final String BASE_URL = "http://training.skillo-bg.com:4200/post/all";
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public BasePage() {
    }

    protected void clickOnElement(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    protected void typeInField(WebElement webElement, String input) {
        waitForVisibilityOfElement(webElement);
        webElement.sendKeys(input);
    }

    protected void waitForVisibilityOfElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitUrlToBe(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    protected String getToastMessage(WebElement webElement) {
        waitForVisibilityOfElement(webElement);
        return webElement.getText();
    }

    public void verifyForCorrectUrl() {
        waitUrlToBe(BASE_URL);
    }
}