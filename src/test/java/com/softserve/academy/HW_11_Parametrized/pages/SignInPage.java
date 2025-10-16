package com.softserve.academy.HW_11_Parametrized.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SignInPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //========================================================================================
    //                                LOCATORS
    //========================================================================================

    // Header / open modal
    @FindBy(css = ".ubs-header-sing-in-img") private WebElement signInButton;

    // Modal texts / labels
    @FindBy(css = ".container h1") private WebElement welcomeText;

    @FindBy(css = ".container h2") private WebElement signInDetailsText;

    @FindBy(css = "label[for='email']") private WebElement emailLabel;

    @FindBy(css = "label[for='password']") private WebElement passwordLabel;

    // Inputs / buttons / errors
    @FindBy(id = "email") private WebElement emailInput;

    @FindBy(id = "password") private WebElement passwordInput;

    @FindBy(css = "button.ubsStyle") private WebElement signInSubmitButton;

    @FindBy(css = "div.alert-general-error") private WebElement generalErrorMessage;

    @FindBy(id = "email-err-msg") private WebElement errorEmail;

    @FindBy(id = "pass-err-msg") private WebElement errorPassword;

    @FindBy(css = "a.close-modal-window[aria-label='close form button']") private WebElement closeButton;

    @FindBy(css = "button.google-sign-in") private WebElement googleSignInButton;

    @FindBy(css = ".forgot-wrapper-ubs a") private WebElement forgotPasswordLink;

    @FindBy(css = ".missing-account a") private WebElement signUpLink;

    @FindBy(css = ".mat-mdc-dialog-container") private WebElement signInModal;

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
        PageFactory.initElements(driver, this);
    }

    //========================================================================================
    //                               WAITS
    //========================================================================================
    private WebElement waitUntilVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            throw new TimeoutException("Element not visible: " + element, e);
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
            driver.switchTo().frame(driver.findElement(iframeLocator));
            try {
                waitUntilClickable(closeButtonLocator).click();
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
        waitUntilClickable(signInButton).click();
        closeGoogleSignInIframeIfPresent();
        waitUntilVisible(emailInput);
        return this;
    }
    //========================================================================================
    //                             TITLE, WELCOME TEXTS
    //========================================================================================

    // Title
    public String getTitle() {
        return driver.getTitle();
    }

    // Welcome texts
    public String getWelcomeText() {
        return waitUntilVisible(welcomeText).getText();
    }

    public String getSignInDetailsText() {
        return waitUntilVisible(signInDetailsText).getText();
    }


    //========================================================================================
    //                             LABELS
    //========================================================================================
    public String getEmailLabelText() {
        return waitUntilVisible(emailLabel).getText();
    }

    public String getPasswordLabelText() {
        return waitUntilVisible(passwordLabel).getText();
    }


    //========================================================================================
    //                             INPUTS
    //========================================================================================
    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public boolean isEmailInputVisible() {
        return waitUntilVisible(emailInput).isDisplayed();
    }

    public boolean isPasswordInputVisible() {
        return waitUntilVisible(passwordInput).isDisplayed();
    }

    public boolean isEmailInputEnabled() {
        return emailInput.isEnabled();
    }

    public boolean isPasswordInputEnabled() {
        return passwordInput.isEnabled();
    }


    public String getEmailPlaceholder() {
        return waitUntilVisible(emailInput).getAttribute("placeholder");
    }

    public String getPasswordPlaceholder() {
        return waitUntilVisible(passwordInput).getAttribute("placeholder");
    }


    public SignInPage enterEmail(String email) {
        waitUntilClickable(emailInput).clear();
        emailInput.sendKeys(email);
        return this;

    }

    public SignInPage enterPassword(String password) {
        waitUntilClickable(passwordInput).clear();
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
        waitUntilClickable(signInSubmitButton).click();
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
        return waitUntilVisible(signInSubmitButton).isDisplayed();
    }

    public boolean isSignInSubmitEnabled() {
        return signInSubmitButton.isEnabled();
    }

    public String getSignInSubmitText() {
        return waitUntilVisible(signInSubmitButton).getText();
    }

    public boolean isGoogleSignInVisible() {
        return waitUntilVisible(googleSignInButton).isDisplayed();
    }

    public boolean isGoogleSignInEnabled() {
        return googleSignInButton.isEnabled();
    }

    public String getGoogleSignInText() {
        return waitUntilVisible(googleSignInButton).getText();
    }


    //========================================================================================
    //                             ERROR MESSAGES
    //========================================================================================

    public String getEmailErrorText() {
        try {
            return waitUntilVisible(errorEmail).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String getPasswordErrorText() {
        try {
            return waitUntilVisible(errorPassword).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String getGeneralErrorText() {
        try {
            return waitUntilVisible(generalErrorMessage).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }


    //========================================================================================
    //                             LINKS
    //========================================================================================
    public boolean isForgotPasswordVisible() {
        return waitUntilVisible(forgotPasswordLink).isDisplayed();
    }

    public boolean isForgotPasswordEnabled() {
        return forgotPasswordLink.isEnabled();
    }

    public String getForgotPasswordText() {
        return waitUntilVisible(forgotPasswordLink).getText();
    }

    public boolean isSignUpLinkVisible() {
        return waitUntilVisible(signUpLink).isDisplayed();
    }

    public boolean isSignUpLinkEnabled() {
        return signUpLink.isEnabled();
    }

    public String getSignUpLinkText() {
        return waitUntilVisible(signUpLink).getText();
    }

    //========================================================================================
    //                             CLOSE BUTTON
    //========================================================================================

    public boolean isCloseButtonVisible() {
        return waitUntilVisible(closeButton).isDisplayed();
    }

    public boolean isCloseButtonEnabled() {
        return closeButton.isEnabled();
    }

    //========================================================================================
    //                            MODAL SIGN IN WINDOW
    //========================================================================================
    public SignInPage closeModal() {
        waitUntilVisible(closeButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
        wait.until(ExpectedConditions.invisibilityOf(signInModal));
        return this;
    }


    public boolean isSignInModalVisible() {
        try {
            return signInModal.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }


}