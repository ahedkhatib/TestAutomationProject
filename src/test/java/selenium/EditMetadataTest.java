package selenium;

import org.example.EditMetadataPage;
import org.example.LoginPage;
import org.example.VerifyUpdatedMetadataPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.DriverFactory.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditMetadataTest {
    private static final String BASE_URL = "https://f48f-2a06-c701-9fc9-5f00-83c-2461-70ac-e2c6.ngrok-free.app/login";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";
    private static final int WAIT_TIMEOUT = 5;

    private WebDriver driver;



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

    private EditMetadataPage loginAndEditMetadata(String bookId) {
        return new LoginPage(driver)
                .loginAs(USERNAME, PASSWORD)
                .openBookById(bookId)
                .clickEditMetadata();
    }

    @Test
    public void testEditTitleMetadata() {
        String successMessage = loginAndEditMetadata("10")
                .modifyTitleMetadata("New Title")
                .getMessage();

        String actualTitle = new VerifyUpdatedMetadataPage(driver)
                .getTitle();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("New Title", actualTitle);
    }

    @Test
    public void testEditAuthorMetadata() {
        String successMessage = loginAndEditMetadata("10")
                .modifyAuthorMetadata("Ahed kh")
                .getMessage();

        String actualAuthor = new VerifyUpdatedMetadataPage(driver)
                .getAuthor();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("Ahed kh", actualAuthor);
    }

    @Test
    public void testEditTagMetadata() {
        String successMessage = loginAndEditMetadata("10")
                .modifyTagMetadata("111111")
                .getMessage();

        String actualTag = new VerifyUpdatedMetadataPage(driver)
                .getTag();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("111111", actualTag);
    }

    @Test
    public void testEditSeriesMetadata() {
        String successMessage = loginAndEditMetadata("10")
                .modifySeriesMetadata("3")
                .getMessage();

        String actualSeries = new VerifyUpdatedMetadataPage(driver)
                .getSeries();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("3", actualSeries);
    }

    @Test
    public void testEditSeriesIdMetadata() {
        String successMessage = loginAndEditMetadata("10")
                .modifySeriesIdMetadata("5")
                .getMessage();

        String actualSeriesId = new VerifyUpdatedMetadataPage(driver)
                .getSeriesId();

        assertEquals("Metadata successfully updated", successMessage);
        assertTrue(actualSeriesId.contains("Book 5 of "));
    }

    @Test
    public void testEditBookRating() {
        String successMessage = loginAndEditMetadata("10")
                .modifyRating(3)
                .getMessage();

        int actualRating = new VerifyUpdatedMetadataPage(driver)
                .getRating();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals(3, actualRating);
    }

    /*@Test
    public void testClearRating() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("7")
                .clickEditMetadata()
                .clearRating();

        assertEquals("Metadata successfully updated", successMessage);
    }*/

    @Test
    public void testEditPublishedDate() {
        String successMessage = loginAndEditMetadata("10")
                .modifyPublishedDate("2023-10-05")
                .getMessage();

        String actualDate = new VerifyUpdatedMetadataPage(driver)
                .getDate();

        assertEquals("Metadata successfully updated", successMessage);
        assertTrue(actualDate.contains("Oct 5, 2023"));
    }

    @Test
    public void testEditPublisher() {
        String successMessage = loginAndEditMetadata("9")
                .modifyPublisher("BenBella Books, Inc.")
                .getMessage();

        String actualPublisher = new VerifyUpdatedMetadataPage(driver)
                .getPublishers();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("BenBella Books, Inc.", actualPublisher);
    }

    @Test
    public void testEditLanguage() {
        String successMessage = loginAndEditMetadata("9")
                .modifyLanguage("English")
                .getMessage();

        String actualLanguage = new VerifyUpdatedMetadataPage(driver)
                .getLanguage();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("Language: English", actualLanguage);
    }

    @Test
    public void testDeletePublishedDate() {
        String successMessage = loginAndEditMetadata("9")
                .deletePublishedDate()
                .getMessage();

        assertEquals("Metadata successfully updated", successMessage);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
