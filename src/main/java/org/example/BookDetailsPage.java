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

    @FindBy(id = "btnGroupDrop1pdf")
    private WebElement downloadButton;

    @FindBy(id = "readbtn")
    private WebElement readInBrowserButton;

    @FindBy(id = "have_read_cb")
    private WebElement readCheckbox;

    @FindBy(css = "a.btn-xs.btn-success[role='button']")
    private WebElement isbnButton;

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

    public Boolean clickDownloadButton(String downloadDir, String expectedFileName) throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(downloadButton));
        button.click();

        return verifyFileDownloaded(downloadDir, expectedFileName);
    }

    public Boolean verifyFileDownloaded(String downloadDir, String expectedFileName) throws InterruptedException {
        File downloadFolder = new File(downloadDir);
        File downloadedFile = new File(downloadFolder, expectedFileName);

        int timeout = 30;
        while (timeout > 0 && !downloadedFile.exists()) {
            Thread.sleep(1000);
            timeout--;
        }

        if (!downloadedFile.exists()) {
            throw new RuntimeException("The file was not downloaded");
        }

        return true;
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

    public HomePage markAsRead() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(readCheckbox));

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        driver.navigate().refresh();
        return new HomePage(driver);
    }

    public boolean isMarkedAsRead() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(readCheckbox));
        return checkbox.isSelected();
    }

    public ISBNPage clickIsbnButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(isbnButton));

        button.click();

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        return new ISBNPage(driver);
    }
}
