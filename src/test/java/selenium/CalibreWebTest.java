package selenium;

import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.example.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.*;

public class CalibreWebTest {
    private static final String BASE_URL = "https://5f69-2a06-c701-7116-c00-1677-13ff-c635-10a9.ngrok-free.app/login";
    private static final int WAIT_TIMEOUT = 5;

    private WebDriver driver;
    private LoginPage loginPage;

    /*@BeforeEach
    public void setUp() {

        // Get the project directory path
        String projectDir = System.getProperty("user.dir");

        ChromeOptions options = new ChromeOptions();
        // Configure Chrome to download files to the project directory
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", projectDir); // Set download directory to project folder
        prefs.put("download.prompt_for_download", false);    // Suppress download dialog
        prefs.put("profile.default_content_settings.popups", 0);      // Automatically overwrite existing files


        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://localhost:8083");

        //loginPage = new LoginPage(driver);
    }*/

    @BeforeEach
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
        } catch (TimeoutException err) {
            System.out.println("Ngrok warning page was not loaded");
        }
    }


    /*@Test
    public void testBookDetailsAndDownload() throws InterruptedException {

        //note :  if exists then delete And to download the book to this folder (maybe hash)
        System.out.println("Download directory set to: " + System.getProperty("user.dir"));

        String downloadDir = System.getProperty("user.dir"); // Refers to the project root directory
        String expectedFileName = "New Title - Ahed kh.pdf";

        // Ensure the file does not already exist in the project directory
        File downloadedFile = new File(downloadDir, expectedFileName);
        if (downloadedFile.exists()) { //tear
            boolean isDeleted = downloadedFile.delete();
            assertTrue(isDeleted, "Existing file could not be deleted before test.");
        }


        boolean isDownloaded = new LoginPage(driver)
                .loginAs("admin", "admin123") //before each
                .openBookById("10")
                .clickDownloadButton(downloadDir, expectedFileName);

        assertTrue(isDownloaded);
    }*/


    @Test
    public void testReadInBrowser() {
        boolean isReaderOpened = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickReadInBrowser()
                .isReaderPageOpened();

        //check title
        assertTrue(isReaderOpened);
    }

    @Test
    public void testMarkBookAsRead() {
        boolean isMarked = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .markAsRead()
                .openBookById("10")
                .isMarkedAsRead();


        assertTrue(isMarked);
    }

    @Test
    public void testIsbnPage() {
        boolean isCorrectIsbn = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("8")
                .clickIsbnButton()
                .isCorrectIsbnPage("worldcat.org");

        assertTrue(isCorrectIsbn);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Attempting to quit the driver...");
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Driver quit successfully.");
            } catch (Exception e) {
                System.out.println("Failed to quit the driver: " + e.getMessage());
            }
        }
        //driver.quit();
    }
}

