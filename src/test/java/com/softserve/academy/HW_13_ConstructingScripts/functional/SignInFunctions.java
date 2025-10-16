package com.softserve.academy.HW_13_ConstructingScripts.functional;

import com.softserve.academy.HW_13_ConstructingScripts.pages.SignInPage;
import com.softserve.academy.HW_13_ConstructingScripts.pages.UbsHomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SignInFunctions {
    private final SignInPage signInPage;
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SignInFunctions(SignInPage signInPage, WebDriver driver, WebDriverWait wait) {
        this.signInPage = signInPage;
        this.driver = driver;
        this.wait = wait;
    }


    // Enter both email and password
    public void enterCredentials(String email, String password) {
        signInPage.enterCredentials(email, password);
    }

    // Enter both email and password and click TAB
    public void enterCredentialsAndEmailTab(String email, String password) {
        signInPage.enterCredentials(email, password);
        signInPage.getEmailInput().sendKeys(Keys.TAB);
    }

    public void enterCredentialsAndPasswordTab(String email, String password) {
        signInPage.enterCredentials(email, password);
        signInPage.getPasswordInput().sendKeys(Keys.TAB);
    }


    // Click on Sign In
    public void clickSignInSubmit() {
        signInPage.clickSignInSubmit();
    }

    // Full login
    public UbsHomePage signInWith(String email, String password) {
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        signInPage.clickSignInSubmit();

        return new UbsHomePage(driver, wait);
    }

    // Close Sign In modal
    public void closeSignInModal() {
        signInPage.closeModal();
    }

    @Deprecated
    public boolean trySignInAndReturnSuccess(String email, String password) {
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        signInPage.clickSignInSubmit();

        try {
            UbsHomePage ubsHomePage = new UbsHomePage(driver, wait);
            String userName = ubsHomePage.getLoggedInUserName();
            return userName != null && !userName.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
