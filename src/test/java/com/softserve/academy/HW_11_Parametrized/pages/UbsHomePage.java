package com.softserve.academy.HW_11_Parametrized.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UbsHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "li.ubs-user-name")
    private WebElement userName;

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
        PageFactory.initElements(driver, this);
    }

    //========================================================================================
    //                               WAIT
    //========================================================================================
    private WebElement waitUntilVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not visible: " + element, e);
        }
    }

    //========================================================================================
    //                         USER NAME
    //========================================================================================

    public String getLoggedInUserName() {
        return waitUntilVisible(userName).getText();

    }

}