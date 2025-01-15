package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ISBNPage {
    private WebDriver driver;

    public ISBNPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isCorrectIsbnPage(String expectedUrl) {
        return driver.getCurrentUrl().contains(expectedUrl);
    }
}
