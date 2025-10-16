package com.softserve.academy.HW_12_JSInject.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserve.academy.HW_12_JSInject.pages.LoginPageLocators.*;


public class SignInPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;


    //========================================================================================
    //                                CONSTRUCTOR
    //========================================================================================

    public SignInPage(WebDriver driver, WebDriverWait wait) {
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
    //                               WAITS
    //========================================================================================
    private WebElement waitUntilVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not visible: " + locator, e);
        }
    }

    private WebElement waitUntilClickable(WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not clickable: " + element, e);
        }
    }

    private WebElement waitUntilClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not clickable: " + locator, e);
        }
    }


    //========================================================================================
    //                OPEN SIGN IN MODAL AND CLOSE IFRAME IF PRESENT
    //========================================================================================

    private void closeGoogleSignInIframeIfPresent() {
        By iframeLocator = By.cssSelector("#credential_picker_container iframe");
        By closeButtonLocator = By.cssSelector("div#close");

        if (!driver.findElements(iframeLocator).isEmpty()) {
            WebElement iframeElement = waitUntilVisible(iframeLocator);
            driver.switchTo().frame(iframeElement);

            try {
                WebElement closeButton = waitUntilClickable(driver.findElement(closeButtonLocator));
                js.executeScript("arguments[0].click();", closeButton);

            } catch (TimeoutException e) {
                System.out.println("Close button in Google iframe not found, continuing...");
            }
            System.out.println("Google sign-in iframe was found and closed.");
            driver.switchTo().defaultContent();
        } else {
            System.out.println("No Google sign-in iframe appeared — continuing test.");
        }
    }

    public SignInPage openSignInModal() {
        waitUntilClickable(SIGN_IN_BUTTON).click();
        closeGoogleSignInIframeIfPresent();
        waitUntilVisible(EMAIL_INPUT);
        return this;
    }
    //========================================================================================
    //                             TITLE, WELCOME TEXTS
    //========================================================================================

    // Title
    public String getTitle() {
        return (String) js.executeScript("return document.title;");
    }

    // Welcome texts
    public String getWelcomeText() {
        WebElement welcomeHeader = waitUntilVisible(WELCOME_TEXT);
        return (String) js.executeScript("return arguments[0].textContent.trim();", welcomeHeader);

//        return (String) js.executeScript("return document.querySelector('.container h1').textContent.trim();");

    }

    public String getSignInDetailsText() {
        WebElement signInHeader = waitUntilVisible(SIGN_IN_DETAILS_TEXT);
        return (String) js.executeScript("return arguments[0].textContent.trim();", signInHeader);
    }


    //========================================================================================
    //                             LABELS
    //========================================================================================
    public String getEmailLabelText() {
        WebElement emailLabel = waitUntilVisible(EMAIL_LABEL);
        return (String) js.executeScript("return arguments[0].textContent.trim();", emailLabel);

    }

    public String getPasswordLabelText() {
        WebElement passwordLabel = waitUntilVisible(PASSWORD_LABEL);
        return (String) js.executeScript("return arguments[0].textContent.trim();", passwordLabel);
    }


    //========================================================================================
    //                             INPUTS
    //========================================================================================
    public WebElement getEmailInput() {
        return waitUntilVisible(EMAIL_INPUT);

    }

    public WebElement getPasswordInput() {
        return waitUntilVisible(PASSWORD_INPUT);
    }

    public boolean isEmailInputVisible() {
        return waitUntilVisible(EMAIL_INPUT).isDisplayed();
    }

    public boolean isPasswordInputVisible() {
        return waitUntilVisible(PASSWORD_INPUT).isDisplayed();
    }

    public boolean isEmailInputEnabled() {
        return waitUntilVisible(EMAIL_INPUT).isEnabled();
    }

    public boolean isPasswordInputEnabled() {
        return waitUntilVisible(PASSWORD_INPUT).isEnabled();
    }


    public String getEmailPlaceholder() {
        return waitUntilVisible(EMAIL_INPUT).getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return waitUntilVisible(PASSWORD_INPUT).getAttribute("placeholder");
    }


    public SignInPage enterEmail(String email) {
        WebElement emailInput = waitUntilClickable(EMAIL_INPUT);
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;

    }

    public SignInPage enterPassword(String password) {
        WebElement passwordInput = waitUntilClickable(PASSWORD_INPUT);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;

    }

    public SignInPage enterCredentials(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return this;
    }

    //========================================================================================
    //                             SIGN IN ACTIONS
    //========================================================================================

    public UbsHomePage clickSignInSubmit() {
        waitUntilClickable(SIGN_IN_SUBMIT_BUTTON).click();
        return new UbsHomePage(driver, wait);
    }

    public UbsHomePage signInWith(String email, String password) {
        return enterCredentials(email, password)
                .clickSignInSubmit();
    }


    //========================================================================================
    //                             BUTTONS
    //========================================================================================
    // Buttons
    public boolean isSignInSubmitVisible() {
        return waitUntilVisible(SIGN_IN_SUBMIT_BUTTON).isDisplayed();
    }

    public boolean isSignInSubmitEnabled() {
        return waitUntilVisible(SIGN_IN_SUBMIT_BUTTON).isEnabled();
    }

    public String getSignInSubmitText() {
        return waitUntilVisible(SIGN_IN_SUBMIT_BUTTON).getText();
    }

    public boolean isGoogleSignInVisible() {
        return waitUntilVisible(GOOGLE_SIGN_IN_BUTTON).isDisplayed();
    }

    public boolean isGoogleSignInEnabled() {
        return waitUntilVisible(GOOGLE_SIGN_IN_BUTTON).isEnabled();
    }

    public String getGoogleSignInText() {
        return waitUntilVisible(GOOGLE_SIGN_IN_BUTTON).getText();
    }


    //========================================================================================
    //                             ERROR MESSAGES
    //========================================================================================

    public String getEmailErrorText() {
        try {
            return waitUntilVisible(ERROR_EMAIL).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String getPasswordErrorText() {
        try {
            return waitUntilVisible(ERROR_PASSWORD).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String getGeneralErrorText() {
        try {
            return waitUntilVisible(GENERAL_ERROR_MESSAGE).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }


    //========================================================================================
    //                             LINKS
    //========================================================================================
    public boolean isForgotPasswordVisible() {
        return waitUntilVisible(FORGOT_PASSWORD_LINK).isDisplayed();
    }

    public boolean isForgotPasswordEnabled() {
        return waitUntilVisible(FORGOT_PASSWORD_LINK).isEnabled();
    }

    public String getForgotPasswordText() {
        return waitUntilVisible(FORGOT_PASSWORD_LINK).getText();
    }

    public boolean isSignUpLinkVisible() {
        return waitUntilVisible(SIGN_UP_LINK).isDisplayed();
    }

    public boolean isSignUpLinkEnabled() {
        return waitUntilVisible(SIGN_UP_LINK).isEnabled();
    }

    public String getSignUpLinkText() {
        return waitUntilVisible(SIGN_UP_LINK).getText();
    }

    //========================================================================================
    //                             CLOSE BUTTON
    //========================================================================================

    public boolean isCloseButtonVisible() {
        return waitUntilVisible(CLOSE_BUTTON).isDisplayed();
    }

    public boolean isCloseButtonEnabled() {
        return waitUntilVisible(CLOSE_BUTTON).isEnabled();
    }

    //========================================================================================
    //                            MODAL SIGN IN WINDOW
    //========================================================================================
    public SignInPage closeModal() {
        WebElement closeButton = waitUntilVisible(CLOSE_BUTTON);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SIGN_IN_MODAL));
        return this;
    }


    public boolean isSignInModalVisible() {
        try {
            waitUntilVisible(SIGN_IN_MODAL);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}