package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReaderPage {
    private WebDriver driver;

    public ReaderPage(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/read"));
    }

    public Boolean isReaderPageOpened(String bookId) {
        boolean checkId = driver.getCurrentUrl().contains(bookId);

        return checkId && driver.getCurrentUrl().contains("read");
    }
}
