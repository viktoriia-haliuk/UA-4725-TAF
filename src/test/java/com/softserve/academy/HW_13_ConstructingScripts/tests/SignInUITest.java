package com.softserve.academy.HW_13_ConstructingScripts.tests;

import org.junit.jupiter.api.*;
import static com.softserve.academy.HW_13_ConstructingScripts.pages.LoginPageExpectedTexts.*;

public class SignInUITest extends SignInBaseTest {

    //================================== UI CHECKS ==================================

    @Test
    @DisplayName("Verify page title")
    public void shouldVerifyTitle() {
        signInAssertions.assertPageTitle(EXPECTED_PAGE_TITLE);
    }

    @Test
    @DisplayName("Verify Welcome and Sign In texts")
    public void shouldVerifyWelcomeSignInText() {
        signInAssertions.assertWelcomeSignInDetailsText(
                EXPECTED_WELCOME_TEXT,
                EXPECTED_SIGN_IN_DETAILS_TEXT
        );
    }

    @Test
    @DisplayName("Verify Email field UI")
    public void shouldVerifyEmailField() {
        signInAssertions.assertEmailField();
    }

    @Test
    @DisplayName("Verify Password field UI")
    public void shouldVerifyPasswordField() {
        signInAssertions.assertPasswordField();
    }

    @Test
    @DisplayName("Verify Sign In button UI")
    public void shouldVerifySignInSubmitButtonUI() {
        signInAssertions.assertSignInSubmitButtonUI();
    }

    @Test
    @DisplayName("Verify Sign In with Google button UI")
    public void shouldVerifySignInWithGoogleButtonUI() {
        signInAssertions.assertSignInWithGoogleButtonUI();
    }

    @Test
    @DisplayName("Verify Forgot Password link UI")
    public void shouldVerifyForgotPasswordLinkUI() {
        signInAssertions.assertForgotPasswordLinkUI();
    }

    @Test
    @DisplayName("Verify Sign Up link UI")
    public void shouldVerifySignUpLinkUI() {
        signInAssertions.assertSignUpLinkUI();
    }

    // TO DO - FIX hardcore
    @Test
    @DisplayName("Verify input fields accept data and button becomes enabled")
    public void verifyFieldsInputAndButtonEnabled() {
        signInFunctions.enterCredentials("test@gmail.com", "Password123*");
        signInAssertions.assertFieldsInputAndButtonEnabled("test@gmail.com", "Password123*");
    }

    @Test
    @DisplayName("Verify Close button closes Sign In modal")
    public void verifyCloseButton() {
        signInAssertions.assertModalIsOpen();
        signInFunctions.closeSignInModal();
        signInAssertions.assertModalIsClosed();
    }
}
