package selenium;

import org.example.LoginPage;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReadInBrowserTest {
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
        //driver.get("http://localhost:8083");

        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
            WebElement visitSiteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Visit Site']")));
            visitSiteButton.click();
        } catch (TimeoutException err) {
            System.out.println("Ngrok warning page was not loaded");
        }
    }

    @Test
    public void testReadInBrowser() {
        boolean isReaderOpened = new LoginPage(driver)
                .loginAs(USERNAME, PASSWORD)
                .openBookById("10")
                .clickReadInBrowser()
                .isReaderPageOpened("10");

        assertTrue(isReaderOpened);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}


