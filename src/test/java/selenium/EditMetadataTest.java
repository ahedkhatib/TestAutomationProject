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
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
        //System.out.println("BeforeEach is running...");
        driver = getDriver();
        //driver.manage().window().maximize();
        driver.get("https://5f69-2a06-c701-7116-c00-1677-13ff-c635-10a9.ngrok-free.app/login");

        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
            System.out.println("Ngrok warning page bypassed.");
        } catch (TimeoutException err) {
            System.out.println("Ngrok warning page was not loaded");
        }
        //loginPage = new LoginPage(driver);
        //System.out.println("LoginPage initialized.");
    }

    @Test
    public void testEditTitleMetadata() {
        //System.out.println("Test is running...");
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifyTitleMetadata("New Title")
                .getMessage();

        String actualTitle = new VerifyUpdatedMetadataPage(driver)
                .getTitle();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("New Title", actualTitle);
    }

    /*@Test
    public void testEditAuthorMetadata() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifyAuthorMetadata("Ahed kh")
                .getMessage();

        String actualAuthor = new VerifyUpdatedMetadataPage(driver)
                .getAuthor();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("Ahed kh", actualAuthor);
    }

    @Test
    public void testEditTagMetadata() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifyTagMetadata("111111")
                .getMessage();

        String actualTag = new VerifyUpdatedMetadataPage(driver)
                .getTag();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("111111", actualTag);
    }

    @Test
    public void testEditSeriesMetadata() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifySeriesMetadata("3")
                .getMessage();

        String actualSeries = new VerifyUpdatedMetadataPage(driver)
                .getSeries();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("3", actualSeries);
    }

    @Test
    public void testEditSeriesIdMetadata() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifySeriesIdMetadata("5")
                .getMessage();

        String actualSeriesId = new VerifyUpdatedMetadataPage(driver)
                .getSeriesId();

        assertEquals("Metadata successfully updated", successMessage);
        assertTrue(actualSeriesId.contains("Book 5 of "));
    }

    @Test
    public void testEditBookRating() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifyRating(3)
                .getMessage();

        int actualRating = new VerifyUpdatedMetadataPage(driver)
                .getRating();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals(3, actualRating);
    }*/

    /*@Test
    public void testClearRating() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("7")
                .clickEditMetadata()
                .clearRating();

        assertEquals("Metadata successfully updated", successMessage);
    }*/

    /*@Test
    public void testEditPublishedDate() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifyPublishedDate("2023-10-05");

        assertEquals("Metadata successfully updated", successMessage);
    }

    @Test
    public void testEditPublisher() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("9")
                .clickEditMetadata()
                .modifyPublisher("BenBella Books, Inc.");

        String actualPublisher = new EditMetadataPage(driver)
                .getPublisher();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("BenBella Books, Inc.", actualPublisher);
    }

    @Test
    public void testEditLanguage() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("9")
                .clickEditMetadata()
                .modifyLanguage("English");

        String actualLanguage = new EditMetadataPage(driver)
                .getLanguage();

        assertEquals("Metadata successfully updated", successMessage);
        assertEquals("Language: English", actualLanguage);
    }*/

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
