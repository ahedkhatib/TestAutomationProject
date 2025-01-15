package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    //@FindBy(xpath = "//a[@href='/book/10']")
    //private WebElement firstBook;

    @FindBy(id = "flash_success")
    private WebElement successMessage;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BookDetailsPage openBookById(String bookId) {
        String bookcssSelector = String.format("a[href=\"/book/%s\"]", bookId);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement book = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(bookcssSelector))));

        book.click();
        //driver.findElement(By.cssSelector("a[href=\"/book/10\"]")).click();
        return new BookDetailsPage(driver);
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessageWait = wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessageWait.getText();
    }

}
