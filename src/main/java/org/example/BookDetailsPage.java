package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class BookDetailsPage {
    private WebDriver driver;

    @FindBy(id = "edit_book")
    private WebElement editMetadataButton;

    @FindBy(id = "readbtn")
    private WebElement readInBrowserButton;

    public BookDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public EditMetadataPage clickEditMetadata() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(editMetadataButton));
        button.click();
        return new EditMetadataPage(driver);
    }

    public ReaderPage clickReadInBrowser() {
        String originalTab = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(readInBrowserButton));
        button.click();

        WebDriverWait tabWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        tabWait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        return new ReaderPage(driver);
    }

}
