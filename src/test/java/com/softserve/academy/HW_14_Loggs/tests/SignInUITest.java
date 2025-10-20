package com.softserve.academy.HW_14_Loggs.tests;

import com.softserve.academy.HW_14_Loggs.data.SignInDataRepository;
import com.softserve.academy.HW_14_Loggs.data.SignInTestData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.softserve.academy.HW_14_Loggs.pages.LoginPageExpectedTexts.*;

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

    // Option 1 - Direct Repository Access
    @Test
    @DisplayName("Verify input fields accept data and button becomes enabled")
    void verifyFieldsInputAndButtonEnabled() {
        SignInTestData data = SignInDataRepository.getValidLogins().get(0);

        signInFunctions.enterCredentials(data.getEmail(), data.getPasswordKey());
        signInAssertions.assertFieldsInputAndButtonEnabled(data.getEmail(), data.getPasswordKey());
    }


    // Option 2 - Parameterized Test via Arguments
    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#validLoginsAsArguments")
    @DisplayName("Verify input fields accept data and button becomes enabled")
    void verifyFieldsInputAndButtonEnabled(String email, String password, String ignoredExpectedUserName) {
        signInFunctions.enterCredentials(email, password);
        signInAssertions.assertFieldsInputAndButtonEnabled(email, password);
    }

    // Option 3 - Parameterized Test via Data Object
    @ParameterizedTest
    @MethodSource("com.softserve.academy.HW_14_Loggs.data.SignInMethodSources#validLoginsAsTestData")
    @DisplayName("Verify input fields accept data and button becomes enabled")
    void verifyFieldsInputAndButtonEnabled(SignInTestData data) {
        signInFunctions.enterCredentials(data.getEmail(), data.getPasswordKey());
        signInAssertions.assertFieldsInputAndButtonEnabled(data.getEmail(), data.getPasswordKey());
    }



    @Test
    @DisplayName("Verify Close button closes Sign In modal")
    public void verifyCloseButton() {
        signInAssertions.assertModalIsOpen();
        signInFunctions.closeSignInModal();
        signInAssertions.assertModalIsClosed();
    }
}
