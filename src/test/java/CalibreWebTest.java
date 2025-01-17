import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CalibreWebTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
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
    }

    /*@Test
    public void testLoginSuccessMessage() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .getSuccessMessage();

        assertTrue(successMessage.contains("logged in as"));
    }*/

    /*@Test
    public void testBookDetailsAndDownload() throws InterruptedException {

        //note :  if exists then delete And to download the book to this folder (maybe hash)
        System.out.println("Download directory set to: " + System.getProperty("user.dir"));

        String downloadDir = System.getProperty("user.dir"); // Refers to the project root directory
        String expectedFileName = "New Title - Ahed.pdf";

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
        driver.quit();
    }
}
