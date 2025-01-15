package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditMetadataPage {
    private WebDriver driver;

    @FindBy(id = "title")
    private WebElement titleField;

    @FindBy(id = "submit")
    private WebElement saveButton;

    //@FindBy(css = ".rating-clear")
    //private WebElement clearRatingButton;

    @FindBy(id = "authors")
    private WebElement authorField;

    @FindBy(id = "tags")
    private WebElement tagField;

    @FindBy(id = "series")
    private WebElement seriesField;

    @FindBy(id = "series_index")
    private WebElement seriesId;

    @FindBy(css = ".rating-input.input-lg")
    private WebElement ratingContainer;

    @FindBy(css = "a.rating-clear")
    private WebElement clearRatingButton;

    public EditMetadataPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String modifyTitleMetadata(String newTitle){
        titleField.clear();
        titleField.sendKeys(newTitle);

        saveButton.click();
        WebElement successMessage = driver.findElement(By.id("flash_success"));

        return successMessage.getText();
    }

    public String modifyAuthorMetadata(String newAuthor){
        authorField.clear();
        authorField.sendKeys(newAuthor);

        saveButton.click();
        WebElement successMessage = driver.findElement(By.id("flash_success"));

        return successMessage.getText();
    }

    public String modifyTagMetadata(String newTag){
        tagField.clear();
        tagField.sendKeys(Keys.CONTROL + "a");
        tagField.sendKeys(Keys.DELETE);
        tagField.sendKeys(newTag);

        saveButton.click();
        WebElement successMessage = driver.findElement(By.id("flash_success"));

        return successMessage.getText();
    }

    public String modifySeriesMetadata(String newSeries){
        seriesField.clear();
        seriesField.sendKeys(Keys.CONTROL + "a");
        seriesField.sendKeys(Keys.DELETE);
        seriesField.sendKeys(newSeries);

        saveButton.click();
        WebElement successMessage = driver.findElement(By.id("flash_success"));

        return successMessage.getText();
    }

    public String modifySeriesIdMetadata(String newSeriesId){
        seriesId.clear();
        seriesId.sendKeys(Keys.CONTROL + "a");
        seriesId.sendKeys(Keys.DELETE);
        seriesId.sendKeys(newSeriesId);

        saveButton.click();
        WebElement successMessage = driver.findElement(By.id("flash_success"));

        return successMessage.getText();
    }

    public String modifyRating(int ratingValue) {
        WebElement star = ratingContainer.findElement(By.cssSelector("i[data-value='" + ratingValue + "']"));
        star.click();

        saveButton.click();
        WebElement successMessage = driver.findElement(By.id("flash_success"));

        return successMessage.getText();
    }

    public String clearRating() {
        //WebElement clearRatingButton = driver.findElement(By.cssSelector("a.rating-clear"));

        if (clearRatingButton.isDisplayed()) {
            clearRatingButton.click();


            saveButton.click();

            WebElement successMessage = driver.findElement(By.id("flash_success"));
            return successMessage.getText();
        }
        return "Clear rating button is not visible";
    }


    /*public void clearRating() {
        if (clearRatingButton.isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement clearButton = wait.until(ExpectedConditions.elementToBeClickable(clearRatingButton));

            clearButton.click();
            WebDriverWait wait_save = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement save = wait_save.until(ExpectedConditions.elementToBeClickable(saveButton));
            save.click();
        }
    }*/

}
