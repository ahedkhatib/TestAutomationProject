package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "query")
    private WebElement searchField;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BookDetailsPage openBookById(String bookId) {
        String bookcssSelector = String.format("a[href=\"/book/%s\"]", bookId);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement book = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(bookcssSelector))));

        book.click();
        return new BookDetailsPage(driver);
    }

    public SearchResult searchForBook(String bookTitle) {
        searchField.clear();
        searchField.sendKeys(bookTitle);
        searchField.submit();

        return new SearchResult(driver);
    }

}
