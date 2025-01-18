package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.channels.Selector;
import java.util.List;

public class VerifyUpdatedMetadataPage {
    private WebDriver driver;

    @FindBy(id = "flash_success")
    private WebElement successMessage;

    @FindBy(id = "title")
    private WebElement title;

    @FindBy(css = "p.author a")
    private WebElement author;

    @FindBy(css = "a.btn.btn-xs.btn-info")
    private WebElement tag;

    @FindBy(css = "p > a[href^='/series/stored/']")
    private WebElement series;

    @FindBy(xpath = "//p[contains(text(), 'Book')]")
    private WebElement seriesId;

    @FindBy(css = "div.rating span.glyphicon.glyphicon-star.good")
    private List<WebElement> ratingStars;

    @FindBy(css = "div.publishing-date p")
    private WebElement date;

    @FindBy(css = "div.publishers a")
    private WebElement publishers;

    @FindBy(css = "span.label.label-default")
    private WebElement language;

    public VerifyUpdatedMetadataPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getMessage() {
        return successMessage.getText();
    }

    public String getTitle() {
        return title.getText();
    }

    public String getAuthor() {
        return author.getText();
    }

    public String getTag() {
        return tag.getText();
    }

    public String getSeries() {
        return series.getText();
    }

    public String getSeriesId() {
        return seriesId.getText();
    }

    public int getRating() {
        return ratingStars.size();
    }

    public String getDate() {
        return date.getText();
    }

    public String getPublishers() {
        return publishers.getText();
    }

    public String getLanguage() {
        return language.getText();
    }
}
