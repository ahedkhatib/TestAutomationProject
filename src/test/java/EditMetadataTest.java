import org.example.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditMetadataTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8083");

        //loginPage = new LoginPage(driver);
    }

    @Test
    public void testEditTitleMetadata() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifyTitleMetadata("New Title");

        assertEquals("Metadata successfully updated", successMessage);
    }

    @Test
    public void testEditAuthorMetadata() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifyAuthorMetadata("Ahed kh");

        assertEquals("Metadata successfully updated", successMessage);
    }

    @Test
    public void testEditTagMetadata() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifyTagMetadata("111111");

        assertEquals("Metadata successfully updated", successMessage);
    }

    @Test
    public void testEditSeriesMetadata() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifySeriesMetadata("3");

        assertEquals("Metadata successfully updated", successMessage);
    }

    @Test
    public void testEditSeriesIdMetadata() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifySeriesIdMetadata("5");

        assertEquals("Metadata successfully updated", successMessage);
    }

    @Test
    public void testEditBookRating() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("10")
                .clickEditMetadata()
                .modifyRating(3);

        assertEquals("Metadata successfully updated", successMessage);
    }

    @Test
    public void testClearRating() {
        String successMessage = new LoginPage(driver)
                .loginAs("admin", "admin123")
                .openBookById("7")
                .clickEditMetadata()
                .clearRating();

        assertEquals("Metadata successfully updated", successMessage);
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
