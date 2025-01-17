package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;


    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public HomePage loginAs(String username, String password) {
        System.out.println("Navigating to login page...");
        wait.until(ExpectedConditions.urlContains("/login"));

        System.out.println("Waiting for username field...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        usernameField.sendKeys(username);
        System.out.println("Username entered.");

        System.out.println("Waiting for password field...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        passwordField.sendKeys(password);
        System.out.println("Password entered.");

        System.out.println("Waiting for login button...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']")));
        loginButton.click();
        System.out.println("Login button clicked.");

        return new HomePage(driver);
    }
    /*public HomePage loginAs(String username, String password){
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new HomePage(driver);
        *//*usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);*//*
    }*/



}
