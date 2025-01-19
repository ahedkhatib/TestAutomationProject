package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResult {
    private WebDriver driver;

    @FindBy(tagName = "h2")
    private WebElement noResultsFound;

    @FindBy(css = ".discover h2")
    private WebElement resultTitle;

    public SearchResult(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isExists(String bookTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement resultTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.title")));

        System.out.println(resultTitle.getText());
        return resultTitle.getText().contains(bookTitle);
    }

    public boolean notExists() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement resultTitle = wait.until(ExpectedConditions.visibilityOf(noResultsFound));

        return resultTitle.getText().contains("No Results Found");
    }

    public int countResults(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement result = wait.until(ExpectedConditions.visibilityOf(resultTitle));
        String text = result.getText();
        int numberResults = 0;

        for(char c: text.toCharArray()){
            if(c < '0'|| c > '9'){
                break;
            }
            numberResults = numberResults * 10 + (int) c -'0';
        }

        return numberResults;
    }
}
