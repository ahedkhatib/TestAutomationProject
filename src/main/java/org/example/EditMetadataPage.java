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

    @FindBy(id = "pubdate")
    private WebElement publishedDate;

    @FindBy(id = "publisher")
    private WebElement publisherField;

    @FindBy(id = "languages")
    private WebElement languageField;

    public EditMetadataPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private VerifyUpdatedMetadataPage modifyField(WebElement field, String newValue) {
        field.clear();
        field.sendKeys(Keys.CONTROL + "a");
        field.sendKeys(Keys.DELETE);
        field.sendKeys(newValue);

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }

    public VerifyUpdatedMetadataPage modifyTitleMetadata(String newTitle) {
        return modifyField(titleField, newTitle);
    }

    public VerifyUpdatedMetadataPage modifyAuthorMetadata(String newAuthor) {
        return modifyField(authorField, newAuthor);
    }

    public VerifyUpdatedMetadataPage modifyTagMetadata(String newTag) {
        return modifyField(tagField, newTag);
    }

    public VerifyUpdatedMetadataPage modifySeriesMetadata(String newSeries) {
        return modifyField(seriesField, newSeries);
    }

    public VerifyUpdatedMetadataPage modifySeriesIdMetadata(String newSeriesId) {
        return modifyField(seriesId, newSeriesId);
    }

    public VerifyUpdatedMetadataPage modifyPublishedDate(String newDate) {
        return modifyField(publishedDate, newDate);
    }

    public VerifyUpdatedMetadataPage modifyPublisher(String newPublisher) {
        return modifyField(publisherField, newPublisher);
    }

    public VerifyUpdatedMetadataPage modifyLanguage(String newLanguage) {
        return modifyField(languageField, newLanguage);
    }

    public VerifyUpdatedMetadataPage modifyRating(int ratingValue) {
        WebElement star = ratingContainer.findElement(By.cssSelector("i[data-value='" + ratingValue + "']"));
        star.click();

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }


    /// *******************************
    /*public VerifyUpdatedMetadataPage modifyTitleMetadata(String newTitle){
        titleField.clear();
        titleField.sendKeys(newTitle);

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }

    public VerifyUpdatedMetadataPage modifyAuthorMetadata(String newAuthor){
        authorField.clear();
        authorField.sendKeys(Keys.CONTROL + "a");
        authorField.sendKeys(Keys.DELETE);
        authorField.sendKeys(newAuthor);

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }

    public VerifyUpdatedMetadataPage modifyTagMetadata(String newTag){
        tagField.clear();
        tagField.sendKeys(Keys.CONTROL + "a");
        tagField.sendKeys(Keys.DELETE);
        tagField.sendKeys(newTag);

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }

    public VerifyUpdatedMetadataPage modifySeriesMetadata(String newSeries){
        seriesField.clear();
        seriesField.sendKeys(Keys.CONTROL + "a");
        seriesField.sendKeys(Keys.DELETE);
        seriesField.sendKeys(newSeries);

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }

    public VerifyUpdatedMetadataPage modifySeriesIdMetadata(String newSeriesId){
        seriesId.clear();
        seriesId.sendKeys(Keys.CONTROL + "a");
        seriesId.sendKeys(Keys.DELETE);
        seriesId.sendKeys(newSeriesId);

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }

    public VerifyUpdatedMetadataPage modifyRating(int ratingValue) {
        WebElement star = ratingContainer.findElement(By.cssSelector("i[data-value='" + ratingValue + "']"));
        star.click();

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }

    *//*public String clearRating() {
        //WebElement clearRatingButton = driver.findElement(By.cssSelector("a.rating-clear"));

        if (clearRatingButton.isDisplayed()) {
            clearRatingButton.click();


            saveButton.click();

            WebElement successMessage = driver.findElement(By.id("flash_success"));
            return successMessage.getText();
        }
        return "Clear rating button is not visible";
    }*//*

    public VerifyUpdatedMetadataPage modifyPublishedDate(String newDate) {
        publishedDate.clear();
        publishedDate.sendKeys(newDate);

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }

    public VerifyUpdatedMetadataPage modifyPublisher(String newPublisher) {
        publisherField.clear();
        publisherField.sendKeys(Keys.CONTROL + "a");
        publisherField.sendKeys(Keys.DELETE);
        publisherField.sendKeys(newPublisher);

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }

    public VerifyUpdatedMetadataPage modifyLanguage(String newLanguage) {
        languageField.clear();
        languageField.sendKeys(Keys.CONTROL + "a");
        languageField.sendKeys(Keys.DELETE);
        languageField.sendKeys(newLanguage);

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }*/

}
