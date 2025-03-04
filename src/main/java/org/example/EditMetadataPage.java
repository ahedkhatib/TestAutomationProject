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

    @FindBy(id = "pubdate_delete")
    private WebElement publishedDateDelete;

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

    public VerifyUpdatedMetadataPage deletePublishedDate() {
        if (publishedDate.getText() != null ){
            publishedDateDelete.click();
        }

        saveButton.click();

        return new VerifyUpdatedMetadataPage(driver);
    }

    /*public String clearRating() {
        //WebElement clearRatingButton = driver.findElement(By.cssSelector("a.rating-clear"));

        if (clearRatingButton.isDisplayed()) {
            clearRatingButton.click();


            saveButton.click();

            WebElement successMessage = driver.findElement(By.id("flash_success"));
            return successMessage.getText();
        }
        return "Clear rating button is not visible";
    }*/
}
