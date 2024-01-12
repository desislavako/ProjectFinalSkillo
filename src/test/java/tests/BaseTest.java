package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.HeaderPage;
import pages.HomePage;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.nio.file.Path.*;

public class BaseTest {
    private static final String SCREENSHOT_DIR = "src\\test\\java\\resources\\screenshots\\";
    protected WebDriver driver;
    protected HomePage homePage;
    protected HeaderPage headerPage;
    protected NewPostTests newPostTests;


    @BeforeSuite
    public void cleanDirectory() {
        cleanDirectory(SCREENSHOT_DIR);
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20).getSeconds(), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5).getSeconds(), TimeUnit.SECONDS);

        navigateToLoginPage();

        homePage = new HomePage(driver);
        headerPage = new HeaderPage(driver);
        LoginPage loginPage = new LoginPage(driver);
    }

    private void navigateToLoginPage() {
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        takeScreenshot(testResult);
        driver.close();
    }

    private void takeScreenshot(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName().concat(getCurrentTime());
            try {
                FileUtils.copyFile(screenshot, new File(SCREENSHOT_DIR.concat(testName).concat(".jpeg")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void cleanDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        try {
            Path screenshotsDir = of(directoryPath);
            if (!Files.exists(screenshotsDir)) {
                Files.createDirectories(screenshotsDir);
            }

            FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'_'dd-MM-yyyy'_'HH.mm");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return simpleDateFormat.format(timestamp);
    }


    protected WebDriver getDriver() {
        return driver;
    }
}