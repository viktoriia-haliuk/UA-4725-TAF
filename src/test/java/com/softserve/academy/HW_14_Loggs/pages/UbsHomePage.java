package com.softserve.academy.HW_14_Loggs.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.softserve.academy.HW_14_Loggs.pages.LoginPageLocators.*;

public class UbsHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;


    //========================================================================================
    //                                CONSTRUCTOR
    //========================================================================================
    public UbsHomePage(WebDriver driver, WebDriverWait wait) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver must not be null");
        }
        if (wait == null) {
            throw new IllegalArgumentException("WebDriverWait must not be null");
        }
        this.driver = driver;
        this.wait = wait;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    //========================================================================================
    //                         USER NAME
    //========================================================================================

    public String getLoggedInUserName() {
        WebElement userNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_NAME));
        return (String) js.executeScript("return arguments[0].textContent.trim();", userNameElement);

    }

}