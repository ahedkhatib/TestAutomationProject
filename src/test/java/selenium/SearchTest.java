package selenium;

import org.example.LoginPage;
import org.example.SearchResult;
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

public class SearchTest {
    private static final String BASE_URL = "https://a050-2a06-c701-7116-c00-1677-13ff-c635-10a9.ngrok-free.app/login";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";
    private static final int WAIT_TIMEOUT = 5;

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        //driver.get("http://localhost:8083");

        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
        } catch (TimeoutException err) {
            System.out.println("Ngrok warning page was not loaded");
        }
    }

    private boolean performSearchTest(String bookTitle) {
        boolean isSearchSuccessful = new LoginPage(driver)
                .loginAs(USERNAME, PASSWORD)
                .searchForBook(bookTitle)
                .isExists(bookTitle);

        return isSearchSuccessful;
    }

    @Test
    public void testSearchExistingBook() {
        String bookTitle = "New Title";
        boolean isSearchSuccessful = performSearchTest(bookTitle);

        assertTrue(isSearchSuccessful);
    }

    @Test
    public void testPartialBookTitle() {
        String bookTitle = "New";
        SearchResult books = new LoginPage(driver)
                .loginAs(USERNAME, PASSWORD)
                .searchForBook(bookTitle);

        boolean isSearchSuccessful = books.isExists(bookTitle);

        int num = books.countResults();
        assertEquals(1, num);
        assertTrue(isSearchSuccessful);
    }

    @Test
    public void testNonEnglishBookTitle() {
        String bookTitle = "أرض زيكولا";
        boolean isSearchSuccessful = performSearchTest(bookTitle);

        assertTrue(isSearchSuccessful);
    }

    @Test
    public void testDifferentCaseBookTitle() {
        String bookTitle = "tHe-pSyChOlOgY-oF-mOnEy";
        boolean isSearchSuccessful = new LoginPage(driver)
                .loginAs(USERNAME, PASSWORD)
                .searchForBook(bookTitle)
                .isExists("The-Psychology");

        assertTrue(isSearchSuccessful);
    }

    @Test
    public void testSearchByAuthorName() {
        String authorName = "Ahed";
        String bookTitle = "New Title";
        boolean isSearchSuccessful = new LoginPage(driver)
                .loginAs(USERNAME, PASSWORD)
                .searchForBook(authorName)
                .isExists(bookTitle);

        assertTrue(isSearchSuccessful);
    }

    @Test
    public void testSearchSpecialCharacters() {
        String bookTitle = "12 Months to $1 Million";
        boolean isSearchSuccessful = performSearchTest(bookTitle);

        assertTrue(isSearchSuccessful);
    }


    @Test
    public void testSearchNonExistingBook() {
        String bookTitle = "Unknown Name";
        boolean isSearchSuccessful = new LoginPage(driver)
                .loginAs(USERNAME, PASSWORD)
                .searchForBook(bookTitle)
                .notExists();

        assertTrue(isSearchSuccessful);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}
